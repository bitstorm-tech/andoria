package net.joesoft.andoria.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import net.joesoft.andoria.utils.Context;
import net.joesoft.andoria.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class MouseProcessor extends InputAdapter {
	private final Log LOG = new Log(this.getClass());
	private List<Integer> pressedButtons = new ArrayList<Integer>(16);

	@Override
	public boolean scrolled(int amount) {
		LOG.debug("Scrolled");
		Context.camera.zoom(amount);
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
			Context.camera.changeDirection(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
		}


		if(pressedButtons.contains(Input.Buttons.LEFT)) {
			Context.camera.changePosition(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
		}
	}
}
