package net.bitstorm.engine;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import net.bitstorm.engine.utils.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClient {
	private static Logger log = LoggerFactory.getLogger(TestClient.class);
	public static void main(String[] args) {

		try {
			generateTextureAtlas();
			final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.title = "BitStorm engine test client";
			config.width = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_X);
			config.height = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_Y);
			config.resizable = false;
			config.fullscreen = Settings.getBoolean(Settings.Key.ENGINE_FULLSCREEN);
			new LwjglApplication(new AndoriaGame(), config);
		} catch(Throwable t) {
			log.error("Error while running BitStorm test client", t);
			System.exit(-666);
		} finally {
			log.info("BitStorm engine stopped");
		}
	}

	private static void generateTextureAtlas() {
//		Resources.copyFromJarToFS("ui/checkbox-checked.png", false);
//		Resources.copyFromJarToFS("ui/checkbox-unchecked.png", false);
//		Resources.copyFromJarToFS("ui/settings-menu-background.png", false);

		final TexturePacker2.Settings settings = new TexturePacker2.Settings();
		settings.maxHeight = 4096;
		settings.maxWidth = 4096;
		TexturePacker2.process(settings, "test-classes/ui", "test-classes/ui", "texture-atlas");
	}
}
