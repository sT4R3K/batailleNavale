package fr.ul.cad.team7.bataillenavale.model;


public class FileManager {
	private static FileManager instance =  new FileManager ();
	
	private FileManager () {}
	
	public static FileManager getInstance () {
		return instance;
	}
	
	void saveToFile (String file) {
		
	}
	
	void loadFromFile (String file) {
		
	}

}
