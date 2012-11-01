package net.joesoft.andoria.utils;

import net.joesoft.andoria.input.KeyboardProcessor;
import net.joesoft.andoria.input.MouseProcessor;

public class Context {
	public static TargetPlatform targetPlatform = TargetPlatform.PC;
	public static int resolutionX = 1280;
	public static int resolutionY = 800;
	public static int logLevel = LogLevel.INFO;
	public static float scrollSpeed = 30f;
	public static GameCamera camera = new GameCamera();
	public static boolean light = true;
	public static boolean wireframe = false;
	public static boolean showCoordinateSystem = false;
	public static boolean showNormals = false;
	public static final KeyboardProcessor keyboardProcessor = new KeyboardProcessor();
	public static final MouseProcessor mouseProcessor = new MouseProcessor();
}
