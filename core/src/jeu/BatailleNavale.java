package jeu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gestionSauvegarde.Game;
import gestionSauvegarde.SimpleGame;
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
	public Game gear;
	OrthographicCamera camera;
	Viewport vp;
	Stage stage;

	@Override
	public void create() {
		gear = new SimpleGame();
		batch = new SpriteBatch();
		map = gear.map;
		camera = new OrthographicCamera(Factory.width(), Factory.height());
		vp = new ScreenViewport();
		stage = new Stage();
		monde = new Battle(batch, stage, vp, gear, camera);
		souris = new Souris(batch, stage, vp, gear, camera);
	}
	
	public void createPerso() {
		gear = new SimpleGame();
		batch = new SpriteBatch();
		map = gear.map;
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
		batch.setProjectionMatrix(camera.combined);
		monde.render();
		souris.render();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		monde.dispose();
	}
}
