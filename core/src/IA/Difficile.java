package IA;

import java.io.Serializable;

import map.Case;
import map.Map;

public class Difficile implements Strategie, Serializable  {

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
		
		// L'IA a une chance sur deux de toucher un bateau
		if (r<0.5) {
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);
			
			c = map.grilleJoueur[x][y];
			
			while(true) {
				if (c.boat != null && c.coule == false) {
					break;
				}
				
				x = (int) (Math.random() * 10);
				y = (int) (Math.random() * 10);
				
				c = map.grilleJoueur[x][y];
			}
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
