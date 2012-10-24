package net.joesoft.andoria.utils;

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
			if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
				startTime = SystemClock.elapsedRealtime();
			} else {
				startTime = System.currentTimeMillis();
			}

			running = true;
		}
	}

	/**
	 * Returns the time elapsed in milliseconds till the last call of the start() method.
	 * This method does not stop the time counting.
	 *
	 * @return elapsed time in milliseconds
	 */
	public long elapsedTime() {
		if (Properties.getTargetPlatform().equals(TargetPlatform.ANDROID)) {
			return SystemClock.elapsedRealtime() - startTime;
		} else {
			return System.currentTimeMillis() - startTime;
		}
	}

    /**
     * Mesures the time (in milliseconds) past after the last call of the start() mehtod
	 *
     * @return the time between start() and stop() was called
     */
	public long stop() {
		running = false;
		return elapsedTime();
	}
}
