package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.joesoft.andoria.input.Keyboard;
import net.joesoft.andoria.utils.CoordinateSystem;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Properties;
import net.joesoft.andoria.utils.StopWatch;

public class RenderEngine {
	private final Log log = new Log(this.getClass());
	private long frames = 0;
	private long fps = 0;
	private final Camera cam;
	private final ShapeRenderer renderer = new ShapeRenderer();
	private final StopWatch stopWatch = new StopWatch();
    private final Terrain terrain = new Terrain();
	private final Text text;
	private final Keyboard keyboard;
	private final FPSLogger fpsLogger = new FPSLogger();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();

	public RenderEngine() {
		cam = new PerspectiveCamera(90, Properties.getResolutionX(), Properties.getResolutionY());
		cam.position.set(0, -200, 200);
        cam.far = 1000f;
        cam.near = 1f;
        cam.lookAt(0, 0, 0);
		cam.update();
		text = new Text(cam);
		keyboard = new Keyboard(cam);
		Gdx.graphics.setVSync(false);
		terrain.generate();
	}

	public void render() {
		stopWatch.start();
        clearScreen();
		coordinateSystem.render();
		terrain.render();
		keyboard.moveCamera(2);
		frames++;

		final long elapsedTime = stopWatch.elapsedTime();
		if(elapsedTime >= 1000) {
			log.info("FPS: " + frames);
			frames = 0;
			stopWatch.stop();
		}
	}

    private void clearScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }
}
