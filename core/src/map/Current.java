package map;

public class Current extends Boat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Current(int nbCase) {
		super(nbCase);
		for (int i = 0; i < vie.length; i++) {
			vie[i] = 2;
		}
	}
}
