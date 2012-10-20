package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen {
	final RenderEngine engine = new RenderEngine();

	public void dispose() {
	}

	public void hide() {
	}

	public void pause() {
	}

	public void render(float arg0) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		engine.render();
	}

	public void resize(int arg0, int arg1) {
	}

	public void resume() {
	}

	public void show() {
	}
}
