package net.joesoft.andoria.utils;

import android.os.SystemClock;

public class StopWatch {
	private long runTime = 0;

    /**
     * Starts the time counting
     */
	public void start() {
		if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
			runTime = SystemClock.elapsedRealtime();
		} else {
			runTime = System.currentTimeMillis();
		}
	}

    /**
     * Mesures the time (in milliseconds) past after the last call of the start() mehtod
     * @return the time between start() and stop() was called
     */
	public long stop() {
		if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
			return SystemClock.elapsedRealtime() - runTime;
		} else {
			return System.currentTimeMillis() - runTime;
		}
	}
}
