package Game;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import fr.ul.cad.team7.bataillenavale.model.Bot;
import fr.ul.cad.team7.bataillenavale.model.strategie.Aleatoire;
import map.Case;
import map.Current;
import map.Map;

public abstract class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map map;
	Bot bot = new Bot(new Aleatoire());
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
