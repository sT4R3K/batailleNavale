package fr.ul.cad.team7.bataillenavale.model;

import java.io.Serializable;

import fr.ul.cad.team7.bataillenavale.model.strategie.Strategie;
import map.Map;

public class Bot implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Strategie strategie;

	public Bot(Strategie strategie) {
		this.strategie = strategie;
	}

	public void executeStrategie(Map m) {
		strategie.degat(m);
	}
}
