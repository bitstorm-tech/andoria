package net.joesoft.andoria.utils;

public class LogLevel {
	public static final int DEBUG =   0;
	public static final int INFO =    1;
	public static final int WARNING = 2;
	public static final int ERROR =   3;

	public static String translate(int level) {
		switch (level) {
			case 0: return "DEBUG";
			case 1: return "INFO";
			case 2: return "WARNING";
			case 3: return "ERROR";
			default: return "UNDEFINED";
		}
	}

	public static int translate(String level) {
		if(level.toLowerCase().equals("debug")) {
			return 0;
		}
		if(level.toLowerCase().equals("info")) {
			return 1;
		}
		if(level.toLowerCase().equals("waring")) {
			return 2;
		}
		if(level.toLowerCase().equals("error")) {
			return 3;
		}

		throw new RuntimeException("Unknown log level: " + level);
	}
}
