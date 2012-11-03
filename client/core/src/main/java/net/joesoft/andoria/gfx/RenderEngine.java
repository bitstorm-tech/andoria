package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
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

		Context.keyboardProcessor.process();
		Context.mouseProcessor.process();
		clearScreen();
		switchPolygonMode();

		if(Context.showCoordinateSystem) {
			coordinateSystem.render();
		}

		light.move(lightPosition, lightPosition, 5);
		light.glow();
		light.render();
		terrain.render();
		player.render();

		if(Context.showNormals) {
			terrain.renderNormals();
			player.renderNormals();
		}

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
