package net.joesoft.andoria.utils;

public class Log {
	private final String loggerName;

	public Log(final Class<?> clazz) {
		loggerName = clazz.getCanonicalName();
	}

	public void debug(final Object message) {
		if(Context.getLogLevel() <= LogLevel.DEBUG) {
			if (Context.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
				android.util.Log.d(loggerName, message.toString());
			} else {
				System.out.println("[DEBUG] [" + loggerName+ "] " + message);
			}
		}
	}

	public void info(final Object message) {
		if(Context.getLogLevel() <= LogLevel.INFO) {
			if (Context.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
				android.util.Log.i(loggerName, message.toString());
			} else {
				System.out.println("[INFO] [" + loggerName+ "] " + message);
			}
		}
	}

	public void warning(final Object message) {
		if(Context.getLogLevel() <= LogLevel.WARNING) {
			if (Context.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
				android.util.Log.w(loggerName, message.toString());
			} else {
				System.out.println("[WARNING] [" + loggerName+ "] " + message);
			}
		}
	}

	public void error(final Object message) {
		if(Context.getLogLevel() <= LogLevel.ERROR) {
			if (Context.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
				android.util.Log.e(loggerName, message.toString());
			} else {
				System.out.println("[ERROR] [" + loggerName+ "] " + message);
			}
		}
	}
}
