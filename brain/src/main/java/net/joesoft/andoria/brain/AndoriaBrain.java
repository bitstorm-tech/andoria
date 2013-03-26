package net.joesoft.andoria.brain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndoriaBrain extends Thread {
	private final static Logger log = LoggerFactory.getLogger(AndoriaBrain.class);
	private long lastHeartBeat;

	@Override
	public void run() {
		while(true) {
			if(System.currentTimeMillis() - lastHeartBeat < 100) {
				continue;
			}

			lastHeartBeat = System.currentTimeMillis();
			log.debug("Heartbeat");
		}
	}
}
