package net.joesoft.andoria.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font {
	private static final FileHandle fntFile = Gdx.files.classpath("fonts/andoria-font.fnt");
	private static final FileHandle pngFile = Gdx.files.classpath("fonts/andoria-font.png");
	private static final BitmapFont font = new BitmapFont(fntFile, pngFile, false);

	public static BitmapFont getBitmapFont() {
		return font;
	}
}
