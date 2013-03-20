package net.joesoft.andoria.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Text extends UiComponent {

	public void write(String text, int x, int y) {
		final BitmapFont font = Font.getBitmapFont();
		spriteBatch.begin();
		font.draw(spriteBatch, text, x, y + font.getCapHeight());
		spriteBatch.end();
	}
}
