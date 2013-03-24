package net.joesoft.andoria.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import net.joesoft.andoria.utils.Constants;

public class Resources {
	private static boolean atlasGenerated = false;
	private static boolean skinCreated = false;
	private static TextureAtlas atlas;
	private static Skin skin;

	public static Skin getSkin() {
		if(skinCreated) {
			return skin;
		}

		skin = new Skin(Constants.skinFileHandle, generateTextureAtlas());
		return skin;
	}

	public static Sprite getSprite(String name) {
		return generateTextureAtlas().createSprite(name);
	}

	private static TextureAtlas generateTextureAtlas() {
		if(atlasGenerated) {
			return atlas;
		}

		final TexturePacker2.Settings settings = new TexturePacker2.Settings();
		settings.maxHeight = 4096;
		settings.maxWidth = 4096;
		TexturePacker2.process(settings, "../../core/src/main/resources/ui", "ui", "andoria-texture-atlas");
		atlas = new TextureAtlas("ui/andoria-texture-atlas.atlas");
		atlasGenerated = true;
		return atlas;
	}
}
