package IA;

import map.Case;
import map.Map;

public class Difficile implements Strategie {

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
		
		double r = Math.random();
		
		// L'IA a une chance sur trois de toucher un bateau
		if (r>0.33 && r<0.66) {
			
		}
		
		// Sinon choisir une case aléatoirement
		else {
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
