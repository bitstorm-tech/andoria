package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.joesoft.andoria.input.Keyboard;
import net.joesoft.andoria.utils.CoordinateSystem;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Properties;
import net.joesoft.andoria.utils.StopWatch;

public class RenderEngine {
	private final Log log = new Log(this.getClass());
	private final Camera cam;
	private final ShapeRenderer renderer = new ShapeRenderer();
	private final StopWatch stopWatch = new StopWatch();
    private final Terrain terrain = new Terrain();
	private final Keyboard keyboard = new Keyboard();
	private final CoordinateSystem coordinateSystem = new CoordinateSystem();
	private long frames = 0;

	public RenderEngine() {
		cam = new PerspectiveCamera(60, Properties.getResolutionX(), Properties.getResolutionY());
        cam.far = 1000f;
        cam.near = 1f;
		cam.position.add(0, -5, 5);
		cam.lookAt(0, 0, 0);
		Properties.setCamera(cam);
		Gdx.graphics.setVSync(false);
		terrain.generate();
	}

	public void render() {
		stopWatch.start();
		keyboard.processInput();
		Properties.getCamera().apply(Gdx.gl10);
        clearScreen();
		coordinateSystem.render();
		terrain.render();
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
