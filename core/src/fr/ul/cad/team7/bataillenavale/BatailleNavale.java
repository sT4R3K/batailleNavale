package fr.ul.cad.team7.bataillenavale;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Game.Game;
import Game.SimpleGame;
import graphic.Battle;
import graphic.Entity;
import graphic.Souris;
import graphic.factory.Factory;
import map.Boat;
import map.Map;
import map.Old;

public class BatailleNavale extends ApplicationAdapter {
	SpriteBatch batch;// = new SpriteBatch();
	Map map = new Map();
	Boat bateau = new Old(3);
	Entity monde;
	Souris souris;
	Game gear;
	OrthographicCamera camera;
	Viewport vp;
	Stage stage;

	@Override
	public void create() {

		gear = new SimpleGame();

		batch = new SpriteBatch();
		map = gear.map;
		// bateau = new Current(5);

		// map.placement(0, 0, false, new Current(8), false);
		camera = new OrthographicCamera(Factory.width(), Factory.height());
		vp = new ScreenViewport();
		stage = new Stage();
		monde = new Battle(batch, stage, vp, gear, camera);
		souris = new Souris(batch, stage, vp, gear, camera);

	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(1, 1, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float x = camera.position.x;
		float y = camera.position.y;
		float z = camera.position.z;
		x++;
		y++;
		batch.begin();
		camera.position.set(Factory.width() / 2, Factory.height() / 2, z);
		camera.update();
		// camera.translate(new Vector2(100, 100));
		batch.setProjectionMatrix(camera.combined);
		monde.render();
		souris.render();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		monde.dispose();

		// img.dispose();
	}
}
