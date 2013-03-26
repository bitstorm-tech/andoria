package net.joesoft.andoria.client.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import net.joesoft.andoria.brain.AndoriaBrain;
import net.joesoft.andoria.client.input.KeyboardProcessor;
import net.joesoft.andoria.client.input.MouseProcessor;
import net.joesoft.andoria.client.model.Player;
import net.joesoft.andoria.client.ui.FpsLabel;
import net.joesoft.andoria.client.ui.SettingsMenu;
import net.joesoft.andoria.client.utils.CameraMode;
import net.joesoft.andoria.client.utils.CoordinateSystem;
import net.joesoft.andoria.client.utils.GameCamera;
import net.joesoft.andoria.client.utils.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RenderEngine {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Terrain terrain = new Terrain();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();
	private final MouseProcessor mouseProcessor = new MouseProcessor();
	private final KeyboardProcessor keyboardProcessor = new KeyboardProcessor();
	private final GameCamera camera = new GameCamera();
	private final Ligth light = new Ligth();
	private final Player player = new Player();
	private final SettingsMenu settingsMenu = new SettingsMenu();
	private final FpsLabel fpsLabel = new FpsLabel();
	private final Stage stage = new Stage();
	private final AndoriaBrain brain = new AndoriaBrain();

	/**
	 *
	 */
	public RenderEngine() {
		final InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(keyboardProcessor);
		multiplexer.addProcessor(mouseProcessor);

		stage.addActor(settingsMenu);
		stage.addActor(fpsLabel);

		Gdx.input.setInputProcessor(multiplexer);
		Gdx.graphics.setVSync(false);
		light.rotate(90);

		brain.start();
	}

	private void set3dGlSettings() {
		Gdx.gl.glEnable(GL10.GL_CULL_FACE);
		Gdx.gl.glCullFace(GL10.GL_BACK);
		switchPolygonMode();
	}

	private void set2dGlSettings() {
		Gdx.gl.glDisable(GL10.GL_CULL_FACE);
		Gdx.gl10.glPolygonMode(GL10.GL_FRONT, GL10.GL_FILL);
		Gdx.gl10.glPolygonMode(GL10.GL_BACK, GL10.GL_FILL);
	}

	/**
	 *
	 */
	public void render() {
		final float renderTime = Gdx.graphics.getRawDeltaTime();
		final int fps = Gdx.graphics.getFramesPerSecond();

		processInput();
		clearScreen();
		moveCamera();
		movePlayer(renderTime);
		moveLight(renderTime);

		//===================== 3D stuff =====================
		set3dGlSettings();
		if(Settings.getBoolean(Settings.Key.ENGINE_SHOWCOORDINATESYSTEM)) {
			coordinateSystem.render();
		}

		light.render();

		if(Settings.getBoolean(Settings.Key.ENGINE_LIGHT)) {
			light.on();
		}

		terrain.render();
		player.render();
		light.off();
		//====================================================


		//===================== 2D stuff =====================
		set2dGlSettings();
		fpsLabel.setVisible(Settings.getBoolean(Settings.Key.UI_SHOWFPS));
		fpsLabel.setFps(fps);
		stage.draw();
		//====================================================
	}

	/**
	 *
	 */
	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl10.glDepthFunc(GL10.GL_LESS);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	}

	/**
	 *
	 */
	private void switchPolygonMode() {
		if(Settings.getBoolean(Settings.Key.ENGINE_WIREFRAME)) {
			Gdx.gl10.glPolygonMode(GL10.GL_FRONT, GL10.GL_LINE);
			Gdx.gl10.glPolygonMode(GL10.GL_BACK, GL10.GL_LINE);
		} else {
			Gdx.gl10.glPolygonMode(GL10.GL_FRONT, GL10.GL_FILL);
			Gdx.gl10.glPolygonMode(GL10.GL_BACK, GL10.GL_FILL);
		}
	}

	/**
	 *
	 */
	private void moveCamera() {
		final List<Integer> pressedButtons = mouseProcessor.pressedButtons();
		final int scrolled = mouseProcessor.getScrolled();
		final float mouseSpeed = Settings.getFloat(Settings.Key.INPUT_MOUSE_SPEED);
		final float deltaX = Gdx.input.getDeltaX() * mouseSpeed;
		final float deltaY = Gdx.input.getDeltaY() * mouseSpeed;

		if(Settings.getString(Settings.Key.ENGINE_CAMERAMODE).equalsIgnoreCase(CameraMode.DETACHED.name())) {
			if(pressedButtons.contains(Input.Buttons.RIGHT)) {
				camera.rotate(deltaX, deltaY);
			}

			if(pressedButtons.contains(Input.Buttons.LEFT)) {
				camera.move(deltaX, deltaY);
			}
		} else {
			if(pressedButtons.contains(Input.Buttons.RIGHT)) {
				camera.rotate(deltaX, deltaY);
				player.rotate(deltaX);
			}
		}

		camera.zoom(scrolled);
	}

	/**
	 * Moves the player (when the appropriate keys are pressed). The distance depends on how
	 * long the last screen needs to render.
	 *
	 * @param renderTime the time the last screen needs to render; to decide the distance of the movement
	 */
	private void movePlayer(float renderTime) {
		float speed = player.getSpeed();
		boolean movePlayer = false;
		float x = 0;
		float y = 0;

		if(keyboardProcessor.isKeyPressed(Input.Keys.W)) {
			y += speed * renderTime;
			movePlayer = true;
		}

		if(keyboardProcessor.isKeyPressed(Input.Keys.S)) {
			y -= speed * renderTime;
			movePlayer = true;
		}

		if(keyboardProcessor.isKeyPressed(Input.Keys.A)) {
			x += speed * renderTime;
			movePlayer = true;
		}

		if(keyboardProcessor.isKeyPressed(Input.Keys.D)) {
			x -= speed * renderTime;
			movePlayer = true;
		}

		if(movePlayer) {
			final float oldHeight = player.getPosition().z;
			player.move(x, y, 0);
			final float newHeight = terrain.getHeight(player.getPosition().x, player.getPosition().y);
			player.getPosition().z = newHeight;

			if(Settings.getString(Settings.Key.ENGINE_CAMERAMODE).equalsIgnoreCase(CameraMode.ATTACHED.name())) {
				camera.lookAt(player.getPosition());
				camera.move(x, y, newHeight - oldHeight);
			}
		}
	}

	/**

	 * Moves the light mesh. The distance depends on how long the last screen needs to render.
	 *
	 * @param renderTime the time the last screen needs to render; to decide the distance of the movement
	 */
	private void moveLight(float renderTime) {
		if(light.getPosition().x > 50) {
			light.getPosition().set(0, 0, 3);
		}

		final float lightSpeed = light.getSpeed();
		light.move(lightSpeed * renderTime, lightSpeed * renderTime, 0);
		light.getPosition().z = terrain.getHeight(light.getPosition().x, light.getPosition().y) + 3;
	}

	private void processInput() {
		if(keyboardProcessor.wasKeyReleased(Input.Keys.ESCAPE)) {
			settingsMenu.setVisible(!settingsMenu.isVisible());
		}
	}
}
