package net.joesoft.andoria.utils;

import net.joesoft.andoria.ui.Resources;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Settings {
	public enum Key {
		ENGINE_RESOLUTION_X,
		ENGINE_RESOLUTION_Y,
		ENGINE_FULLSCREEN,
		ENGINE_LIGHT,
		ENGINE_WIREFRAME,
		ENGINE_SHOWCOORDINATESYSTEM,
		ENGINE_SHOWNORMALS,
		ENGINE_CAMERAMODE,
		ENGINE_TARGETPLATFORM,
		INPUT_MOUSE_SCROLLSPEED,
		INPUT_MOUSE_SPEED,
		UI_SHOWFPS;

		@Override
		public String toString() {
			return name().toLowerCase().replace('_', '.');
		}
	}

	private static final Logger log = LoggerFactory.getLogger(Settings.class);
    private static final HashMap<String, String> properties = new LinkedHashMap<String, String>();
	private static boolean loaded = false;
	private static final String COMMENT = "_COMMENT";
	private static final String EMPTY_LINE = "_EMPTY_LINE";
	private static final File configFile = new File("./settings.properties");

	private static void load() {
		if(loaded) {
			return;
		}

		// copy settings.properties from the jar to the root directory
		Resources.copyFromJarToFS("settings.properties", false);

		// copy content from file to map
		InputStream in = null;
		try {
			in = new FileInputStream(configFile);
			final List<String> lines = IOUtils.readLines(in);

			for(String line : lines) {
				if(line.trim().isEmpty()) {
					properties.put(EMPTY_LINE + UUID.randomUUID(), "");
				} else if (line.trim().startsWith("#")) {
					properties.put(COMMENT + UUID.randomUUID(), line);
				} else {
					final String[] keyValue = line.trim().split("=");
					properties.put(keyValue[0].trim().toLowerCase(), keyValue[1].trim());
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

	public static Integer getInteger(Key key) {
		load();
		return Integer.parseInt(properties.get(key.toString()));
	}

	public static void setInteger(Key key, Integer value) {
		properties.put(key.toString(), value.toString());
		save();
	}

	public static Float getFloat(Key key) {
		load();
		return Float.parseFloat(properties.get(key.toString()));
	}

	public static void setFloat(Key key, Float value) {
		properties.put(key.toString(), value.toString());
		save();
	}

	public static Boolean getBoolean(Key key) {
		load();
		return Boolean.parseBoolean(properties.get(key.toString()));
	}

	public static void setBoolean(Key key, Boolean value) {
		properties.put(key.toString(), value.toString());
		save();
	}

	public static String getString(Key key) {
		load();
		return properties.get(key.toString());
	}

	public static void setString(Key key, String value) {
		properties.put(key.toString(), value);
		save();
	}
}
