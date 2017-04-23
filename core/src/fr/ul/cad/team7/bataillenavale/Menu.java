package fr.ul.cad.team7.bataillenavale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import graphic.Clavier;
import graphic.factory.Factory;

public class Menu extends ApplicationAdapter {

	TextButton rapide;
	TextButton perso;
	SpriteBatch batch;
	Stage stage;
	ScreenViewport vp;
	ApplicationAdapter app;

	Clavier clavier;
	int cas = 0;

	@Override
	public void create() {
		batch = new SpriteBatch();
		vp = new ScreenViewport();
		stage = new Stage(vp, batch);
		
		rapide = new TextButton("Partie Rapide", Factory.skin);
		perso = new TextButton("Partie Perso", Factory.skin);
		rapide.setOrigin(0, 0);
		perso.setOrigin(0, 0);
		rapide.setPosition((Factory.width() / 2) - Factory.largeur() * 2, (Factory.height() / 2) + Factory.hauteur());
		perso.setPosition((Factory.width() / 2) - Factory.largeur() * 2, (Factory.height() / 2) - Factory.hauteur());
		rapide.setSize(Factory.largeur() * 4, Factory.hauteur() * 2);
		perso.setSize(Factory.largeur() * 4, Factory.hauteur() * 2);
		stage.addActor(rapide);
		stage.addActor(perso);

		rapide.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (app == null) {
					app = new BatailleNavale();
					app.create();
				}

				cas = 1;
			}
		});

		perso.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				app = new BatailleNavale();
				app.create();
				
			}
		});

		Gdx.input.setInputProcessor(stage);

		clavier = new Clavier();
	}

	void maj() {
		vp.update(Factory.width(), Factory.height(), true);
		Gdx.input.setInputProcessor(stage);
		rapide.setOrigin(0, 0);
		perso.setOrigin(0, 0);
		rapide.setPosition((Factory.width() / 2) - Factory.largeur() * 2, (Factory.height() / 2) + Factory.hauteur());
		perso.setPosition((Factory.width() / 2) - Factory.largeur() * 2, (Factory.height() / 2) - Factory.hauteur());
		rapide.setSize(Factory.largeur() * 4, Factory.hauteur() * 2);
		perso.setSize(Factory.largeur() * 4, Factory.hauteur() * 2);
	}

	public void interaction() {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			cas = 0;
			Gdx.input.setCursorCatched(false);
		}
	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 1, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		maj();
		interaction();
		batch.begin();
		switch (cas) {
		case 1:
			app.render();
			break;

		default:
			batch.end();
			stage.draw();
			batch.begin();
			;
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
	}
}
