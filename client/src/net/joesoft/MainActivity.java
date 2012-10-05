package net.joesoft;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;

/**
 * The "Main class" of Andoria.
 * 
 * @author josef.bauer.1st@gmail.com
 */
public class MainActivity extends Activity implements Renderer {
	private GLSurfaceView surfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new GLSurfaceView(this);
		surfaceView.setRenderer(this);
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
