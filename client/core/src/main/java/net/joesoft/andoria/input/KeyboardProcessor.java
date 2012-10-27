package net.joesoft.andoria.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import net.joesoft.andoria.utils.Context;
import net.joesoft.andoria.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class KeyboardProcessor extends InputAdapter {
	private static final Log log = new Log(KeyboardProcessor.class);
	private List<Integer> pressedKeys = new ArrayList<Integer>(64);

	@Override
	public boolean keyTyped(char c) {
		if(c == 'l') {
			log.debug("key L typed");
			Context.light = !Context.light;

			if(Context.light) {
				log.info("light on");
			} else {
				log.info("light off");
			}
		}

		if(c == 'w') {
			Context.wireframe = !Context.wireframe;
		}

		if(c == 'c') {
			Context.showCoordinateSystem = !Context.showCoordinateSystem;
		}

		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		pressedKeys.add(keycode);
		return true;
	}

	public boolean keyUp(int keycode) {
		pressedKeys.remove(new Integer(keycode));
		return true;
	}

	public void process() {
		final float distance = Context.scrollDistance;
		final Camera camera = Context.camera;

		if (pressedKeys.contains(Input.Keys.LEFT)) {
			log.debug("key LEFT pressed");
			camera.translate(-distance, 0, 0);
		}

		if (pressedKeys.contains(Input.Keys.RIGHT)) {
			log.debug("key RIGHT pressed");
			camera.translate(distance, 0, 0);
		}

		if (pressedKeys.contains(Input.Keys.UP)) {
			log.debug("key UP pressed");
			camera.translate(0, distance, 0);
		}

		if (pressedKeys.contains(Input.Keys.DOWN)) {
			log.debug("key DOWN pressed");
			camera.translate(0, -distance, 0);
		}
	}
}
