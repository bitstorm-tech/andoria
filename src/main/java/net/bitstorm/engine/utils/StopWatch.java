package net.bitstorm.engine.utils;

public class StopWatch {
	private long startTime = 0;
	private boolean running = false;

    /**
     * Starts the time counting. If the watch is already running, calling this method
	 * has no effect.
     */
	public void start() {
		if(!running) {
			startTime = System.currentTimeMillis();
			running = true;
		}
	}

	/**
	 * Resetting the watch.
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
		return System.currentTimeMillis() - startTime;
	}
}
