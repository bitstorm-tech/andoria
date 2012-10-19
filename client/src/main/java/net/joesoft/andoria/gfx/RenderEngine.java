package net.joesoft.andoria.gfx;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import net.joesoft.andoria.utils.Log;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;

public class RenderEngine implements Renderer {
	private final Log log = new Log(RenderEngine.class);
	private final FloatBuffer vertices;
	private long secondCounter = 0;

	public RenderEngine() {
		final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * 3 * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertices = byteBuffer.asFloatBuffer();
		vertices.put(-0.5f);
		vertices.put(-0.5f);
		vertices.put(0);
		vertices.put(0.5f);
		vertices.put(-0.5f);
		vertices.put(0);
		vertices.put(0);
		vertices.put(0.5f);
		vertices.put(0);
	}

	public void onDrawFrame(GL10 gl) {
		final long start = SystemClock.elapsedRealtime();
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
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
		// log.debug("Second counter = " + secondCounter);
		// show FPS only once a second
		if (secondCounter > 1000) {
			final long fps = 1000 / duration;
			log.info("FPS: " + fps);
			secondCounter = 0;
		}
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		log.debug("onSurfaceChanged() called");
		gl.glViewport(0, 0, width, height);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		log.debug("onSurfaceCreated() called");
	}
}
