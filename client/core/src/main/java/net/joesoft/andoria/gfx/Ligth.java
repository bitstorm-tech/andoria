package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import net.joesoft.andoria.utils.Context;
import net.joesoft.andoria.utils.Log;

public class Ligth {
	private Log LOG = new Log(this.getClass());

	public Ligth() {
		Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[]{0, -1, 1, 1}, 0);
	}

	public void glow() {
		if(Context.light) {
			LOG.debug("light enabled");
			Gdx.gl.glEnable(GL10.GL_LIGHTING);
			Gdx.gl.glEnable(GL10.GL_LIGHT0);
		} else {
			LOG.debug("light disabled");
			Gdx.gl.glDisable(GL10.GL_LIGHTING);
			Gdx.gl.glDisable(GL10.GL_LIGHT0);
		}
	}
}
