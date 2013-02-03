package net.joesoft.andoria.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.sun.prism.impl.packrect.LevelSet;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Settings {
    private static final HashMap<String, String> properties = new LinkedHashMap<String, String>();
	private static boolean loaded = false;
	private static final String COMMENT = "_COMMENT";
	private static final String EMPTY_LINE = "_EMPTY_LINE";

	private static final String ENGINE_RESOLUTION_X =           "engine.resolution.x";
	private static final String ENGINE_RESOLUTION_Y =           "engine.resolution.y";
	private static final String ENGINE_LOG_LEVEL =              "engine.log.level";
	private static final String ENGINE_LIGHT =                  "engine.light";
	private static final String ENGINE_WIREFRAME =              "engine.wireframe";
	private static final String ENGINE_SHOW_COORDINATE_SYSTEM = "engine.showCoordinateSystem";
	private static final String ENGINE_SHOW_NORMALS =           "engine.showNormals";
	private static final String ENGINE_CAMERA_MODE =            "engine.cameraMode";
	private static final String ENGINE_TARGET_PATFORM =         "engine.targetPlatform";

	private static final String INPUT_SCROLL_SPEED = "input.mouse.scrollSpeed";
	private static final String INPUT_MOUSE_SPEED =  "input.mouse.speed";

	private static void load() {
		if(loaded) {
			return;
		}

		final File dest = new File("./settings.properties");

		// copy settings.properties from the jar to the root directory
		try {
			if(!dest.exists()) {
				final FileHandle handle = Gdx.files.classpath("settings.properties");
				if(!handle.exists()) {
					throw new IOException("Can't find settings.properties in jar file");
				}
				handle.copyTo(new FileHandle(dest));
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}

		FileInputStream in = null;
		try {
			in = new FileInputStream(dest);
			final List<String> lines = IOUtils.readLines(in);

			for(String line : lines) {
				if(line.trim().isEmpty()) {
					properties.put(EMPTY_LINE + System.currentTimeMillis(), "");
				} else if (line.trim().startsWith("#")) {
					properties.put(COMMENT + System.currentTimeMillis(), line);
				} else {
					final String[] keyValue = line.trim().split("=");
					properties.put(keyValue[0].trim(), keyValue[1].trim());
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}

		loaded = true;
	}

    private static void save() {

    }

	public static int getResolutionX() {
		load();
		return Integer.parseInt(properties.get(ENGINE_RESOLUTION_X));
	}

	public static void setResolutionX(int resolutionX) {
		properties.put(ENGINE_RESOLUTION_X, Integer.toString(resolutionX));
	}

	public static int getResolutionY() {
		load();
		return Integer.parseInt(properties.get(ENGINE_RESOLUTION_Y));
	}

	public static void setResolutionY(int resolutionY) {
		properties.put(ENGINE_RESOLUTION_Y, Integer.toString(resolutionY));
	}

	public static int getLogLevel() {
		load();
		return LogLevel.translate(properties.get(ENGINE_LOG_LEVEL));
	}

	public static void setLogLevel(int logLevel) {
		properties.put(ENGINE_LOG_LEVEL, LogLevel.translate(logLevel));
	}

	public static float getScrollSpeed() {
		load();
		return Float.parseFloat(properties.get(INPUT_SCROLL_SPEED));
	}

	public static void setScrollSpeed(float scrollSpeed) {
		properties.put(INPUT_SCROLL_SPEED, Float.toString(scrollSpeed));
	}

	public static float getMouseSpeed() {
		load();
		return Float.parseFloat(properties.get(INPUT_MOUSE_SPEED));
	}

	public static void setMouseSpeed(float mouseSpeed) {
		properties.put(INPUT_MOUSE_SPEED, Float.toString(mouseSpeed));
	}

	public static boolean isLight() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_LIGHT));
	}

	public static void setLight(boolean light) {
		properties.put(ENGINE_LIGHT, Boolean.toString(light));
	}

	public static boolean isWireframe() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_WIREFRAME));
	}

	public static void setWireframe(boolean wireframe) {
		properties.put(ENGINE_WIREFRAME, Boolean.toString(wireframe));
	}

	public static boolean isShowCoordinateSystem() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_SHOW_COORDINATE_SYSTEM));
	}

	public static void setShowCoordinateSystem(boolean showCoordinateSystem) {
		properties.put(ENGINE_SHOW_COORDINATE_SYSTEM, Boolean.toString(showCoordinateSystem));
	}

	public static boolean isShowNormals() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_SHOW_NORMALS));
	}

	public static void setShowNormals(boolean showNormals) {
		properties.put(ENGINE_SHOW_NORMALS, Boolean.toString(showNormals));
	}

	public static CameraMode getCameraMode() {
		load();
		return CameraMode.valueOf(properties.get(ENGINE_CAMERA_MODE).toUpperCase());
	}

	public static void setCameraMode(CameraMode cameraMode) {
		properties.put(ENGINE_CAMERA_MODE, cameraMode.name());
	}

	public static TargetPlatform getTargetPlatform() {
		load();
		return TargetPlatform.valueOf(properties.get(ENGINE_TARGET_PATFORM).toUpperCase());
	}

	public static void setTargetPlatfrom(TargetPlatform targetPlatfrom) {
		properties.put(ENGINE_TARGET_PATFORM, targetPlatfrom.name());
	}
}
