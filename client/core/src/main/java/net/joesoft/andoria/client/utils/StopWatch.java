package net.joesoft.andoria.client.utils;

import android.os.SystemClock;

public class StopWatch {
	private long startTime = 0;
	private boolean running = false;

    /**
     * Starts the time counting. If the watch is already running, calling this method
	 * has no effect.
     */
	public void start() {
		if(!running) {
			if (Settings.getString(Settings.Key.ENGINE_TARGETPLATFORM).equalsIgnoreCase(TargetPlatform.ANDROID.name())) {
				startTime = SystemClock.elapsedRealtime();
			} else {
				startTime = System.currentTimeMillis();
			}

			running = true;
		}
	}

	/**
	 * Reseting the watch.
	 */
	public void reset() {
		running = false;
		start();
	}

	/**
	 * Returns the time elapsed in milliseconds till the last call of the start() method.
	 * This method does not stop the time counting.
	 *
	 * @return elapsed time in milliseconds
	 */
	public long elapsedTime() {
		if (Settings.getString(Settings.Key.ENGINE_TARGETPLATFORM).equalsIgnoreCase(TargetPlatform.ANDROID.name())) {
			return SystemClock.elapsedRealtime() - startTime;
		} else {
			return System.currentTimeMillis() - startTime;
		}
	}
}
