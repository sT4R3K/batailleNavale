package Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveManager {

	public static void saveToFile(Game g) {
		try {
			FileOutputStream fos = new FileOutputStream("sauvegarde/save.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(g);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveToFile(Game g, String nom) {
		try {
			FileOutputStream fos = new FileOutputStream("sauvegarde/" + nom);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(g);
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	public static Game loadFromFile() {

		Game g = null;
		try {
			FileInputStream fis = new FileInputStream("sauvegarde/save.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			g = (Game) ois.readObject();
			ois.close();
		}

		catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}

	public static Game loadFromFile(String s) {

		Game g = null;
		try {
			FileInputStream fis = new FileInputStream("sauvegarde/" + s);
			ObjectInputStream ois = new ObjectInputStream(fis);
			g = (Game) ois.readObject();
			ois.close();
		}

		catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}

}
