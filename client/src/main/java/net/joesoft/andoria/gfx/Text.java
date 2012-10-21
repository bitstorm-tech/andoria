package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Properties;

public class Text {
	private final Log log = new Log(this.getClass());
	private final BitmapFont font;
	private final SpriteBatch spriteBatch = new SpriteBatch();
	private final Camera cam;

	public Text(Camera camera) {
		cam = camera;
		final FileHandle fntFile = Gdx.files.classpath("fonts/andoria-font.fnt");
		final FileHandle pngFile = Gdx.files.classpath("fonts/andoria-font.png");
		font = new BitmapFont(fntFile, pngFile, false);
	}

	public void write(String text, int x, int y) {
		final float posX = x - (Properties.getResolutionX()/2) + cam.position.x;
		final float posY = y + (Properties.getResolutionY()/2) + cam.position.y - font.getCapHeight();
		spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.begin();
		font.draw(spriteBatch, text, posX, posY);
		spriteBatch.end();
	}
}
