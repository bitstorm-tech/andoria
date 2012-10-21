package net.joesoft.andoria.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import net.joesoft.andoria.utils.Log;

public class Keyboard {
	private final Log log = new Log(this.getClass());
	private final Camera cam;

	public Keyboard(Camera camera) {
		cam = camera;
	}

	public void moveCamera(float distance) {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			log.debug("Keyboard LEFT pressed");
			cam.position.add(-distance,0,0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			log.debug("Keyboard RIGHT pressed");
			cam.position.add(distance,0,0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			log.debug("Keyboard UP pressed");
			cam.position.add(0,-distance,0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			log.debug("Keyboard DOWN pressed");
			cam.position.add(0,distance,0);
		}

		cam.update();
	}
}
