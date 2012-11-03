package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import net.joesoft.andoria.utils.Context;
import net.joesoft.andoria.utils.CoordinateSystem;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.StopWatch;

import java.util.List;

public class RenderEngine {
	private final Log log = new Log(this.getClass());
	private final StopWatch stopWatch = new StopWatch();
	private final Terrain terrain = new Terrain();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();
	private final Ligth light = new Ligth();
	private final Player player = new Player();
	private long frames = 0;
	private float lightPosition = 0;

	public RenderEngine() {
		Gdx.graphics.setVSync(false);
		Gdx.gl.glEnable(GL10.GL_CULL_FACE);
		Gdx.gl.glCullFace(GL10.GL_BACK);
	}

	public void render() {
		stopWatch.start();
		if(lightPosition > 50) {
			lightPosition = 0;
		} else {
			lightPosition += 0.001;
		}

		moveCamera();
		movePlayer();

		clearScreen();
		switchPolygonMode();

		if(Context.showCoordinateSystem) {
			coordinateSystem.render();
		}

		light.move(lightPosition, lightPosition, 5);
		light.render();

		light.on();
		terrain.render();
		player.render();
		light.off();

		frames++;

		if(stopWatch.elapsedTime() >= 1000) {
			log.info("FPS: " + frames);
			frames = 0;
			stopWatch.stop();
		}
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl10.glDepthFunc(GL10.GL_LESS);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	}

	private void switchPolygonMode() {
		if(Context.wireframe) {
			Gdx.gl10.glPolygonMode(GL10.GL_FRONT, GL10.GL_LINE);
			Gdx.gl10.glPolygonMode(GL10.GL_BACK, GL10.GL_LINE);
		} else {
			Gdx.gl10.glPolygonMode(GL10.GL_FRONT, GL10.GL_FILL);
			Gdx.gl10.glPolygonMode(GL10.GL_BACK, GL10.GL_FILL);
		}
	}

	private void moveCamera() {
		final List<Integer> pressedButtons = Context.mouseProcessor.pressedButtons();

		if(pressedButtons.contains(Input.Buttons.RIGHT)) {
			Context.camera.changeDirection(Gdx.input.getDeltaX() * 0.7f, Gdx.input.getDeltaY() * 0.7f);
		}

		if(pressedButtons.contains(Input.Buttons.LEFT)) {
			Context.camera.changePosition(Gdx.input.getDeltaX() * 0.7f, Gdx.input.getDeltaY() * 0.7f);
		}
	}

	private void movePlayer() {
		final List<Integer> pressedKeys = Context.keyboardProcessor.pressedKeys();

		if(pressedKeys.contains(Input.Keys.UP)) {
			player.move(0, 0.01f, 0);
		}

		if(pressedKeys.contains(Input.Keys.DOWN)) {
			player.move(0, -0.01f, 0);

		}

		if(pressedKeys.contains(Input.Keys.LEFT)) {
			player.move(-0.01f, 0, 0);
		}

		if(pressedKeys.contains(Input.Keys.RIGHT)) {
			player.move(0.01f, 0, 0);
		}
	}
}
