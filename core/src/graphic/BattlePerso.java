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
import graphic.Battle.alea;
import graphic.Battle.curr;
import graphic.Battle.diff;
import graphic.Battle.old;
import graphic.factory.Factory;
import jeu.Game;
import map.Boat;

public class BattlePerso extends GraphBattle {

	TextButton incJ, incB;
	TextButton decJ, decB;

	TextButton un, deux, trois, quatre, cinq;

	int limitJ = 20;
	int limitB = 20;

	TextButton setOld;
	TextButton setCurrent;

	TextButton setAlea;
	TextButton setDiff;

	TextButton next;

	boolean epoque = false;
	boolean diff = false;

	boolean suivant = false;

	public BattlePerso(SpriteBatch sb, Stage stage, Viewport vp, Game game, OrthographicCamera camera) {
		super(sb, stage, vp, game, camera);
		// TODO Auto-generated constructor stub
		setOld = new TextButton("Ancien monde", Factory.skin);
		setCurrent = new TextButton("Nouveau monde", Factory.skin);
		setAlea = new TextButton("Facile", Factory.skin);
		setDiff = new TextButton("Difficile", Factory.skin);
		incJ = new TextButton("+", Factory.skin);
		incB = new TextButton("+", Factory.skin);
		decJ = new TextButton("-", Factory.skin);
		decB = new TextButton("-", Factory.skin);
		next = new TextButton("suivant", Factory.skin);

		setOld.addListener(new old());
		setCurrent.addListener(new curr());
		setAlea.addListener(new alea());
		setDiff.addListener(new diff());
		incJ.addListener(new incj());
		incB.addListener(new incb());
		decJ.addListener(new decj());
		decB.addListener(new decb());
		next.addListener(new next());

		stage.addActor(setOld);
		stage.addActor(setCurrent);
		stage.addActor(setAlea);
		stage.addActor(setDiff);

		stage.addActor(next);
		stage.addActor(incJ);
		stage.addActor(incB);
		stage.addActor(decJ);
		stage.addActor(decB);

		for (Actor a : stage.getActors()) {
			a.setVisible(false);
		}
	}

	void personnal() {

		incJ.setVisible(true);
		incB.setVisible(true);
		decJ.setVisible(true);
		decB.setVisible(true);
		next.setVisible(true);

		incJ.setSize(incJ.getText().toString().length() * (Factory.largeur()), Factory.hauteur());
		incJ.setPosition(stage.getWidth() / 5, stage.getHeight() * 3 / 4);

		incB.setSize(incB.getText().toString().length() * (Factory.largeur()), Factory.hauteur());
		incB.setPosition(stage.getWidth() * 3 / 5, stage.getHeight() * 3 / 4);

		decJ.setSize(decJ.getText().toString().length() * (Factory.largeur()), Factory.hauteur());
		decJ.setPosition(stage.getWidth() / 5, stage.getHeight() * 2 / 4);

		decB.setSize(decB.getText().toString().length() * (Factory.largeur()), Factory.hauteur());
		decB.setPosition(stage.getWidth() * 3 / 5, stage.getHeight() * 2 / 4);

		next.setSize(next.getText().toString().length() * (Factory.largeur() / 2), Factory.hauteur());
		next.setPosition(stage.getWidth() / 2 - next.getWidth() / 2, stage.getHeight() / 5);

		stage.getBatch().begin();
		Factory.font.draw(stage.getBatch(), "Nombre case joueur " + limitJ + "/30", stage.getWidth() / 5,
				stage.getHeight() * 3 / 4);
		Factory.font.draw(stage.getBatch(), "Nombre case adversaire " + limitB + "/30", stage.getWidth() / 2,
				stage.getHeight() * 3 / 4);
		stage.getBatch().end();
		stage.draw();

	}

	public void render() {

		vp.update(Factory.width(), Factory.height(), true);

		tuto();

		vp.update(Factory.width(), Factory.height());

		if (suivant) {
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
					// set(setAlea, setDiff);
				}
			} else {
				// set(setOld, setCurrent);
			}
		} else {
			personnal();
		}
		load();
	}

	public void creation() {

	}

	public class incj extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// System.out.println("j+");
			limitJ++;
			if (limitJ > 30) {
				limitJ = 30;
			}
		}
	}

	public class incb extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// System.out.println("b+");
			limitB++;
			if (limitB > 30) {
				limitB = 30;
			}
		}
	}

	public class decj extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// System.out.println("j-");
			limitJ--;
			if (limitJ < 5) {
				limitJ = 5;
			}
		}
	}

	public class decb extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			// System.out.println("b-");
			limitB--;
			if (limitB < 5) {
				limitB = 5;
			}
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

	public class next extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			suivant = true;
		}
	}
}
