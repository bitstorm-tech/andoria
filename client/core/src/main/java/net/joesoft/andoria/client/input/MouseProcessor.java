package net.joesoft.andoria.client.input;

import com.badlogic.gdx.InputAdapter;

import java.util.ArrayList;
import java.util.List;

public class MouseProcessor extends InputAdapter {
	private List<Integer> pressedButtons = new ArrayList<Integer>(16);
	private int scrolled;

	@Override
	public boolean scrolled(int amount) {
		scrolled = amount;
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

	public List<Integer> pressedButtons() {
		return pressedButtons;
	}

	public int getScrolled() {
		final int tmp = scrolled;
		scrolled = 0;
		return tmp;
	}
}
