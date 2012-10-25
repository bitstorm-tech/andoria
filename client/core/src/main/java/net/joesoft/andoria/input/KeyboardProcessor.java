package net.joesoft.andoria.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Properties;

import java.util.ArrayList;
import java.util.List;

public class KeyboardProcessor extends InputAdapter {
	private static final Log log = new Log(KeyboardProcessor.class);
	private List<Integer> pressedKeys = new ArrayList<Integer>(64);

	@Override
	public boolean keyTyped(char c) {
		if(c == 'l') {
			log.debug("key L typed");
			Properties.setLight(!Properties.getLight());
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
		final float distance = Properties.getScrollDistance();
		final Camera camera = Properties.getCamera();

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
