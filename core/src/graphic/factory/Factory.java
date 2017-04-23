package graphic.factory;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import map.Case;

public class Factory {

	private static volatile Factory instance = null;

	public static Texture defaut = new Texture("badlogic.jpg");
	public static Texture blue = new Texture("bleu.jpg");
	public static Texture vert = new Texture("vert.jpeg");
	public static Texture gris = new Texture("gris.jpg");
	public static Texture select = new Texture("select.png");
	public static Texture shield = new Texture("shield.png");
	public static Texture croix = new Texture("croix.png");
	public static Texture barre = new Texture("barre.png");
	public static Texture explosion = new Texture("explosion.png");
	public static Sprite selected = new Sprite(select);
	public static Sprite selected2 = new Sprite(select);
	public static Sprite brouillard = new Sprite(gris);
	public static BitmapFont font = new BitmapFont();
	public static Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

	public static int width() {
		return Gdx.graphics.getWidth();
	}

	public static int height() {
		return Gdx.graphics.getHeight();
	}

	public static int largeur() {
		return width() / 21;
	}

	public static int hauteur() {
		return height() / 21;
	}

	public static int bordureTB() {
		return height() / 100;
	}

	public static int bordureLR() {

		return width() / 100;
	}

	public static void sens(ArrayList<Sprite> spr, Case c) {
		if (c != null)
			if (c.sens) {
				for (Sprite s : spr) {
					s.rotate(90);
					// s.setPosition(s.getX(), s.getY());
					// s.setSize(100,100);
				}
			}
	}

	public static void isSelect(ArrayList<Sprite> spr, Case c) {
		if (c.selected)
			spr.add(new Sprite(Factory.selected2));
	}

	public static ArrayList<Sprite> bateau(Case c) {

		if (c == null) {
			ArrayList<Sprite> spr = new ArrayList<Sprite>();
			spr.add(new Sprite(gris));
			isSelect(spr, c);
			// sens(spr, c);
			return spr;
		}

		if (c.reveal != true) {
			ArrayList<Sprite> spr = new ArrayList<Sprite>();
			spr.add(new Sprite(gris));
			isSelect(spr, c);
			// sens(spr, c);
			return spr;
		}

		if (Boolean.logicalAnd(c.reveal == true, c.boat == null)) {

			if (c.plouf) {
				ArrayList<Sprite> spr = new ArrayList<Sprite>();
				spr.add(new Sprite(blue));
				spr.add(new Sprite(croix));
				sens(spr, c);
				isSelect(spr, c);
				return spr;

			} else {
				ArrayList<Sprite> spr = new ArrayList<Sprite>();
				spr.add(new Sprite(blue));
				sens(spr, c);
				isSelect(spr, c);
				return spr;
			}
		}

		if (Boolean.logicalAnd(c.reveal == false, c.boat != null)) {
			ArrayList<Sprite> spr = new ArrayList<Sprite>();
			spr.add(new Sprite(vert));
			isSelect(spr, c);
			// sens(spr, c);
			return spr;
		}

		if (c.boat.vie.length < c.zone) {
			ArrayList<Sprite> spr = new ArrayList<Sprite>();
			spr.add(new Sprite(gris));
			isSelect(spr, c);
			// sens(spr, c);
			return spr;
		}

		ArrayList<Sprite> s = new ArrayList<Sprite>();
		if (c.zone < c.boat.vie.length)
			switch (c.boat.vie[Math.abs(c.zone)]) {
			case 2:
				s.add(new Sprite(blue));
				s.add(new Sprite(barre));
				s.add(new Sprite(shield));
				isSelect(s, c);
				// sens(s, c);
				return s;

			case 1:
				s.add(new Sprite(blue));
				s.add(new Sprite(barre));
				isSelect(s, c);
				// sens(s, c);
				return s;

			case 0:
				s.add(new Sprite(blue));
				s.add(new Sprite(barre));
				s.add(new Sprite(explosion));
				isSelect(s, c);
				// sens(s, c);
				return s;

			default:
				s.add(new Sprite(blue));
				isSelect(s, c);
				// sens(s, c);
				return s;
			}

		ArrayList<Sprite> spr = new ArrayList<Sprite>();
		spr.add(new Sprite(blue));
		isSelect(spr, c);
		// sens(spr, c);
		return spr;
	}

	public final static Factory getInstance() {
		if (Factory.instance == null) {
			synchronized (Factory.class) {
				if (Factory.instance == null) {
					Factory.instance = new Factory();
				}
			}
		}
		selected.setColor(Color.BLUE);
		selected2.setColor(Color.RED);
		return Factory.instance;
	}

	public Texture Bateau(Case grilleJoueur) {

		if (grilleJoueur == null) {
			return gris;
		}
		return blue;
	}
}
