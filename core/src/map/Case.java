package map;

import java.io.Serializable;

public class Case implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int zone;
	public Boat boat;
	public boolean reveal = false;
	public boolean plouf = false;
	public boolean sens;
	public boolean selected = false;

	public Case(Boat b, int n, boolean s) {
		boat = b;
		zone = n;
		sens = s;
	}

	public Case() {
		boat = null;
		zone = 0;
		reveal = false;
	}

	public String toString() {

		// if (zone > boat.vie.length)
		// return zone + "?";

		if (boat == null) {
			return -1 + "";
		}
		return boat.vie[Math.abs(zone)] + "";
	}
}
