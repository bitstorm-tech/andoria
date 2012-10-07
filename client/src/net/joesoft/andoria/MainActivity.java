package net.joesoft.andoria;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import net.joesoft.andoria.gfx.AndoriaRenderEngine;
import net.joesoft.andoria.listener.AndoriaTouchListener;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * The "Main class" of Andoria Client.
 * 
 * @author josef.bauer.1st@gmail.com
 */
public class MainActivity extends Activity {
	private GLSurfaceView surfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new GLSurfaceView(this);
		surfaceView.setRenderer(new AndoriaRenderEngine());
		surfaceView.setOnTouchListener(new AndoriaTouchListener());
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
