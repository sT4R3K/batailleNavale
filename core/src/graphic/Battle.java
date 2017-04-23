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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import Game.Game;
import fr.ul.cad.team7.bataillenavale.BatailleNavale;
import graphic.factory.Factory;
import map.Boat;
import map.Case;

public class Battle extends Entity {

	Button[][] but = new Button[10][10];
	int larg = Factory.largeur();
	int haut = Factory.hauteur();

	int bordl = Factory.bordureLR();
	int bordh = Factory.bordureTB();

	Button butt = new Button();
	Souris sou;
	// boolean dir = true;

	Sprite[][] sprite;
	Sprite[][] spriteBot;
	Rectangle[][] box;

	TextButton tir;
	TextButton perso;

	void maj() {
		larg = Factory.largeur();
		haut = Factory.hauteur();

		bordl = Factory.bordureLR();
		bordh = Factory.bordureTB();
		// stage.act();
		// System.out.println(stage.getActors());
		// stage.getActors().first().;
	}

	int tmp = 0;

	public boolean delay() {
		if (tmp > 0) {
			tmp--;
			return true;
		}
		return false;
	}

	public void hud() {

		
		
		vp.update(Factory.width(), Factory.height(), true);
		stage.setViewport(vp);
		// tir.setOrigin(0, 0);
		tir.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
		tir.setSize(Factory.largeur(), Factory.hauteur());
		// tir.toFront();
		// tir.setVisible(true);
		// System.out.println(tir.getX() + " " + tir.getY());
		// tir.setPosition(Factory.largeur() * 3, (Factory.height()) -
		// Factory.hauteur());
		// tir.setSize(Factory.largeur() * 2, Factory.hauteur());
		// tir.addListener(new feu());
		batch.end();
		stage.draw();
		batch.begin();

	}

	int pos = 0;

	void recouvrement(Sprite spr, int x, int y, boolean sens) {
		if (map.joueur.size() > pos) {
			map.put = new Case(map.joueur.get(pos), 0, false);
			for (int i = 0; i < map.put.boat.vie.length; i++) {
				if (spr.getBoundingRectangle().overlaps(Souris.curseur)) {
					Sprite s = new Sprite(Factory.vert);
					s.setOrigin(spr.getOriginX(), spr.getOriginY());
					if (sens) {
						s.setPosition(spr.getX() + (i * Factory.largeur()), spr.getY());
					} else {
						s.setPosition(spr.getX(), spr.getY() + (i * Factory.hauteur()));
					}
					if (Gdx.input.isTouched()) {
						Souris.err = map.placement(x, y, !sens, map.put.boat, !false);
						int err = Souris.err;
						if (err != 0) {
							Factory.font.draw(batch, map.err(err), Factory.width() / 2,
									Factory.height() - Factory.hauteur());
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
				recouvrement(sprite[x][y], x, y, map.sens);
				if (map.joueur.size() == 0) {
					gear.placement = false;
				}
			}
	}

	void souris() {
		for (int x = 0; x < 10; x++)
			for (int y = 0; y < 10; y++) {
				recouvrement(sprite[x][y], x, y, map.sens);
				if (sprite[x][y].getBoundingRectangle().overlaps(Souris.curseur)) {
					if (Gdx.input.isTouched(0)) {
						if (map.selection != null)
							map.selection.selected = false;
						map.selection = map.grilleJoueur[x][y];
						map.selection.selected = true;
					}
				}
				if (spriteBot[x][y].getBoundingRectangle().overlaps(Souris.curseur)) {
					if (Gdx.input.isTouched(0)) {
						if (map.selection != null)
							map.selection.selected = false;
						map.selection = map.grilleBot[x][y];
						map.selection.selected = true;
					}
				}
			}
	}

	public Battle(SpriteBatch sb, Stage stage, Viewport vp, Game game, OrthographicCamera camera) {
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
		tir.setOrigin(0, 0);
		tir.setPosition(Factory.largeur() * 3, (Factory.height()) - Factory.hauteur());
		tir.setSize(Factory.largeur() * 2, Factory.hauteur());
		stage.addActor(tir);
		tir.addListener(new feu());

		Gdx.input.setInputProcessor(stage);

		// Gdx.input.setInputProcessor(stage);
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
		if (map.selection != null) {
			Factory.font.draw(batch, map.selection.zone + "", Factory.width() / 2, Factory.height() / 2);
		} else {
			Factory.font.draw(batch, "choississez une case", Factory.width() / 2, Factory.height() / 2);
		}
	}

	public void placement() {
		int pos = 0;
		int pol = 0;
		for (Boat e : map.joueur) {
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
				if (map.grilleJoueur[i][j] != null)
					map.grilleJoueur[i][j].reveal = true;
				sup(Factory.bateau(map.grilleJoueur[i][j]), i, j);
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				supp(Factory.bateau(map.grilleBot[i][j]), i, j);
			}
		}
		souris();
	}

	public void ordi() {
		maj();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (map.grilleJoueur[i][j] != null)
					map.grilleJoueur[i][j].reveal = true;

				sup(Factory.bateau(map.grilleJoueur[i][j]), i, j);
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				supp(Factory.bateau(map.grilleBot[i][j]), i, j);
			}
		}
	}

	public void installation() {
		maj();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map.grilleJoueur[i][j] != null)
					map.grilleJoueur[i][j].reveal = true;
				sup(Factory.bateau(map.grilleJoueur[i][j]), i, j);
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				supp(Factory.bateau(map.grilleBot[i][j]), i, j);
			}
		}
		instal();
		placement();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

		vp.update(Factory.width(), Factory.height());
		// Gdx.input.setInputProcessor(stage);
		if (!gear.placement) {
			hud();
			if (map.tourJoueur) {
				this.joueur();
				Gdx.input.setInputProcessor(stage);
				// this.hud();
			} else {
				gear.tourOrdinateur();
				this.ordi();
			}
		} else {
			this.installation();
		}
		// Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();

		sou.dispose();
		// img.dispose();
	}

	public class feu extends ClickListener {

		@Override
		public void clicked(InputEvent event, float x, float y) {

			if (map.selection != null) {
				map.infligerDegat(map.selection);
				map.tourJoueur = false;
			}
		}
	};
}