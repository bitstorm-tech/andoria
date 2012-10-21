package net.joesoft.andoria.utils;

public class Properties {
	private static TargetPlatform targetPlatform = TargetPlatform.ANDROID;
	private static int resolutionX = 1280;
	private static int resolutionY = 800;

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
