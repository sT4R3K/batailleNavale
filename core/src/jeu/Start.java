package jeu;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import gestionSauvegarde.SaveManager;
import graphic.Clavier;
import graphic.factory.Factory;

public class Start extends ApplicationAdapter {

	TextButton rapide;
	TextButton perso;
	TextButton reprendre;
	TextButton sauvegarde;
	TextButton chargement;
	TextButton quitter;
	TextButton exitGame;
	TextButton retour;
	TextButton validerSave;
	TextField field;

	ArrayList<TextButton> alt;

	ArrayList<TextButton> save;

	SpriteBatch batch;
	Stage stage;
	ScreenViewport vp;
	public static BatailleNavale app;

	Clavier clavier;

	int cas = -1;

	@Override
	public void create() {
		alt = new ArrayList<TextButton>();
		save = new ArrayList<TextButton>();
		batch = new SpriteBatch();
		vp = new ScreenViewport();
		stage = new Stage(vp, batch);

		rapide = bouton(new normal(), "Partie Rapide");
		perso = bouton(new perso(), "Partie Perso");
		reprendre = bouton(new normal(), "Reprendre");
		sauvegarde = bouton(new save(), "Sauvegarder");
		chargement = bouton(new charger(), "Charger");

		field = new TextField("nom sauvegarde", Factory.skin);
		field.setOrigin(0, 0);
		stage.addActor(field);
		validerSave = bouton(new saveVal(), "Sauvegarder la partie");
		retour = bouton(new retour(), "Retour");
		exitGame = bouton(new quitterGame(), "Quitter la partie");
		quitter = bouton(new quitter(), "Quitter");

		field.setVisible(false);

		Gdx.input.setInputProcessor(stage);
	}

	public TextButton bouton(ClickListener clic, String nom) {

		TextButton but = new TextButton(nom, Factory.skin);
		but.setOrigin(0, 0);
		stage.addActor(but);
		alt.add(but);
		but.addListener(clic);
		but.setVisible(false);
		return but;
	}

	void maj() {
		vp.update(Factory.width(), Factory.height(), true);
		Gdx.input.setInputProcessor(stage);
		for (TextButton b : alt) {
			bsize(b);
			posBB(b);
		}
		aidePositionnement = 1;

		Factory.font.getData().setScale(Factory.width() / 500);

	}

	public void bsize(TextButton b) {
		int rap = Factory.largeur() / 3 * b.getText().length();
		b.setSize(rap, b.getHeight());
	}

	int aidePositionnement = 1;

	public void posBB(TextButton b) {
		if (b.isVisible()) {
			int rap = Factory.largeur() / 3 * b.getText().length();
			b.setPosition((Factory.width() / 2) - rap / 2,
					(float) ((Factory.height() / 1.5) - Factory.hauteur() * 2 * aidePositionnement));
			aidePositionnement++;
		}

	}

	public void visible(Button... T) {
		for (Button b : T) {
			b.setVisible(true);
		}
	}

	public void cas(Button... T) {
		hide();
		visible(T);
		batch.end();
		stage.draw();
		batch.begin();
	}

	public void interaction() {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			if (app != null) {
				cas = 1;
				Gdx.input.setCursorCatched(false);
			} else {
			}
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
			cas(reprendre, sauvegarde, chargement, exitGame, quitter);
			break;

		case 2:
			app.render();
			fin();
			break;

		case 3:
			Gdx.input.setCursorCatched(false);
			sauvegarde();
			break;
		case 4:
			Gdx.input.setCursorCatched(false);
			charge();
			break;
		case 5:
			Gdx.input.setCursorCatched(false);
			finGame();
			break;

		default:
			cas(rapide, perso, chargement, quitter);
			;
		}
		batch.end();
	}

	public void fin() {
		if (app != null)
			if (app.gear != null)
				if (app.gear.map.verifierFinPartie() != 0) {
					cas = 5;
				}
	}

	public void finGame() {

		cas(exitGame);
		if (app.gear.map.verifierFinPartie() == 1) {
			Factory.font.setColor(Color.RED);
			Factory.font.draw(batch, "You Lose !", Factory.width() / 2, Factory.height() / 2);
		}
		if (app.gear.map.verifierFinPartie() == 2) {
			Factory.font.setColor(Color.BLACK);
			Factory.font.draw(batch, "You Win !", Factory.width() / 2, Factory.height() / 2);
		}
	}

	public void hide() {
		for (TextButton b : alt) {
			b.setVisible(false);
		}
		for (TextButton b : save) {
			b.setVisible(false);
		}
	}

	public void charge() {
		for (TextButton i : save) {
			this.bsize(i);
			this.posBB(i);
		}
		visible(validerSave, retour);
		field.setVisible(true);
		field.getText();
		field.setPosition(50, 50);
		int rap = Factory.largeur() / 3 * field.getText().length();
		field.setSize(rap, field.getHeight());
		batch.end();
		stage.draw();
		batch.begin();
	}

	public void sauvegarde() {
		for (TextButton i : save) {
			this.bsize(i);
			this.posBB(i);
		}
		visible(retour);
		batch.end();
		stage.draw();
		batch.begin();
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
	}

	public class normal extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			System.out.println("bidule");
			if (app == null) {
				app = new BatailleNavale();
				app.create();
			}
			cas = 2;
		}
	};

	public class quitter extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			System.exit(0);
		}
	};

	public class retour extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			cas = 0;
		}
	};

	public class quitterGame extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			cas = 0;
			app = null;
		}
	};

	public class perso extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			System.out.println("bidule");
			if (app == null) {
				app = new BatailleNavale();
				app.createPerso();
			}
			cas = 2;
		}
	};

	public class save extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {

			hide();
			cas = 4;
			field.getText().toString();
			File repertoire = new File("sauvegarde");
			String[] listefichiers;

			int i;
			listefichiers = repertoire.list();
			for (i = 0; i < listefichiers.length; i++) {
				final TextButton but = new TextButton(listefichiers[i], Factory.skin);
				but.setOrigin(0, 0);
				stage.addActor(but);
				save.add(but);
				but.addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						SaveManager.saveToFile(app.gear, field.getText().toString());
						cas = 1;
						field.setVisible(false);
					}
				});
			}

		};
	}

	public class saveVal extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			app.gear.map.sauvegarde = true;
			SaveManager.saveToFile(app.gear, field.getText().toString());
			field.setVisible(false);
			cas = 1;
		};
	}

	public class charger extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			hide();
			cas = 3;

			File repertoire = new File("sauvegarde");
			String[] listefichiers;

			int i;
			listefichiers = repertoire.list();
			for (i = 0; i < listefichiers.length; i++) {
				final TextButton but = new TextButton(listefichiers[i], Factory.skin);
				but.setOrigin(0, 0);
				stage.addActor(but);
				save.add(but);
				but.addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						but.getText();
						app = new BatailleNavale();
						app.create();
						app.gear.map = SaveManager.loadFromFile(but.getText().toString()).map;
						cas = 2;
					}
				});
			}
		}
	}

};
