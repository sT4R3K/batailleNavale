package map;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int nbCasesJoueur, nbCasesBot;
	public Case[][] grilleJoueur, grilleBot;
	public ArrayList<Boat> joueur, bot;
	public Case selection;// = new Case();
	public Case put = null;
	public int posX;
	public int posY;
	public boolean sens = true;
	int sortieY = 1;
	int sortieX = 2;
	int supperposition = 3;
	public boolean tourJoueur = true;

	public Map() {
		grilleJoueur = new Case[10][10];
		grilleBot = new Case[10][10];
		joueur = new ArrayList<Boat>();
		bot = new ArrayList<Boat>();

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++)
				grilleBot[x][y] = new Case();
		}

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++)
				grilleJoueur[x][y] = new Case();
		}
	}

	public void botPlaceAlea() {
		for (Boat b : bot) {
			int tent = 0;
			while (true) {
				tent++;
				boolean sens = true;
				double i = Math.random();
				if (i < 0.5) {
					sens = false;
				}
				// Case c = new Case(b, 0, sens);
				int x = (int) (Math.random() * 10);
				int y = (int) (Math.random() * 10);
				int res = this.placement(x, y, sens, b, false);
				if (Boolean.logicalOr(res == 0, tent > 100)) {
					break;
				}
			}
		}
	}

	public int verifierFinPartie() {

		int j1 = -1;
		int j2 = -1;

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++)
				if (grilleJoueur[x][y].boat != null)
					if (grilleJoueur[x][y].boat.vie[grilleJoueur[x][y].zone] >= j1) {
						j1 = grilleJoueur[x][y].boat.vie[grilleJoueur[x][y].zone];
					}
		}
		if (j1 == 0)
			return 1;

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++)
				if (grilleBot[x][y].boat != null)
					if (grilleBot[x][y].boat.vie[grilleBot[x][y].zone] >= j2) {
						j2 = grilleBot[x][y].boat.vie[grilleBot[x][y].zone];
					}
		}
		if (j2 == 0)
			return 2;

		return 0;
	}

	void init(Case[][] grille) {
		for (int x = 0; x < grille.length; x++)
			for (int y = 0; y < grille.length; y++) {
				// grille[x][y] = new Case();
			}
	}

	public String err(int a) {

		switch (a) {

		case 1:
			return "sortie X";
		case 2:
			return "sortie Y";
		case 3:
			return "supperpisition";

		default:
			return "inconnu";

		}

	}

	public boolean verifierTouche(int x, int y, boolean joueur) {
		System.out.println("bite");
		Case[][] grille = grilleJoueur;
		if (!joueur) {
			grille = grilleBot;
		}
		if (grille[x][y] != null) {
			Boat b = grille[x][y].boat;
			int zone = grille[x][y].zone;
			if (b.vie[zone] > 0)
				return true;
		} else {
			grille[x][y] = new Case();
			grille[x][y].reveal = true;

			return false;
		}
		return false;
	}

	public boolean verifierTouche(Case c, boolean joueur) {
		System.out.println("bite");

		if (c != null) {
			Boat b = c.boat;
			int zone = c.zone;
			if (b.vie[zone] > 0)
				return true;
		} else {
			c = new Case();
			c.reveal = true;

			return false;
		}
		return false;
	}

	public void infligerDegat(Case c) {

		if (c.boat != null) {
			c.reveal = true;
			c.touche = true;
			if (c.boat.vie[Math.abs(c.zone)] > 0) {
				c.boat.hit(Math.abs(c.zone));
			}
		} else {
			c.reveal = true;
			c.plouf = true;
		}
	}

	public int placement(int x, int y, boolean sens, Boat boat, boolean joueur) {
		Case[][] grille;
		if (joueur) {
			grille = grilleJoueur;
		} else {
			grille = grilleBot;
		}

		int size = boat.vie.length;

		if (sens) {
			if (size + y > 10) {
				return sortieY;
			}
			for (int i = y; i < size + y; i++) {
				if (grille[x][i].boat != null) {
					return supperposition;
				}
			}
		} else {
			if (size + x > 10) {
				return sortieX;
			}
			for (int i = x; i < size + x; i++) {
				if (grille[i][y].boat != null) {
					return supperposition;
				}
			}
		}

		if (sens) {
			for (int i = y; i < size + y; i++) {
				grille[x][i] = new Case(boat, i - y, sens);
			}
		} else {
			for (int i = x; i < size + x; i++) {
				grille[i][y] = new Case(boat, i - x, sens);
			}
		}

		if (joueur) {
			this.joueur.remove(boat);
		}

		return 0;
	}

	public void afficherJoueur() {
		for (Case[] a : grilleJoueur) {
			for (Case b : a) {
				System.out.print("\t" + b);
			}
			System.out.println();
		}
	}

	public void afficherBot() {
		for (Case[] a : grilleBot) {
			for (Case b : a) {
				System.out.print("\t" + b);
			}
			System.out.println();
		}
	}
}
