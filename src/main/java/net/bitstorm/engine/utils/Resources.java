package net.bitstorm.engine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Resources {
	private static final Logger log = LoggerFactory.getLogger(Resources.class);
	private static boolean init = false;
	private static Skin skin;

	public static Skin getSkin() {
		if(init) {
			return skin;
		}

		final FileHandle skinFile = Gdx.files.classpath(Settings.getString(Settings.Key.ENGINE_SKIN_FILE));
		final TextureAtlas atlas = new TextureAtlas(Settings.getString(Settings.Key.ENGINE_TEXTUREATLAS_FILE));
		skin = new Skin(skinFile, atlas);
		init = true;
		return skin;
	}

	/**
	 * Copies resources (files or folders) from a jar file to the filesystem.
	 *
	 * @param fileName the name of the file which shall be copied
	 * @param overwrite if false file will only be extracted if it doesn't exists already in the FS;
	 *                  if true file will be extracted always and overwrites any already existing one
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
