package map;

public class Old extends Boat {

	private static final long serialVersionUID = 1L;

	public Old(int nbCase) {
		super(nbCase);
		for (int i = 0; i < vie.length; i++) {
			vie[i] = 1;
		}
	}
}
