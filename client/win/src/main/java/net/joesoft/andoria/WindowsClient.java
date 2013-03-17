package net.joesoft.andoria;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.joesoft.andoria.utils.Settings;

public class WindowsClient {
	public static void main(String[] args) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Andoria [developer preview]";
		config.width = Settings.getResolutionX();
		config.height = Settings.getResolutionY();
//		config.fullscreen = true;
		new LwjglApplication(new AndoriaGame(), config);
	}
}
