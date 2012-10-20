package net.joesoft.andoria.gfx;

import android.os.SystemClock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Logger;

public class RenderEngine {
	private final Logger log = new Logger(RenderEngine.class.getCanonicalName());
	private long secondCounter = 0;
	private final OrthographicCamera cam;
	private final ShapeRenderer debugRenderer = new ShapeRenderer();

	public RenderEngine() {
		cam = new OrthographicCamera(10, 7);
		cam.position.set(5, 3.5f, 0);
		cam.update();
	}

	public void render() {
		final long start = SystemClock.elapsedRealtime();

		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		debugRenderer.setColor(new Color(1, 0, 0, 1));
		debugRenderer.rect(0, 0, 5, 5);
		debugRenderer.end();

		try {
			Thread.sleep(234);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
		}
		final long end = SystemClock.elapsedRealtime();
		final long duration = end - start;
		log.debug("Start:  :" + start);
		log.debug("End     :" + end);
		log.debug("Duration: " + duration);
		secondCounter = secondCounter + duration;
		if (secondCounter > 1000) {
			final long fps = 1000 / duration;
			log.info("FPS: " + fps);
			secondCounter = 0;
		}
	}
}
