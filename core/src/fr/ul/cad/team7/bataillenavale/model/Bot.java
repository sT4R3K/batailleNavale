package fr.ul.cad.team7.bataillenavale.model;

import fr.ul.cad.team7.bataillenavale.model.strategie.Strategie;

public class Bot {
	private Strategie strategie;
	
	public Bot (Strategie strategie) {
		this.strategie = strategie;
	}
	
	public int [] executeStrategie () {
		return strategie.doComputePosion();
	}
}
