package map;

import java.io.Serializable;

public abstract class Boat implements Serializable {

	private static final long serialVersionUID = 1L;
	public int[] vie;

	public Boat(int nbCase) {
		vie = new int[nbCase];
	}

	public int size(){
		return vie.length;
	}	

	public void hit(int zone) {
		vie[zone]--;
	}

	public String toString() {
		return "bat ";
	}

	public void setEpoque(int i) {
		for (int z = 0; z < vie.length; z++) {
			vie[z] = i;
		}
	}

	public boolean isDead(){
		for(int i = 0; i < vie.length;i++){
			if(vie[i] > 0)
				return false;
		}
		return true;
	}
	
	public static Boat factory(int epoque, int taille) {
		switch (epoque) {

		case 2:
			Boat b = new Current(taille);
			return b;

		default:
			Boat bb = new Old(taille);
			return bb;

		}
	}
}
