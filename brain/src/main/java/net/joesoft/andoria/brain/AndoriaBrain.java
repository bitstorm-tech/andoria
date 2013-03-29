package net.joesoft.andoria.brain;

import net.joesoft.andoria.brain.commands.SpawnObjectCmd;
import net.joesoft.andoria.brain.net.CommunicationManager;
import net.joesoft.andoria.brain.net.Communicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AndoriaBrain implements Runnable {
	private final static Logger log = LoggerFactory.getLogger(AndoriaBrain.class);
	private final CommunicationManager comMgr = new CommunicationManager();
	private final Map<Integer, Communicator> communicators = new HashMap<Integer, Communicator>();
	private boolean playerSpawned = false;

	public AndoriaBrain() {
		log.info("A new brain was born");
		new Thread(comMgr).start();
	}

	public void run() {
		while(true) {
			communicators.putAll(comMgr.getCommunicators());
			spawnCommunicationThreads(communicators.values());

			if(communicators.size() > 0) {
				final Communicator com = communicators.get(1);
				if(!playerSpawned) {
					final SpawnObjectCmd cmd = new SpawnObjectCmd(1);
					cmd.objectType = ObjectType.PLAYER;
					com.sendCommand(cmd);
					playerSpawned = true;
				}
			}
		}
	}

	private void processCommands() {

	}

	private void simulateLight() {

	}

	private void spawnCommunicationThreads(Collection<Communicator> communicators) {
		//TODO same thread is started multiple times
		for(Communicator communicator : communicators) {
			if(!communicator.isRunning()) {
				log.debug("Spawning new communication thread");
				new Thread(communicator).start();
			}
		}
	}

	public static void main(String[] args) {
		new AndoriaBrain().run();
	}
}
