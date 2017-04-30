package jeu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Menu extends ApplicationAdapter {

	SpriteBatch batch;
	Stage stage;
	ScreenViewport vp;

	TextButton rapide;
	TextButton perso;
	TextButton sauvegarde;
	TextButton chargement;
	TextButton quitter;
	TextButton exitGame;

	public Menu(SpriteBatch batch, Stage stage, ScreenViewport vps) {
		this.batch = batch;
		this.stage = stage;
		this.vp = vps;

	}

}
