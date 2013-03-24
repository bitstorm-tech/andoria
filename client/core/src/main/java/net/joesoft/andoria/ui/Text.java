package net.joesoft.andoria.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Text extends UiComponent {
	private static final Skin skin = Resources.getSkin();

	public void write(String text, int x, int y) {
		final BitmapFont font = skin.getFont("default");
		spriteBatch.begin();
		font.draw(spriteBatch, text, x, y + font.getCapHeight());
		spriteBatch.end();
	}
}
