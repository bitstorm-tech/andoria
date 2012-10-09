package net.joesoft.andoria;

import net.joesoft.andoria.gfx.RenderEngine;
import net.joesoft.andoria.listener.TouchListener;
import net.joesoft.andoria.utils.Log;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * The "Main class" of Andoria Client.
 * 
 * @author josef.bauer.1st@gmail.com
 */
public class MainActivity extends Activity {
	private GLSurfaceView glView;
	private final Log log = new Log(MainActivity.class);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		log.info("Andoria started");
		glView = new GLSurfaceView(this);
		glView.setRenderer(new RenderEngine());
		glView.setOnTouchListener(new TouchListener());
		setContentView(glView);
	}
}
