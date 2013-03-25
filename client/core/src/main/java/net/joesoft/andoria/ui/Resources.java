package net.joesoft.andoria.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import net.joesoft.andoria.utils.Constants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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
		skinCreated = true;
		return skin;
	}

	private static TextureAtlas generateTextureAtlas() {
		copyFromJarToFS("ui", "ui", false);

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
	 * @param src the source file or directory inside the jar file
	 * @param dest the destination file or directory
	 * @param overwrite if false content will only be extracted if it doesn't exists already in the FS;
	 *                  if true content will be extracted always
	 */
	public static void copyFromJarToFS(String src, String dest, boolean overwrite) {
		final File destFile = new File(dest);

		try {
			if(!destFile.exists() || overwrite) {
				final URL resourceURL = ClassLoader.getSystemResource(src);
				if(resourceURL == null) {
					throw new RuntimeException("Can't find settings.properties in jar file");
				}

				final File srcFile = new File(resourceURL.toURI());

				if(srcFile.isFile()) {
					FileUtils.copyFile(srcFile, destFile);
				} else {
					FileUtils.copyDirectory(srcFile, destFile);
				}
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
