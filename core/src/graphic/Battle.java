package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import IA.Aleatoire;
import IA.Bot;
import IA.Difficile;
import graphic.factory.Factory;
import jeu.Game;
import map.Boat;

public class Battle extends GraphBattle {

	boolean epoque = false;
	boolean diff = false;

	TextButton setOld;
	TextButton setCurrent;

	TextButton setAlea;
	TextButton setDiff;

	public Battle(SpriteBatch sb, Stage stage, Viewport vp, Game game, OrthographicCamera camera) {
		super(sb, stage, vp, game, camera);
		// TODO Auto-generated constructor stub

		setOld = new TextButton("Ancien monde", Factory.skin);
		setCurrent = new TextButton("Nouveau monde", Factory.skin);
		setAlea = new TextButton("Facile", Factory.skin);
		setDiff = new TextButton("Difficile", Factory.skin);

		setOld.addListener(new old());
		setCurrent.addListener(new curr());
		setAlea.addListener(new alea());
		setDiff.addListener(new diff());

		stage.addActor(setOld);
		stage.addActor(setCurrent);
		stage.addActor(setAlea);
		stage.addActor(setDiff);

		for (Actor a : stage.getActors()) {
			a.setVisible(false);
		}

		Gdx.input.setInputProcessor(stage);
	}

	void set(TextButton but, TextButton butt) {

		but.setVisible(true);
		butt.setVisible(true);

		but.setSize(but.getText().toString().length() * (Factory.largeur() / 3), Factory.hauteur() * 2);
		butt.setSize(butt.getText().toString().length() * (Factory.largeur() / 3), Factory.hauteur() * 2);

		but.setPosition((stage.getWidth() / 2) - but.getWidth() / 2, stage.getHeight() / 2 + Factory.hauteur());
		butt.setPosition((stage.getWidth() / 2) - butt.getWidth() / 2, stage.getHeight() / 2 - Factory.hauteur());

		stage.draw();

	}

	public void render() {

		// Factory.font.draw(batch, "bsgnsfg", stage.getWidth() / 2,
		// stage.getHeight() / 2);

		if (gear.map.sauvegarde) {
			epoque = true;
			diff = true;
		}

		if (epoque) {
			if (diff) {
				Gdx.input.setCursorCatched(true);
				tuto();
				vp.update(Factory.width(), Factory.height());
				if (!gear.placement) {
					hud();
					if (gear.map.tourJoueur) {
						this.joueur();
						Gdx.input.setInputProcessor(stage);
					} else {
						gear.tourOrdinateur();
						this.ordi();
					}
				} else {
					this.installation();
					hud2();
				}
				load();
			} else {
				set(setAlea, setDiff);
			}
		} else {
			set(setOld, setCurrent);
		}
	}

	public class old extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {

			for (Boat b : gear.map.joueur) {
				b.setEpoque(1);
			}

			for (Boat b : gear.map.bot) {
				b.setEpoque(1);
			}
			epoque = true;
			setCurrent.setVisible(false);
			setOld.setVisible(false);
		}
	}

	public class curr extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			for (Boat b : gear.map.joueur) {
				b.setEpoque(2);
			}

			for (Boat b : gear.map.bot) {
				b.setEpoque(2);
			}
			epoque = true;
			setCurrent.setVisible(false);
			setOld.setVisible(false);
		}
	}

	public class alea extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.bot = new Bot(new Aleatoire());
			diff = true;
			setAlea.setVisible(false);
			setDiff.setVisible(false);
		}
	}

	public class diff extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.bot = new Bot(new Difficile());
			setAlea.setVisible(false);
			setDiff.setVisible(false);
			diff = true;
		}
	}
}
