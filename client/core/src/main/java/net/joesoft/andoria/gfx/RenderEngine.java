package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import net.joesoft.andoria.utils.Context;
import net.joesoft.andoria.utils.CoordinateSystem;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.StopWatch;

public class RenderEngine {
	private final Log log = new Log(this.getClass());
	private final StopWatch stopWatch = new StopWatch();
	private final Terrain terrain = new Terrain();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();
	private final Ligth light = new Ligth();
	private long frames = 0;

	public RenderEngine() {
		final Camera cam = new PerspectiveCamera(75, Context.resolutionX, Context.resolutionY);
		cam.far = 1000f;
		cam.near = 1f;
		cam.translate(0, -5, 5);
		cam.lookAt(0, 0, 0);
		Context.camera = cam;
		Gdx.graphics.setVSync(false);
	}

	public void render() {
		stopWatch.start();

		Context.keyboardProcessor.process();
		Context.mouseProcessor.process();
		Context.camera.update();
		Context.camera.apply(Gdx.gl10);
		clearScreen();
		switchPolygonMode();
		light.glow();
		coordinateSystem.render();
		terrain.render();

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
}
