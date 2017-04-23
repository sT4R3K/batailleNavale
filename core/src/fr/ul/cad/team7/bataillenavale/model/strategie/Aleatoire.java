package fr.ul.cad.team7.bataillenavale.model.strategie;

import java.io.Serializable;

import map.Case;
import map.Map;

public class Aleatoire implements Strategie, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int[] doComputePosion() {
		return null;
	}

	int tour = 0 ;
	@Override
	public void degat(Map m) {
		Map map = m;
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 10);
		// if (map.verifierTouche(x, y, true)) {
		Case c = map.grilleJoueur[x][y];

		while (true) {
			if (c.reveal == false) {
				break;
			}
			x = (int) (Math.random() * 10);
			y = (int) (Math.random() * 10);
			// if (map.verifierTouche(x, y, true)) {
			c = map.grilleJoueur[x][y];
			tour ++;
			if(tour>1000){
				tour = 0 ;
				break;
			}
				
		}

		map.infligerDegat(c);
		// }
	}
}