package net.joesoft.andoria.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Properties;

public class Keyboard {
	private final Log log = new Log(this.getClass());

	public void processInput() {
		final float distance = Properties.getScrollDistance();
		final Camera camera = Properties.getCamera();

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			log.debug("Keyboard LEFT pressed");
			camera.position.add(-distance, 0, 0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			log.debug("Keyboard RIGHT pressed");
			camera.position.add(distance, 0, 0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			log.debug("Keyboard UP pressed");
			camera.position.add(0, distance, 0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			log.debug("Keyboard DOWN pressed");
			camera.position.add(0, -distance, 0);
		}

		camera.update();
	}
}
