package graphic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import graphic.factory.Factory;
import jeu.Game;
import map.Map;

public abstract class Entity extends ApplicationAdapter {

	public SpriteBatch batch;
	Stage stage;
	Viewport vp;
	//Map map;
	Factory graph = Factory.getInstance();
	OrthographicCamera camera;
	Game gear;

	public Entity(SpriteBatch sb, Stage stage, Viewport vp, Game game, OrthographicCamera camera) {
		gear = game;
		//map = gear.map;
		batch = sb;
		this.vp = vp;
		this.stage = stage;
		this.camera = camera;
	}

	public void load() {
		//map = gear.map;
	}

	public abstract void render();

}
