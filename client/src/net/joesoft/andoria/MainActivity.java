package net.joesoft.andoria;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import net.joesoft.andoria.gfx.RenderEngine;
import net.joesoft.andoria.listener.TouchListener;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

/**
 * The "Main class" of Andoria Client.
 * 
 * @author josef.bauer.1st@gmail.com
 */
public class MainActivity extends Activity {
	private GLSurfaceView surfaceView;
	private String LOGGER_NAME = MainActivity.class.toString();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOGGER_NAME, "Andoria started");
		surfaceView = new GLSurfaceView(this);
		surfaceView.setRenderer(new RenderEngine());
		surfaceView.setOnTouchListener(new TouchListener());
	}

	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub

	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub

	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

	}
}
