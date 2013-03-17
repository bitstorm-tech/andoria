package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
	private final BitmapFont font;
	private final SpriteBatch spriteBatch = new SpriteBatch();

	public Text() {
		final FileHandle fntFile = Gdx.files.classpath("fonts/andoria-font.fnt");
		final FileHandle pngFile = Gdx.files.classpath("fonts/andoria-font.png");
		font = new BitmapFont(fntFile, pngFile, false);
	}

	public void write(String text, int x, int y) {
		spriteBatch.begin();
		font.draw(spriteBatch, text, x, y + font.getCapHeight());
		spriteBatch.end();
	}
}
