package graphic;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import Game.Game;
import graphic.factory.Factory;
import graphic.factory.Son;
import map.Boat;
import map.Current;
import map.Map;

public class Souris extends Entity {

	Vector3 touchPos = new Vector3();

	public static int err = 0;

	MouseWheelListener a = new MouseWheelListener() {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
		}
	};

	public static Rectangle curseur;

	public Souris(SpriteBatch sb, Stage stage, Viewport vp, Game map, OrthographicCamera camera) {
		super(sb, stage, vp, map, camera);
		touchPos = new Vector3();
		curseur = new Rectangle();
		curseur.setSize(1, 1);
		Pixmap pm = new Pixmap(Gdx.files.internal("select.png"));
		pm.dispose();

	}

	public int x() {

		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos);
		return (int) touchPos.x - Factory.largeur() / 2;
	}

	public int y() {
		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos);
		return (int) touchPos.y - Factory.hauteur() / 2;
	}

	int tmp = 0;

	public boolean delay() {
		if (tmp > 0) {
			tmp--;
			return true;
		}
		return false;
	}

	public void fenetre() {

		Vector3 pos = camera.position;

		// int right = (Factory.width() + Factory.largeur() / 2);
		// int left = Factory.largeur() / 2;
		// int top = (int) (pos.y + camera.viewportHeight / 2) -
		// Factory.hauteur() * 10;
		// int bot = (int) (pos.y - camera.viewportHeight) + Factory.hauteur();

		int right = (Factory.width());
		int left = 0;
		int top = Factory.height();
		int bot = 0;

		if (Gdx.input.getX() > right) {
			Gdx.input.setCursorPosition(right, Gdx.input.getY());
		}

		if (Gdx.input.getX() < left) {
			Gdx.input.setCursorPosition(left, Gdx.input.getY());
		}

		if (Gdx.input.getY() > top) {
			Gdx.input.setCursorPosition(Gdx.input.getX(), top);
		}

		if (Gdx.input.getY() < bot) {
			Gdx.input.setCursorPosition(Gdx.input.getX(), bot);
		}

	}

	public void joueur() {
		fenetre();
		if (!delay()) {
			debug();
		}

		// if (!Gdx.input.isCursorCatched()) {
		// Gdx.input.setCursorCatched(true);
		// }
		vp.update(Factory.width(), Factory.height(), true);
		camera.viewportWidth = Factory.width();
		camera.viewportHeight = Factory.height();
		// camera.setToOrtho(true, Factory.width(), Factory.height());

		camera.unproject(touchPos);
		Factory.selected.setSize(Factory.largeur(), Factory.hauteur());
		touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		int az = (int) camera.position.y;
		int x = (int) ((int) touchPos.x);
		int y = (int) (Factory.height() - touchPos.y);
		Factory.selected.setPosition(x - Factory.largeur() / 2, y - Factory.hauteur() / 2);
		Factory.selected.draw(batch);
		curseur.setPosition(x, y);
		Factory.font.draw(batch, "0", 0, 0);

		// camera.viewportWidth = Factory.width();
		// camera.viewportHeight = Factory.height();
		//
		// camera.unproject(touchPos);
		// Factory.selected.setSize(Factory.largeur(), Factory.hauteur());
		// touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		// int az = (int) camera.position.y;
		// int x = (int) touchPos.x - Factory.largeur() / 2;
		// int y = (int) (az - touchPos.y + Factory.hauteur());
		// Factory.selected.setPosition(x - Factory.largeur() / 2, y -
		// Factory.hauteur() / 2);
		// Factory.selected.draw(batch);
		// curseur.setPosition(x, y);

	}

	public void debug() {

		// a.mouseWheelMoved(null);
		// Boat bat = new Current(3);
		// int x = Factory.width();
		// int y = Factory.height();
		// x = x * (1 / 10);
		// y = y * (1 / 10);

		Factory.font.draw(batch, "curseur " + curseur.x + " " + curseur.y, Factory.width() / 2,
				Factory.height() - (Factory.hauteur() * 3));
		Factory.font.draw(batch, "fleche " + Gdx.input.getX() + " " + Gdx.input.getY(), Factory.width() / 2,
				Factory.height() - (Factory.hauteur() * 4));
		Factory.font.draw(batch, map.err(err), 600, 400);

		if (Gdx.input.isKeyJustPressed(Keys.J)) {
			map.afficherJoueur();

		}

		if (Gdx.input.isKeyJustPressed(Keys.K)) {
			// if (map.verifierTouche(map.selection, true))
			// Son.explosion.play();
			map.infligerDegat(map.selection);
			map.tourJoueur = false;
		}
		if (Gdx.input.isKeyJustPressed(Keys.H)) {
			System.out.println("pif !");
			map.infligerDegat(map.grilleJoueur[3][3]);
		}

		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			System.out.println("pif !");
			map.selection = map.grilleJoueur[3][3];
		}

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			map.sens = !map.sens;
		}

		if (Gdx.input.isKeyJustPressed(Keys.B)) {
			map.tourJoueur = false;
		}

		if (Gdx.input.isKeyJustPressed(Keys.ALT_RIGHT)) {
			DisplayMode dis = Gdx.graphics.getDisplayMode();

			Gdx.graphics.setFullscreenMode(dis);
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		joueur();
		vp.update(Factory.width(), Factory.height());
		Gdx.input.setInputProcessor(stage);
	}

	public void dispose() {
		// stage.dispose();
		// batch.dispose();
	}

}
