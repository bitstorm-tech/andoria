package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.Camera;
import net.joesoft.andoria.input.KeyboardProcessor;
import net.joesoft.andoria.input.MouseProcessor;

public class Context {
	public static TargetPlatform targetPlatform = TargetPlatform.PC;
	public static int resolutionX = 1280;
	public static int resolutionY = 800;
	public static int logLevel = LogLevel.INFO;
	public static float scrollDistance = 0.001f;
	public static Camera camera = null;
	public static boolean light = false;
	public static KeyboardProcessor keyboardProcessor = new KeyboardProcessor();
	public static MouseProcessor mouseProcessor = new MouseProcessor();
}
