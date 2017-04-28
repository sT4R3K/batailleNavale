package fr.ul.cad.team7.bataillenavale.desktop;

import java.io.Serializable;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.ul.cad.team7.bataillenavale.Start;

public class DesktopLauncher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// new LwjglApplication(new BatailleNavale(), config);
		new LwjglApplication(new Start(), config);

		// Game g = new SimpleGame();

		// SaveManager.saveToFile(g);

		// Game gg = SaveManager.loadFromFile();

		// Boat b = new Current(5);

		// gg.map.placement(0, 0, true, b, false);

		// gg.gameLoop();

		// Game g = SaveManager.loadFromFile();
		// g.map.afficherJoueur();
		//
		// g.gameLoop();
		// System.out.println("fin");

	}
}
