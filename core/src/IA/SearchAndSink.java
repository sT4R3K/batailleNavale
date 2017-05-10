package IA;

import java.io.Serializable;

import map.Case;
import map.Map;
import java.util.Random;

public class SearchAndSink implements Strategie, Serializable {
	private int xBoat;
	private int yBoat;
	//0 = gauche
	//1 = droite
	//2 = haut
	//3 = bas
	private int dir;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchAndSink(){
		xBoat = -1;
		yBoat = -1;
		dir = 0;
	}

	@Override
	public int[] doComputePosion() {
		return null;
	}

	@Override
	public void degat(Map m) {
		Map map = m;
		Case c = null;
		int x,y;
		//recherche aleatoire de touche
		if(xBoat == -1) {
			x = (int) (Math.random() * 10);
			y = (int) (Math.random() * 10);
			c = map.grilleJoueur[x][y];

			while (true) {
				if (c.coule == false && c.plouf == false) {
					break;
				}
				x = (int) (Math.random() * 10);
				y = (int) (Math.random() * 10);
				c = map.grilleJoueur[x][y];
			}
		}else {
			x = xBoat;
			y = yBoat;
			if(!(map.grilleJoueur[x][y].touche && !map.grilleJoueur[x][y].coule)) {
				if (dir == 0) {
					while (dir == 0 && map.grilleJoueur[x][y].touche) {
						if (x + 1 >= map.grilleJoueur.length) {
							dir++;
						} else {
							x++;
						}
					}
				}
				if (dir == 1) {
					x = xBoat;
					y = yBoat;
					while (dir == 1 && map.grilleJoueur[x][y].touche) {
						if (x - 1 < 0) {
							dir++;
						} else {
							x--;
						}
					}
				}
				if (dir == 2) {
					x = xBoat;
					y = yBoat;
					while (dir == 2 && map.grilleJoueur[x][y].touche) {
						if (y + 1 >= map.grilleJoueur.length) {
							dir++;
						} else {
							y++;
						}
					}
				}
				if (dir == 3) {
					x = xBoat;
					y = yBoat;
					while (map.grilleJoueur[x][y].touche)
						y--;
				}
			}
		}

		c = map.grilleJoueur[x][y];
		map.infligerDegat(c);
		if(c.touche ) {
			xBoat = x;
			yBoat = y;
			if(c.boat.isDead()){
				xBoat = -1;
				yBoat = -1;
				dir = 0;
			}
		}else if(xBoat!=-1 && c.plouf){
			dir++;
		}

	}
}

