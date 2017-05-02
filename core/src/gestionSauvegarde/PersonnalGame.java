package gestionSauvegarde;

import java.io.Serializable;
import java.util.ArrayList;

import jeu.Game;
import map.Boat;
import map.Old;

public class PersonnalGame extends Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PersonnalGame() {
		super();
		// map.joueur.addAll(basique());
		creAleatoire();
		map.botPlaceAlea();

	}

	public void creAleatoire() {
		while (map.nbCasesBot != 0) {
			int taille = tailleRandom();
			map.bot.add(Boat.factory((int) (Math.random() * (2 - 1)), taille));
			map.nbCasesBot = map.nbCasesBot - taille;
		}
	}

	int tailleRandom() {
		int i = (int) (Math.random() * 5);
		i++;

		if (i > map.nbCasesBot) {
			i = (int) (Math.random() * (map.nbCasesBot - 1));
		}
		return i;
	}

	public ArrayList<Boat> basique() {

		ArrayList<Boat> alb = new ArrayList<Boat>();

		alb.add(new Old(5));
		alb.add(new Old(5));
		alb.add(new Old(3));
		alb.add(new Old(3));
		alb.add(new Old(2));
		alb.add(new Old(2));

		return alb;

	}

	@Override
	public void gameLoop() {
		tour++;
		bot.executeStrategie(map);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (tour != 100) {
			System.out.println(tour);
			gameLoop();
		}
		map.afficherJoueur();
	}

	@Override
	public void tourJoueur() {
		map.infligerDegat(map.grilleJoueur[0][0]);
	}

}
