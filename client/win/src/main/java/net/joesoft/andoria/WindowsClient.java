package net.joesoft.andoria;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.joesoft.andoria.utils.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowsClient {
	private static Logger log = LoggerFactory.getLogger(WindowsClient.class);
	public static void main(String[] args) {
		try {
			final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.title = "Andoria [developer preview]";
			config.width = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_X);
			config.height = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_Y);
	//		config.fullscreen = true;
			new LwjglApplication(new AndoriaGame(), config);
		} catch(Throwable t) {
			log.error("Error while running Andoria", t);
		} finally {
			log.info("Andoria stopped");
		}
	}
}
