package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.Camera;

public class Properties {
	private static TargetPlatform targetPlatform = TargetPlatform.PC;
	private static int resolutionX = 1280;
	private static int resolutionY = 800;
	private static int logLevel = LogLevel.INFO;
	private static float scrollDistance = 0.001f;
	private static Camera camera;
	private static boolean light = false;

	public static boolean getLight() {
		return light;
	}

	public static void setLight(boolean lightning) {
		Properties.light = lightning;
	}

	public static Camera getCamera() {
		return camera;
	}

	public static void setCamera(Camera camera) {
		Properties.camera = camera;
	}

	public static float getScrollDistance() {
		return scrollDistance;
	}

	public static void setScrollDistance(int scrollSpeed) {
		Properties.scrollDistance = scrollSpeed;
	}

	public static int getLogLevel() {
		return logLevel;
	}

	public static void setLogLevel(int logLevel) {
		Properties.logLevel = logLevel;
	}

	public static TargetPlatform getTargetPlatform() {
		return targetPlatform;
	}

	public static void setTargetPlatform(TargetPlatform targetPlatform) {
		Properties.targetPlatform = targetPlatform;
	}

	public static int getResolutionY() {
		return resolutionY;
	}

	public static void setResolutionY(int resolutionY) {
		Properties.resolutionY = resolutionY;
	}

	public static int getResolutionX() {
		return resolutionX;
	}

	public static void setResolutionX(int resolutionX) {
		Properties.resolutionX = resolutionX;
	}
}
