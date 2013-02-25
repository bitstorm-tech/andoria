package net.joesoft.andoria.input;

import com.badlogic.gdx.InputAdapter;
import net.joesoft.andoria.utils.CameraMode;
import net.joesoft.andoria.utils.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class KeyboardProcessor extends InputAdapter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private List<Integer> pressedKeys = new ArrayList<Integer>(64);

	@Override
	public boolean keyTyped(char c) {
		if(c == 'l') {
			log.debug("key L typed");
			Settings.setLight(!Settings.isLight());

			if(Settings.isLight()) {
				log.info("light on");
			} else {
				log.info("light off");
			}
		}

		if(c == 'w') {
			Settings.setWireframe(!Settings.isWireframe());
		}

		if(c == 'c') {
			Settings.setShowCoordinateSystem(!Settings.isShowCoordinateSystem());
		}

		if(c == 'n') {
			Settings.setShowNormals(!Settings.isShowNormals());
		}

		if(c == 'm') {
			if(Settings.getCameraMode() == CameraMode.ATTACHED) {
				Settings.setCameraMode(CameraMode.DETACHED);
			} else {
				Settings.setCameraMode(CameraMode.ATTACHED);
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
