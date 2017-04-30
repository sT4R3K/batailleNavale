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

	int tour = 0 ;
	@Override
	public void degat(Map m) {
		Map map = m;
		Case c = null;
		boolean selected = false;
		
		// Choisir une case voisine à une case déjà coulée
		for (int i = 0; i < 10 && !selected; i++)
			for (int j = 0; j < 10 && !selected; j++) {
				if (map.grilleJoueur[i][j].touche) {
					if (i>0 && !map.grilleJoueur[i-1][j].touche && !map.grilleJoueur[i-1][j].plouf) {
						c = map.grilleJoueur[i-1][j];
						selected = true;
					}
					else if (i<9 && !map.grilleJoueur[i+1][j].touche && !map.grilleJoueur[i+1][j].plouf) {
						c = map.grilleJoueur[i+1][j];
						selected = true;
					}
					else if (j>0 && !map.grilleJoueur[i][j-1].touche && !map.grilleJoueur[i][j-1].plouf) {
						c = map.grilleJoueur[i][j-1];
						selected = true;
					}
					else if (j<9 && !map.grilleJoueur[i][j+1].touche && !map.grilleJoueur[i][j+1].plouf) {
						c = map.grilleJoueur[i][j+1];
						selected = true;
					}
				}
			}
		
		// Sinon choisir aléatoirement
		if (!selected) {
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);
			c = map.grilleJoueur[x][y];
		}
			
		map.infligerDegat(c);
		
	}

}
