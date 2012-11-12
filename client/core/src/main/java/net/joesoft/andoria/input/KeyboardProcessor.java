package net.joesoft.andoria.input;

import com.badlogic.gdx.InputAdapter;
import net.joesoft.andoria.utils.CameraMode;
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

		if(c == 'n') {
			Context.showNormals = !Context.showNormals;
		}

		if(c == 'm') {
			if(Context.cameraMode == CameraMode.ATTACHED) {
				Context.cameraMode = CameraMode.DETACHED;
			} else {
				Context.cameraMode = CameraMode.ATTACHED;
			}
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

	public List<Integer> pressedKeys() {
		return pressedKeys;
	}
}
