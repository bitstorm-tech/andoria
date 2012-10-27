package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.Camera;
import net.joesoft.andoria.input.KeyboardProcessor;
import net.joesoft.andoria.input.MouseProcessor;

public class Context {
	public static TargetPlatform targetPlatform = TargetPlatform.PC;
	public static int resolutionX = 1280;
	public static int resolutionY = 800;
	public static int logLevel = LogLevel.INFO;
	public static float scrollSpeed = 30f;
	public static Camera camera = null;
	public static boolean light = false;
	public static boolean wireframe = false;
	public static boolean showCoordinateSystem = true;
	public static final KeyboardProcessor keyboardProcessor = new KeyboardProcessor();
	public static final MouseProcessor mouseProcessor = new MouseProcessor();
}
