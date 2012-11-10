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
	private final StopWatch stopWatchFPS = new StopWatch();
	private final StopWatch stopWatchRenderTime = new StopWatch();
	private final Terrain terrain = new Terrain();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();
	private final Ligth light = new Ligth();
	private final Player player = new Player();
	private long frames = 0;
	private long renderTime = 1;

	public RenderEngine() {
		Gdx.graphics.setVSync(false);
		Gdx.gl.glEnable(GL10.GL_CULL_FACE);
		Gdx.gl.glCullFace(GL10.GL_BACK);
	}

	public void render() {
		stopWatchFPS.start();
		stopWatchRenderTime.start();

		moveCamera();
		movePlayer(renderTime);
//		moveLight(renderTime);

		clearScreen();
		switchPolygonMode();

		if(Context.showCoordinateSystem) {
			coordinateSystem.render();
		}

		light.render();

		light.on();
		terrain.render();
		player.render();
		light.off();

		frames++;

		if(stopWatchFPS.elapsedTime() >= 1000) {
			log.info("FPS: " + frames);
			log.info("Light position: " + light.getPosition());
			frames = 0;
			stopWatchFPS.stop();
		}

		renderTime = stopWatchRenderTime.stop();

		// Limit to max. 1000 FPS
		if(renderTime == 0) {
			renderTime = 1;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
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

	private void movePlayer(long renderTime) {
		final List<Integer> pressedKeys = Context.keyboardProcessor.pressedKeys();
		float speed = player.getSpeed();
		boolean movePlayer = false;
		float x = 0;
		float y = 0;

		if(pressedKeys.contains(Input.Keys.UP)) {
			y += speed * (renderTime / 1000f);
			movePlayer = true;
		}

		if(pressedKeys.contains(Input.Keys.DOWN)) {
			y -= speed * (renderTime / 1000f);
			movePlayer = true;
		}

		if(pressedKeys.contains(Input.Keys.LEFT)) {
			x -= speed * (renderTime / 1000f);
			movePlayer = true;
		}

		if(pressedKeys.contains(Input.Keys.RIGHT)) {
			x += speed * (renderTime / 1000f);
			movePlayer = true;
		}

		if(movePlayer) {
			player.move(x, y, 0);
			player.getPosition().z = terrain.getHeight(player.getPosition().x, player.getPosition().y);
		}
	}

	private void moveLight(long renderTime) {
		final float lightSpeed = light.getSpeed();
		light.move(lightSpeed * (renderTime/1000f), lightSpeed * (renderTime/1000f), 0);
	}
}
