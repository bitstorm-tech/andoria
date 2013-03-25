package net.joesoft.andoria.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import net.joesoft.andoria.utils.Constants;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Resources {
	private static final Logger log = LoggerFactory.getLogger(Resources.class);
	private static boolean atlasGenerated = false;
	private static boolean skinCreated = false;
	private static TextureAtlas atlas;
	private static Skin skin;

	public static Skin getSkin() {
		if(skinCreated) {
			return skin;
		}

		skin = new Skin(Constants.skinFileHandle, generateTextureAtlas());
		skinCreated = true;
		return skin;
	}

	private static TextureAtlas generateTextureAtlas() {
		copyFromJarToFS("ui/checkbox-checked.png", false);
		copyFromJarToFS("ui/checkbox-unchecked.png", false);
		copyFromJarToFS("ui/settings-menu-background.png", false);

		if(atlasGenerated) {
			return atlas;
		}

		final TexturePacker2.Settings settings = new TexturePacker2.Settings();
		settings.maxHeight = 4096;
		settings.maxWidth = 4096;
		TexturePacker2.process(settings, "ui", "ui", "andoria-texture-atlas");
		atlas = new TextureAtlas("ui/andoria-texture-atlas.atlas");
		atlasGenerated = true;
		return atlas;
	}

	/**
	 * Copies resources (files or folders) from a jar file to the filesystem.
	 *
	 * @param fileName the name of the file which shall be copied
	 * @param overwrite if false file will only be extracted if it doesn't exists already in the FS;
	 *                  if true file will be extracted always and overwrited any already existing one
	 */
	public static void copyFromJarToFS(String fileName, boolean overwrite) {
		final File file = new File(fileName);
		InputStream in;

		try {
			if(!file.exists() || overwrite) {
				in = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
				if(in == null) {
					throw new RuntimeException("Can't find " + file + " in jar file");
				}

				FileUtils.copyInputStreamToFile(in, file);
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
