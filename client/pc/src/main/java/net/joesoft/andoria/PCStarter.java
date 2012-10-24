package net.joesoft.andoria;

import net.joesoft.andoria.utils.LogLevel;
import net.joesoft.andoria.utils.Properties;
import net.joesoft.andoria.utils.TargetPlatform;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class PCStarter {
	public static void main(String[] args) {
		Properties.setTargetPlatform(TargetPlatform.PC);
		Properties.setLogLevel(LogLevel.INFO);
		new LwjglApplication(new AndoriaGame(), "Andoria", 1280, 800, false);
	}
}
