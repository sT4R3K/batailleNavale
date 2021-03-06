package jeu;

import java.io.Serializable;

import IA.*;

import map.Map;

public abstract class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map map;
	public Bot bot = new Bot(new SearchAndSink());
	public boolean placement = true;
	public boolean endCreation = false;;

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
