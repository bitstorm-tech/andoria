package net.joesoft.andoria.input;

import com.badlogic.gdx.InputAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class KeyboardProcessor extends InputAdapter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private List<Integer> pressedKeys = new LinkedList<Integer>();
	private List<Integer> releasedKeys = new LinkedList<Integer>();

	@Override
	public boolean keyDown(int keycode) {
		pressedKeys.add(keycode);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		releasedKeys.add(keycode);
		pressedKeys.remove(new Integer(keycode));
		return true;
	}

	/**
	 *
	 * @param keycode
	 * @return
	 */
	public boolean isKeyPressed(int keycode) {
		return pressedKeys.contains(new Integer(keycode));
	}

	/**
	 *
	 * @param keycode
	 * @return
	 */
	public boolean wasKeyReleased(int keycode) {
		return releasedKeys.remove(new Integer(keycode));
	}
}
