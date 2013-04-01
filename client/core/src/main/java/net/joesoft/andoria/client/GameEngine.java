package net.joesoft.andoria.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import net.joesoft.andoria.brain.AndoriaBrain;
import net.joesoft.andoria.brain.ObjectType;
import net.joesoft.andoria.brain.commands.Command;
import net.joesoft.andoria.brain.commands.PositionUpdateCmd;
import net.joesoft.andoria.brain.commands.SpawnObjectCmd;
import net.joesoft.andoria.brain.net.ServerCommunicator;
import net.joesoft.andoria.client.gfx.Light;
import net.joesoft.andoria.client.gfx.Player;
import net.joesoft.andoria.client.gfx.Renderable;
import net.joesoft.andoria.client.gfx.Skybox;
import net.joesoft.andoria.client.gfx.Terrain;
import net.joesoft.andoria.client.input.KeyboardProcessor;
import net.joesoft.andoria.client.input.MouseProcessor;
import net.joesoft.andoria.client.model.GameObject;
import net.joesoft.andoria.client.ui.FpsLabel;
import net.joesoft.andoria.client.ui.SettingsMenu;
import net.joesoft.andoria.client.utils.CameraMode;
import net.joesoft.andoria.client.utils.CoordinateSystem;
import net.joesoft.andoria.client.utils.GameCamera;
import net.joesoft.andoria.client.utils.Settings;
import net.joesoft.andoria.client.utils.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameEngine {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Terrain terrain = new Terrain();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();
	private final MouseProcessor mouseProcessor = new MouseProcessor();
	private final KeyboardProcessor keyboardProcessor = new KeyboardProcessor();
	private final GameCamera camera = new GameCamera();
	private final SettingsMenu settingsMenu = new SettingsMenu();
	private final FpsLabel fpsLabel = new FpsLabel();
	private final Stage stage = new Stage();
	private final StopWatch stopWatch = new StopWatch();
	private final Map<Integer, GameObject> gameObjects = new Hashtable<Integer, GameObject>();
	private final LinkedList<Renderable> renderables = new LinkedList<Renderable>();
	private final ServerCommunicator com = new ServerCommunicator();
	private final Skybox skybox =  new Skybox(1000, false);
	private float lastRenderDuration;

	/**
	 *
	 */
	public GameEngine() {
		new Thread(new AndoriaBrain()).start();
		final InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(keyboardProcessor);
		multiplexer.addProcessor(mouseProcessor);

		stage.addActor(settingsMenu);
		stage.addActor(fpsLabel);

		Gdx.input.setInputProcessor(multiplexer);
		Gdx.graphics.setVSync(false);

		com.connect(1);
		new Thread(com).start();

		renderables.add(skybox);
		renderables.add(terrain);
		renderables.add(coordinateSystem);
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
		lastRenderDuration = Gdx.graphics.getRawDeltaTime();

		clearScreen();
		stopWatch.start();
		final int fps = Gdx.graphics.getFramesPerSecond();

		processMouseInput();
		processKeyboardInput();
		processCommands();
		moveCamera();

		// 3D stuff ===============================================================================
		set3dGlSettings();
		renderRenderables();

		// 32 stuff ===============================================================================
		set2dGlSettings();
		fpsLabel.setVisible(Settings.getBoolean(Settings.Key.UI_SHOWFPS));
		fpsLabel.setFps(fps);
		stage.draw();
	}

	private void renderRenderables() {
		for(Renderable renderable : renderables) {
			if(!renderable.isVisible()) {
				continue;
			}

			if(renderable.isIlluminated() && Settings.getBoolean(Settings.Key.ENGINE_LIGHT)) {
				lightOn();
			}

			renderable.render();
			lightOff();
		}
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
	 * Either enables wireframe rendering mode or filled rendering mode (depending on the settings).
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
	}

//	/**
//	 * Moves the player (when the appropriate keys are pressed). The distance depends on how
//	 * long the last screen needs to render.
//	 */
//	private void movePlayer() {
//		float speed = player.speed;
//		boolean movePlayer = false;
//		float x = 0;
//		float y = 0;
//
//		if(keyboardProcessor.isKeyPressed(Input.Keys.W)) {
//			y += speed * lastRenderDuration;
//			movePlayer = true;
//		}
//
//		if(keyboardProcessor.isKeyPressed(Input.Keys.S)) {
//			y -= speed * lastRenderDuration;
//			movePlayer = true;
//		}
//
//		if(keyboardProcessor.isKeyPressed(Input.Keys.A)) {
//			x += speed * lastRenderDuration;
//			movePlayer = true;
//		}
//
//		if(keyboardProcessor.isKeyPressed(Input.Keys.D)) {
//			x -= speed * lastRenderDuration;
//			movePlayer = true;
//		}
//
//		if(movePlayer) {
//			final float oldHeight = player.z;
//			player.x = x;
//			player.y = y;
//			final float newHeight = terrain.getHeight(player.x, player.y);
//			player.z = newHeight;
//
//			if(Settings.getString(Settings.Key.ENGINE_CAMERAMODE).equalsIgnoreCase(CameraMode.ATTACHED.name())) {
//				camera.lookAt(new Vector3(player.x, player.y, player.z));
//				camera.move(x, y, newHeight - oldHeight);
//			}
//		}
//	}

	/**

//	 * Moves the light mesh. The distance depends on how long the last screen needs to render.
//	 *
//	 * @param renderTime the time the last screen needs to render; to decide the distance of the movement
//	 */
//	private void moveLight(float renderTime) {
//		if(light.getPosition().x > 50) {
//			light.getPosition().set(0, 0, 3);
//		}
//
//		final float lightSpeed = light.speed;
//		light.move(lightSpeed * renderTime, lightSpeed * renderTime, 0);
//		light.getPosition().z = terrain.getHeight(light.getPosition().x, light.getPosition().y) + 3;
//	}

	/**
	 * Processing keyboard input.
	 */
	private void processKeyboardInput() {
		if(keyboardProcessor.wasKeyReleased(Input.Keys.ESCAPE)) {
			settingsMenu.setVisible(!settingsMenu.isVisible());
		}
	}

	/**
	 * Processing mouse input.
	 */
	private void processMouseInput() {
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
//				player.rotate(deltaX);
			}
		}

		camera.zoom(scrolled);
	}

	private void processCommands() {
		final long startTime = System.currentTimeMillis();

		while(true) {
			// Limit the command processing to x ms otherwhise this could block the rendering phase.
			if(System.currentTimeMillis() - startTime > 1) {
				break;
			}

			if(!com.hasCommandsQueued()) {
				continue;
			}

			final Command cmd = com.getCommand();
			if(cmd instanceof PositionUpdateCmd) {
				updatePosition((PositionUpdateCmd)cmd);
			} else if (cmd instanceof SpawnObjectCmd) {
				spawnObject((SpawnObjectCmd)cmd);
			}
		}
	}

	private void spawnObject(SpawnObjectCmd cmd) {
		final int objectId = cmd.objectId;
		if(gameObjects.containsKey(objectId)) {
			return;
		}

		if(cmd.objectType == ObjectType.PLAYER) {
			final Player player = new Player();
			gameObjects.put(objectId, player);
			renderables.add(player);
		} else if(cmd.objectType == ObjectType.LIGHT) {
			final Light light = new Light();
			gameObjects.put(objectId, light);
			renderables.add(light);
		}
	}

	private void updatePosition(PositionUpdateCmd cmd) {
		final GameObject gameObject = gameObjects.get(cmd.objectId);
		gameObject.x = cmd.x;
		gameObject.y = cmd.y;
		gameObject.z = cmd.z;
	}

	/**
	 * Enables light.
	 */
	private void lightOn() {
		//TODO not performant!!
		Light light = null;
		for(GameObject o : gameObjects.values()) {
			if(o.type == ObjectType.LIGHT) {
				light = (Light)o;
			}
		}


		Gdx.gl10.glShadeModel(GL10.GL_SMOOTH);
		Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, new float[]{1, 1, 1, 1}, 0);
		Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, new float[]{1, 1, 1, 1}, 0);
		if(light != null) {
			Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[]{light.x, light.y, light.z, 1}, 0);
		} else {
			Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[]{0, 0, 0, 1}, 0);
		}
		Gdx.gl.glEnable(GL10.GL_LIGHTING);
		Gdx.gl.glEnable(GL10.GL_LIGHT0);
	}

	/**
	 * Disables light.
	 */
	private void lightOff() {
		Gdx.gl.glDisable(GL10.GL_LIGHTING);
		Gdx.gl.glDisable(GL10.GL_LIGHT0);
	}
}
