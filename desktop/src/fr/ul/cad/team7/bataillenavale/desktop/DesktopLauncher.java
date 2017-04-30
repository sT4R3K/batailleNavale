package fr.ul.cad.team7.bataillenavale.desktop;

import java.io.Serializable;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import jeu.Start;

public class DesktopLauncher implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Start(), config);
	}
}
