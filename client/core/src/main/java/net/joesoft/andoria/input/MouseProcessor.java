package net.joesoft.andoria.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
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
		final Vector3 direction = Context.camera.direction;
		Context.camera.translate(-amount * direction.x, -amount * direction.y, -amount * direction.z);
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
			Context.camera.rotate(-Gdx.input.getDeltaX(), 0, 0, 1);
		}


		if(pressedButtons.contains(Input.Buttons.LEFT)) {
			final float speed = Context.scrollSpeed;
			final float moveX = Gdx.input.getDeltaX() / speed;
			final float moveY = Gdx.input.getDeltaY() / speed;
			Context.camera.translate(-moveX, moveY, 0);
		}
	}
}
