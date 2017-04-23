package graphic.factory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Son {

	public static Sound explosion = Gdx.audio.newSound(Gdx.files.internal("son/explosion.mp3"));

	private static volatile Son instance = null;

	public final static Son getInstance() {
		if (Son.instance == null) {
			synchronized (Son.class) {
				if (Son.instance == null) {
					Son.instance = new Son();
				}
			}
		}
		return Son.instance;
	}

}
