package IA;

import java.io.Serializable;

import map.Case;
import map.Map;

public class Croix implements Strategie, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int[] doComputePosion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void degat(Map m) {
		Map map = m;
		Case c = null;
		boolean selected = false;
		
		// Choisir une case voisine � une case d�j� coul�e
		double r = Math.random();
		boolean left_to_right = (r<0.5)?true:false;
		r = Math.random();
		boolean bottom_to_top = (r<0.5)?true:false;
		
		for (int i = (left_to_right)?0:9; (left_to_right?(i<10):(i>0)) && !selected; i+=(left_to_right?1:-1))
			for (int j = (bottom_to_top)?0:9; (bottom_to_top?(j<10):(j>0)) && !selected; j+=(bottom_to_top?1:-1)) {
				r = Math.random ();
				int Di = (r<0.5)?1:-1;
				r = Math.random();
				int Dj = (r<0.5)?1:-1;
				
				if (map.grilleJoueur[i][j].touche && !map.grilleJoueur[i][j].coule) {
					c = map.grilleJoueur[i][j];
					selected = true;
				}
				else if (map.grilleJoueur[i][j].coule) {
					if (((Di>0 && i>0)||(Di<0 && i<9)) && !map.grilleJoueur[i-Di][j].coule && !map.grilleJoueur[i-Di][j].plouf) {
						c = map.grilleJoueur[i-Di][j];
						selected = true;
					}
					else if (((Di>0 && i<9)||(Di<0 && i>0)) && !map.grilleJoueur[i+Di][j].coule && !map.grilleJoueur[i+Di][j].plouf) {
						c = map.grilleJoueur[i+Di][j];
						selected = true;
					}
					else if (((Dj>0 && j>0)||(Dj<0 && j<9)) && !map.grilleJoueur[i][j-Dj].coule && !map.grilleJoueur[i][j-Dj].plouf) {
						c = map.grilleJoueur[i][j-Dj];
						selected = true;
					}
					else if (((Dj>0 && j<9)||(Dj<0 && j>0)) && !map.grilleJoueur[i][j+Dj].coule && !map.grilleJoueur[i][j+Dj].plouf) {
						c = map.grilleJoueur[i][j+Dj];
						selected = true;
					}
				}
			}
		
		// Sinon choisir al�atoirement
		if (!selected) {
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);
			c = map.grilleJoueur[x][y];
			
			while(true) {
				if (!c.touche && !c.plouf)
					break;
				x = (int) (Math.random() * 10);
				y = (int) (Math.random() * 10);
				c = map.grilleJoueur[x][y];
			}
		}
		
		map.infligerDegat(c);
		
	}

}
