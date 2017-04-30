package gestionSauvegarde;

import java.io.Serializable;
import java.util.ArrayList;

import map.Boat;
import map.Old;

public class SimpleGame extends Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SimpleGame() {
		super();
		map.joueur.addAll(basique());
		map.bot.addAll(basique());
		map.botPlaceAlea();
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
