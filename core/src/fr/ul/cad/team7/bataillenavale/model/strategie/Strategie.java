package fr.ul.cad.team7.bataillenavale.model.strategie;

import map.Map;

public interface Strategie   {
	public int[] doComputePosion();

	public void degat(Map m);
}
