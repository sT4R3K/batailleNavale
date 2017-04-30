package gestionSauvegarde;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import IA.Aleatoire;
import IA.Bot;
import IA.Croix;
import map.Case;
import map.Current;
import map.Map;

public abstract class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map map;
	Bot bot = new Bot(new Croix());
	public boolean placement = true;

	public static int tour = 0;

	public Game() {
		map = new Map();
	}

	public abstract void gameLoop();

	public abstract void tourJoueur();

	public void tourOrdinateur() {
		bot.executeStrategie(map);
		map.tourJoueur = true;
	}
}
