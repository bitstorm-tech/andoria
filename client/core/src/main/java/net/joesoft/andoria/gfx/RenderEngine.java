package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import net.joesoft.andoria.utils.*;

import java.util.List;

public class RenderEngine {
	private final Log log = new Log(this.getClass());
	private final StopWatch stopWatchFPS = new StopWatch();
	private final StopWatch stopWatchRenderTime = new StopWatch();
	private final Terrain terrain = new Terrain();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();
	public static final GameCamera camera = new GameCamera();
	private final Ligth light = new Ligth();
	private final Player player = new Player();
	private final float SECOND = 1000f;
	private long frames = 0;
	private long renderTime = 1;

	public RenderEngine() {
		Gdx.graphics.setVSync(false);
		Gdx.gl.glEnable(GL10.GL_CULL_FACE);
		Gdx.gl.glCullFace(GL10.GL_BACK);
		light.rotate(90);
	}

	public void render() {
		stopWatchFPS.start();
		stopWatchRenderTime.start();

		moveCamera();
		movePlayer(renderTime);
		moveLight(renderTime);

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

		if(stopWatchFPS.elapsedTime() >= SECOND) {
			log.info("FPS: " + frames);
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
		final int scrolled = Context.mouseProcessor.getScrolled();
		final float deltaX = Gdx.input.getDeltaX() * Context.mouseSpeed;
		final float deltaY = Gdx.input.getDeltaY() * Context.mouseSpeed;

		if(Context.cameraMode == CameraMode.DETACHED) {
			if(pressedButtons.contains(Input.Buttons.RIGHT)) {
				camera.rotate(deltaX, deltaY);
			}

			if(pressedButtons.contains(Input.Buttons.LEFT)) {
				camera.move(deltaX, deltaY);
			}
		} else {
			if(pressedButtons.contains(Input.Buttons.RIGHT)) {
				camera.rotate(deltaX, deltaY, player.getPosition());
				player.rotate(deltaX);
			}
		}

		camera.zoom(scrolled);
	}

	private void movePlayer(long renderTime) {
		final List<Integer> pressedKeys = Context.keyboardProcessor.pressedKeys();
		float speed = player.getSpeed();
		boolean movePlayer = false;
		float x = 0;
		float y = 0;

		if(pressedKeys.contains(Input.Keys.UP)) {
			y += speed * (renderTime / SECOND);
			movePlayer = true;
		}

		if(pressedKeys.contains(Input.Keys.DOWN)) {
			y -= speed * (renderTime / SECOND);
			movePlayer = true;
		}

		if(pressedKeys.contains(Input.Keys.LEFT)) {
			x += speed * (renderTime / SECOND);
			movePlayer = true;
		}

		if(pressedKeys.contains(Input.Keys.RIGHT)) {
			x -= speed * (renderTime / SECOND);
			movePlayer = true;
		}

		if(movePlayer) {
			final float oldHeight = player.getPosition().z;
			player.move(x, y, 0);
			final float newHeight = terrain.getHeight(player.getPosition().x, player.getPosition().y);
			player.getPosition().z = newHeight;

			if(Context.cameraMode == CameraMode.ATTACHED) {
				camera.lookAt(player.getPosition());
				camera.move(x, y, newHeight - oldHeight);
			}
		}
	}

	private void moveLight(long renderTime) {
		if(light.getPosition().x > 50) {
			light.getPosition().set(0, 0, 3);
		}

		final float lightSpeed = light.getSpeed();
		light.move(lightSpeed * (renderTime/SECOND), lightSpeed * (renderTime/SECOND), 0);
		light.getPosition().z = terrain.getHeight(light.getPosition().x, light.getPosition().y) + 3;
	}
}
