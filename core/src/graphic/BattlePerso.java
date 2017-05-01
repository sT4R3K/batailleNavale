package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import graphic.factory.Factory;
import jeu.Game;

public class BattlePerso extends GraphBattle {

	public BattlePerso(SpriteBatch sb, Stage stage, Viewport vp, Game game, OrthographicCamera camera) {
		super(sb, stage, vp, game, camera);
		// TODO Auto-generated constructor stub
	}

	public void render() {
		tuto();

		Factory.font.draw(batch, "sdsjihsuisuj", Factory.width() * (3 / 4), Factory.height() * (3 / 4));
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
}
