package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
	final RenderEngine engine = new RenderEngine();

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
