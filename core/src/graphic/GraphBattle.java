package graphic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import graphic.factory.Factory;
import jeu.Game;
import map.Boat;
import map.Case;

public abstract class GraphBattle extends Entity {

	Button[][] but = new Button[10][10];
	int larg = Factory.largeur();
	int haut = Factory.hauteur();

	int bordl = Factory.bordureLR();
	int bordh = Factory.bordureTB();

	int tmp = 0;
	int pos = 0;

	Button butt = new Button();
	Souris sou;

	Sprite[][] sprite;
	Sprite[][] spriteBot;
	ArrayList<Sprite> tuto;
	Rectangle[][] box;

	TextButton tir;
	TextButton sens;

	// gear.map.sens = !gear.map.sens;
	void maj() {
		larg = Factory.largeur();
		haut = Factory.hauteur();
		bordl = Factory.bordureLR();
		bordh = Factory.bordureTB();
	}

	public boolean delay() {
		if (tmp > 0) {
			tmp--;
			return true;
		}
		return false;
	}

	public void hud() {
		sens.setVisible(false);
		tir.setVisible(true);
		vp.update(Factory.width(), Factory.height(), true);
		stage.setViewport(vp);
		tir.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
		tir.setSize((Factory.largeur() / 2) * tir.getText().toString().length(), Factory.hauteur());
		batch.end();
		stage.draw();
		batch.begin();
	}

	public void hud2() {
		tir.setVisible(false);
		sens.setVisible(true);
		vp.update(Factory.width(), Factory.height(), true);
		stage.setViewport(vp);
		sens.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
		sens.setSize((Factory.largeur() / 2) * tir.getText().toString().length(), Factory.hauteur());
		batch.end();
		stage.draw();
		batch.begin();
	}

	void recouvrement(Sprite spr, int x, int y, boolean sens) {
		if (gear.map.joueur.size() > pos) {
			gear.map.put = new Case(gear.map.joueur.get(pos), 0, false);
			for (int i = 0; i < gear.map.put.boat.vie.length; i++) {
				if (spr.getBoundingRectangle().overlaps(Souris.curseur)) {
					Sprite s = new Sprite(Factory.vert);
					s.setOrigin(spr.getOriginX(), spr.getOriginY());
					if (sens) {
						s.setPosition(spr.getX() + (i * Factory.largeur()), spr.getY());
					} else {
						s.setPosition(spr.getX(), spr.getY() + (i * Factory.hauteur()));
					}
					if (Gdx.input.isTouched()) {
						Souris.err = gear.map.placement(x, y, !sens, gear.map.put.boat, !false);
						int err = Souris.err;
						if (err != 0) {
						}
					}
					s.setSize(spr.getWidth(), spr.getHeight());
					s.draw(batch);
				}
			}
		}
	}

	public void instal() {
		for (int x = 0; x < 10; x++)
			for (int y = 0; y < 10; y++) {
				recouvrement(sprite[x][y], x, y, gear.map.sens);
				if (gear.map.joueur.size() == 0) {
					gear.placement = false;
				}
			}
	}

	void souris() {
		for (int x = 0; x < 10; x++)
			for (int y = 0; y < 10; y++) {
				recouvrement(sprite[x][y], x, y, gear.map.sens);
				if (sprite[x][y].getBoundingRectangle().overlaps(Souris.curseur)) {
					if (Gdx.input.isTouched(0)) {
						if (gear.map.selection != null)
							gear.map.selection.selected = false;
						gear.map.selection = gear.map.grilleJoueur[x][y];
						gear.map.selection.selected = true;
					}
				}
				if (spriteBot[x][y].getBoundingRectangle().overlaps(Souris.curseur)) {
					if (Gdx.input.isTouched(0)) {
						if (gear.map.selection != null)
							gear.map.selection.selected = false;
						gear.map.selection = gear.map.grilleBot[x][y];
						gear.map.selection.selected = true;
					}
				}
			}
	}

	public GraphBattle(SpriteBatch sb, Stage stage, Viewport vp, Game game, OrthographicCamera camera) {
		super(sb, stage, vp, game, camera);
		butt = new Button(Factory.skin);
		butt.setPosition(0, 0);
		sprite = new Sprite[10][10];
		spriteBot = new Sprite[10][10];
		box = new Rectangle[10][10];
		for (int x = 0; x < 10; x++)
			for (int y = 0; y < 10; y++) {
				sprite[x][y] = new Sprite();
				spriteBot[x][y] = new Sprite();
				box[x][y] = new Rectangle();
			}
		sou = new Souris(sb, stage, vp, game, camera);
		tir = new TextButton("Boom !", Factory.skin);
		sens = new TextButton("Sens Bateau", Factory.skin);
		tir.setOrigin(0, 0);
		sens.setOrigin(0, 0);
		tir.setPosition(Factory.largeur() * 3, (Factory.height()) - Factory.hauteur());
		sens.setPosition(Factory.largeur() * 3, (Factory.height()) - Factory.hauteur());
		tir.setSize((Factory.largeur() / 2) * tir.getText().toString().length(), Factory.hauteur());
		stage.addActor(tir);
		stage.addActor(sens);
		tir.addListener(new feu());
		sens.addListener(new sens());
		Gdx.input.setInputProcessor(stage);
		creerTuto();
	}

	void creerTuto() {
		tuto = new ArrayList<Sprite>();
		tuto.add(Factory.brouillard);
		tuto.add(new Sprite(Factory.blue));
		tuto.add(new Sprite(Factory.barre));
	}

	public void sup(ArrayList<Sprite> spr, int x, int y) {
		for (Sprite s : spr) {
			sprite[x][y] = s;
			sprite[x][y].setOrigin(0, 0);
			sprite[x][y].setPosition((x * larg) + bordl, (y * haut) + bordh);
			sprite[x][y].setSize(larg, haut);
			sprite[x][y].draw(batch);
		}
	}

	public void supp(ArrayList<Sprite> spr, int x, int y) {
		int decal = Factory.width() / 2;
		for (Sprite s : spr) {
			spriteBot[x][y] = s;
			spriteBot[x][y].setOrigin(0, 0);
			spriteBot[x][y].setPosition(decal + (x * larg) + bordl, (y * haut) + bordh);
			spriteBot[x][y].setSize(larg, haut);
			spriteBot[x][y].draw(batch);
		}
	}

	public void selection() {
		Factory.font.setColor(Color.RED);
		if (gear.map.selection != null) {
			Factory.font.draw(batch, gear.map.selection.zone + "", Factory.width() / 2, Factory.height() / 2);
		} else {
			Factory.font.draw(batch, "choississez une case", Factory.width() / 2, Factory.height() / 2);
		}
	}

	public void placement() {
		int pos = 0;
		int pol = 0;
		for (Boat e : gear.map.joueur) {
			pos = 0;
			for (int i = 0; i < e.vie.length; i++) {
				Case c = new Case(e, 0, false);
				c.reveal = true;
				for (Sprite p : Factory.bateau(c)) {
					Sprite fac = p;
					fac.setOrigin(0, 0);
					fac.setPosition(this.bordl + (larg * pos), (Factory.height() / 2) + (haut * pol));
					fac.setSize(larg, haut);
					fac.draw(batch);
				}
				pos++;

			}
			pol++;
		}
	}

	public void joueur() {
		maj();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (gear.map.grilleJoueur[i][j] != null)
					gear.map.grilleJoueur[i][j].reveal = true;
				sup(Factory.bateau(gear.map.grilleJoueur[i][j]), i, j);
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				supp(Factory.bateau(gear.map.grilleBot[i][j]), i, j);
			}
		}
		souris();
	}

	public void ordi() {
		maj();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (gear.map.grilleJoueur[i][j] != null)
					gear.map.grilleJoueur[i][j].reveal = true;
				sup(Factory.bateau(gear.map.grilleJoueur[i][j]), i, j);
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				supp(Factory.bateau(gear.map.grilleBot[i][j]), i, j);
			}
		}
	}

	public void installation() {
		maj();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (gear.map.grilleJoueur[i][j] != null)
					gear.map.grilleJoueur[i][j].reveal = true;
				sup(Factory.bateau(gear.map.grilleJoueur[i][j]), i, j);
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				supp(Factory.bateau(gear.map.grilleBot[i][j]), i, j);
			}
		}
		instal();
		placement();
	}

	public void tuto() {

	}

	@Override
	public void render() {
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
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		sou.dispose();
	}

	// gear.map.sens = !gear.map.sens;

	public class feu extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {

			if (gear.map.selection != null) {
				gear.map.infligerDegat(gear.map.selection);
				gear.map.tourJoueur = false;
			}
		}
	};

	public class sens extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {

			gear.map.sens = !gear.map.sens;
		}
	};
}
