package net.joesoft.andoria.utils;

public class Log {
	private final String loggerName;

	public Log(final Class<?> clazz) {
		loggerName = clazz.getCanonicalName();
	}

	public void debug(final Object message) {
		if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
			android.util.Log.d(loggerName, message.toString());
		} else {
			System.out.println("[DEBUG] Andoria Client: " + message);
		}
	}

	public void info(final Object message) {
		if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
			android.util.Log.i(loggerName, message.toString());
		} else {
			System.out.println("[INFO] Andoria Client: " + message);
		}
	}

	public void warn(final Object message) {
		if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
			android.util.Log.w(loggerName, message.toString());
		} else {
			System.out.println("[WARNING] Andoria Client: " + message);
		}
	}

	public void error(final Object message) {
		if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
			android.util.Log.e(loggerName, message.toString());
		} else {
			System.out.println("[ERROR] Andoria Client: " + message);
		}
	}
}
