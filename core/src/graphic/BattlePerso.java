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
import IA.Croix;
import IA.Difficile;
import graphic.Battle.alea;
import graphic.Battle.croix;
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
	TextButton setCroix;

	TextButton next;
	TextButton suite;

	boolean epoque = false;
	boolean diff = false;

	boolean suivant = false;

	boolean go = false;

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
		suite = new TextButton("suivant", Factory.skin);
		setCroix = new TextButton("Normale", Factory.skin);

		un = new TextButton("Normale", Factory.skin);
		deux = new TextButton("deux", Factory.skin);
		trois = new TextButton("trois", Factory.skin);
		quatre = new TextButton("quatre", Factory.skin);
		cinq = new TextButton("cinq", Factory.skin);

		setCroix.addListener(new croix());

		setOld.addListener(new old());
		setCurrent.addListener(new curr());
		setAlea.addListener(new alea());
		setDiff.addListener(new diff());
		incJ.addListener(new incj());
		incB.addListener(new incb());
		decJ.addListener(new decj());
		decB.addListener(new decb());
		next.addListener(new next());
		suite.addListener(new go());

		un.addListener(new un());
		deux.addListener(new deux());
		trois.addListener(new trois());
		quatre.addListener(new quatre());
		cinq.addListener(new cinq());

		// stage.addActor(un);
		stage.addActor(deux);
		stage.addActor(trois);
		stage.addActor(quatre);
		stage.addActor(cinq);

		stage.addActor(setOld);
		stage.addActor(setCurrent);
		stage.addActor(setAlea);
		stage.addActor(setDiff);

		stage.addActor(setCroix);

		stage.addActor(next);
		stage.addActor(incJ);
		stage.addActor(incB);
		stage.addActor(decJ);
		stage.addActor(decB);
		stage.addActor(suite);

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

		stage.getBatch().begin();
		Factory.font.draw(stage.getBatch(), "Selectionner le nombre de cases composant vos navires",
				stage.getWidth() / 8, stage.getHeight() / 3);
		stage.getBatch().end();

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

	void azerty() {

		stage.getBatch().begin();
		Factory.font.draw(stage.getBatch(), "Selectionner les navires composants votre flotte "+gear.map.nbCasesJoueur+" case restantes",
				stage.getWidth() / 8, stage.getHeight() / 3);
		stage.getBatch().end();

		for (Actor a : stage.getActors()) {
			a.setVisible(false);
		}

		if (gear.map.joueur.size() > 0)
			suite.setVisible(true);

		if (gear.map.nbCasesJoueur > 4) {
			cinq.setVisible(true);
		}
		if (gear.map.nbCasesJoueur > 3) {
			quatre.setVisible(true);
		}
		if (gear.map.nbCasesJoueur > 2) {
			trois.setVisible(true);
		}
		if (gear.map.nbCasesJoueur > 1) {
			deux.setVisible(true);
		}

		deux.setSize(deux.getText().toString().length() * (Factory.largeur() / 2), Factory.hauteur());
		deux.setPosition(stage.getWidth() / 2 - next.getWidth() / 2, stage.getHeight() * 5 / 6);

		trois.setSize(trois.getText().toString().length() * (Factory.largeur() / 2), Factory.hauteur());
		trois.setPosition(stage.getWidth() / 2 - next.getWidth() / 2, stage.getHeight() * 4 / 6);

		quatre.setSize(quatre.getText().toString().length() * (Factory.largeur() / 2), Factory.hauteur());
		quatre.setPosition(stage.getWidth() / 2 - next.getWidth() / 2, stage.getHeight() * 3 / 6);

		cinq.setSize(cinq.getText().toString().length() * (Factory.largeur() / 2), Factory.hauteur());
		cinq.setPosition(stage.getWidth() / 2 - next.getWidth() / 2, stage.getHeight() * 2 / 6);

		suite.setSize(suite.getText().toString().length() * (Factory.largeur() / 2), Factory.hauteur());
		suite.setPosition(stage.getWidth() / 2 - next.getWidth() / 2, stage.getHeight() / 6);
		stage.draw();
	}

	void set(TextButton but, TextButton butt, TextButton buttt) {

		but.setVisible(true);
		butt.setVisible(true);
		buttt.setVisible(true);

		but.setSize(but.getText().toString().length() * (Factory.largeur() / 3), Factory.hauteur() * 2);
		butt.setSize(butt.getText().toString().length() * (Factory.largeur() / 3), Factory.hauteur() * 2);
		buttt.setSize(butt.getText().toString().length() * (Factory.largeur() / 3), Factory.hauteur() * 2);

		but.setPosition((stage.getWidth() / 2) - but.getWidth() / 2, stage.getHeight() / 2 + 2 * Factory.hauteur());
		butt.setPosition((stage.getWidth() / 2) - butt.getWidth() / 2, stage.getHeight() / 2);
		buttt.setPosition((stage.getWidth() / 2) - butt.getWidth() / 2, stage.getHeight() / 2 - 2 * Factory.hauteur());

		stage.draw();

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

		vp.update(Factory.width(), Factory.height(), true);

		tuto();

		vp.update(Factory.width(), Factory.height());

		if (suivant) {
			if (go) {
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
						set(setAlea, setCroix, setDiff);
					}
				} else {
					set(setOld, setCurrent);
				}
			} else {
				azerty();
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
			setCroix.setVisible(false);
		}
	}

	public class diff extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.bot = new Bot(new Difficile());
			setAlea.setVisible(false);
			setDiff.setVisible(false);
			setCroix.setVisible(false);
			diff = true;
		}
	}

	public class next extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.map.nbCasesJoueur = limitJ;
			gear.map.nbCasesBot = limitB;
			suivant = true;
		}
	}

	public class go extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			next.setVisible(false);
			suite.setVisible(false);
			go = true;
			gear.map.ajouterBotRand();
		}
	}

	public class croix extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.bot = new Bot(new Croix());
			setAlea.setVisible(false);
			setCroix.setVisible(false);
			setDiff.setVisible(false);
			diff = true;
		}
	}

	public class un extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.map.ajouter(Boat.factory(1, 1));
		}
	}

	public class deux extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.map.ajouter(Boat.factory(1, 2));
		}
	}

	public class trois extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.map.ajouter(Boat.factory(1, 3));
		}
	}

	public class quatre extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.map.ajouter(Boat.factory(1, 4));
		}
	}

	public class cinq extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			gear.map.ajouter(Boat.factory(1, 5));
		}
	}
}
