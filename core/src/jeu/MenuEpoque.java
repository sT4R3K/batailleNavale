package jeu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class 	MenuEpoque extends ApplicationAdapter {

	SpriteBatch batch;
	Stage stage;
	ScreenViewport vp;

	TextButton Classic;
	TextButton Old;
	TextButton Modern;
	
	public MenuEpoque(SpriteBatch batch, Stage stage, ScreenViewport vps) {
		this.batch = batch;
		this.stage = stage;
		this.vp = vps;

	}

}
