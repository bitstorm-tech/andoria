package net.joesoft.andoria.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import net.joesoft.andoria.utils.Context;
import net.joesoft.andoria.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class MouseProcessor extends InputAdapter {
	private final Log LOG = new Log(this.getClass());
	private List<Integer> pressedButtons = new ArrayList<Integer>(16);

	@Override
	public boolean scrolled(int amount) {
		final Camera cam = Context.camera;

		if(amount == -1) {
			LOG.debug("Scrolled -1");
			cam.translate(0, 1, 1);
		}

		if(amount == 1) {
			LOG.debug("Scrolled 1");
			cam.translate(0, -1, -1);
		}

		cam.update();
		return true;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		pressedButtons.add(button);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		pressedButtons.remove(new Integer(button));
		return true;
	}

	public void process() {
		if(pressedButtons.contains(Input.Buttons.RIGHT)) {
			Gdx.input.setCursorPosition(Context.resolutionX / 2, Context.resolutionY / 2);
			final int moveX = Gdx.input.getDeltaX();
			final int movey = Gdx.input.getDeltaY();
		}
	}
}
