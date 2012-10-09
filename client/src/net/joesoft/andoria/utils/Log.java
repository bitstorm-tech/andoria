package net.joesoft.andoria.utils;

public class Log {
	private final String loggerName;

	public Log(final Class<?> clazz) {
		loggerName = clazz.getCanonicalName();
	}

	public void debug(final String message) {
		android.util.Log.d(loggerName, message);
	}

	public void info(final String message) {
		android.util.Log.i(loggerName, message);
	}

	public void warn(final String message) {
		android.util.Log.w(loggerName, message);
	}

	public void error(final String message) {
		android.util.Log.e(loggerName, message);
	}
}
