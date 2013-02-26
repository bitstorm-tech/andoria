package net.joesoft.andoria.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Settings {
	private static final Logger log = LoggerFactory.getLogger(Settings.class);
    private static final HashMap<String, String> properties = new LinkedHashMap<String, String>();
	private static boolean loaded = false;
	private static final String COMMENT = "_COMMENT";
	private static final String EMPTY_LINE = "_EMPTY_LINE";
	private static final File configFile = new File("./settings.properties");

	private static final String ENGINE_RESOLUTION_X =           "engine.resolution.x";
	private static final String ENGINE_RESOLUTION_Y =           "engine.resolution.y";
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

		// copy settings.properties from the jar to the root directory
		InputStream in = null;
		OutputStream out = null;
		try {
			if(!configFile.exists()) {
				final URL settingsUrl = ClassLoader.getSystemResource(configFile.getName());
				if(settingsUrl == null) {
					throw new RuntimeException("Can't find settings.properties in jar file");
				}
				in = settingsUrl.openStream();
				out = new FileOutputStream(configFile);
				IOUtils.copy(in, out);
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}

		// copy content from file to map
		try {
			in = new FileInputStream(configFile);
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
		final StringBuilder builder = new StringBuilder();

		for(Map.Entry<String, String> entry : properties.entrySet()) {
			final String key = entry.getKey();
			if(key.startsWith(COMMENT)) {
				builder.append(entry.getValue()).append("\n");
			} else if(key.startsWith(EMPTY_LINE)) {
				builder.append("\n");
			} else {
				builder.append(key);
				builder.append(" = ");
				builder.append(entry.getValue()).append("\n");
			}
		}

		FileOutputStream out = null;

		try {
			out = new FileOutputStream(configFile);
			IOUtils.write(builder.toString(), out);
		} catch (FileNotFoundException e) {
			log.error("Can't save settings", e);
		} catch (IOException e) {
			log.error("Can't save settings", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public static int getResolutionX() {
		load();
		return Integer.parseInt(properties.get(ENGINE_RESOLUTION_X));
	}

	public static void setResolutionX(int resolutionX) {
		properties.put(ENGINE_RESOLUTION_X, Integer.toString(resolutionX));
		save();
	}

	public static int getResolutionY() {
		load();
		return Integer.parseInt(properties.get(ENGINE_RESOLUTION_Y));
	}

	public static void setResolutionY(int resolutionY) {
		properties.put(ENGINE_RESOLUTION_Y, Integer.toString(resolutionY));
		save();
	}

	public static float getScrollSpeed() {
		load();
		return Float.parseFloat(properties.get(INPUT_SCROLL_SPEED));
	}

	public static void setScrollSpeed(float scrollSpeed) {
		properties.put(INPUT_SCROLL_SPEED, Float.toString(scrollSpeed));
		save();
	}

	public static float getMouseSpeed() {
		load();
		return Float.parseFloat(properties.get(INPUT_MOUSE_SPEED));
	}

	public static void setMouseSpeed(float mouseSpeed) {
		properties.put(INPUT_MOUSE_SPEED, Float.toString(mouseSpeed));
		save();
	}

	public static boolean isLight() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_LIGHT));
	}

	public static void setLight(boolean light) {
		properties.put(ENGINE_LIGHT, Boolean.toString(light));
		save();
	}

	public static boolean isWireframe() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_WIREFRAME));
	}

	public static void setWireframe(boolean wireframe) {
		properties.put(ENGINE_WIREFRAME, Boolean.toString(wireframe));
		save();
	}

	public static boolean isShowCoordinateSystem() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_SHOW_COORDINATE_SYSTEM));
	}

	public static void setShowCoordinateSystem(boolean showCoordinateSystem) {
		properties.put(ENGINE_SHOW_COORDINATE_SYSTEM, Boolean.toString(showCoordinateSystem));
		save();
	}

	public static boolean isShowNormals() {
		load();
		return Boolean.parseBoolean(properties.get(ENGINE_SHOW_NORMALS));
	}

	public static void setShowNormals(boolean showNormals) {
		properties.put(ENGINE_SHOW_NORMALS, Boolean.toString(showNormals));
		save();
	}

	public static CameraMode getCameraMode() {
		load();
		return CameraMode.valueOf(properties.get(ENGINE_CAMERA_MODE).toUpperCase());
	}

	public static void setCameraMode(CameraMode cameraMode) {
		properties.put(ENGINE_CAMERA_MODE, cameraMode.name());
		save();
	}

	public static TargetPlatform getTargetPlatform() {
		load();
		return TargetPlatform.valueOf(properties.get(ENGINE_TARGET_PATFORM).toUpperCase());
	}

	public static void setTargetPlatfrom(TargetPlatform targetPlatfrom) {
		properties.put(ENGINE_TARGET_PATFORM, targetPlatfrom.name());
		save();
	}
}
