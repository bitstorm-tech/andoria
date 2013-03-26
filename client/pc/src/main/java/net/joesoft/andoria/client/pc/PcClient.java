package net.joesoft.andoria.client.pc;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.joesoft.andoria.client.AndoriaGame;
import net.joesoft.andoria.client.utils.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PcClient {
	private static Logger log = LoggerFactory.getLogger(PcClient.class);
	public static void main(String[] args) {
		try {
			final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.title = "Andoria [developer preview]";
			config.width = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_X);
			config.height = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_Y);
			config.resizable = false;
			config.fullscreen = Settings.getBoolean(Settings.Key.ENGINE_FULLSCREEN);
			new LwjglApplication(new AndoriaGame(), config);
		} catch(Throwable t) {
			log.error("Error while running Andoria", t);
		} finally {
			log.info("Andoria stopped");
		}
	}
}
