package net.joesoft.andoria.client.gfx;

import com.badlogic.gdx.Screen;
import net.joesoft.andoria.client.GameEngine;

public class GameScreen implements Screen {
	final GameEngine engine = new GameEngine();

	public void dispose() {
	}

	public void hide() {
	}

	public void pause() {
	}

	public void render(float arg0) {
		engine.render();
	}

	public void resize(int arg0, int arg1) {
	}

	public void resume() {
	}

	public void show() {
	}
}
