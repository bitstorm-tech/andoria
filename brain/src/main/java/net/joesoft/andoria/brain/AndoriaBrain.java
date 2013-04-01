package net.joesoft.andoria.brain;

import net.joesoft.andoria.brain.commands.PositionUpdateCmd;
import net.joesoft.andoria.brain.commands.SpawnObjectCmd;
import net.joesoft.andoria.brain.net.CommunicationManager;
import net.joesoft.andoria.brain.net.Communicator;
import net.joesoft.andoria.brain.utils.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AndoriaBrain implements Runnable {
	private final static Logger log = LoggerFactory.getLogger(AndoriaBrain.class);
	private final CommunicationManager comMgr = new CommunicationManager();
	private final Map<Integer, Communicator> communicators = new HashMap<Integer, Communicator>();
	private int playerId = -1;
	private int lightId = -1;

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
				if(playerId == -1) {
					playerId = IdGenerator.nextId();
					final SpawnObjectCmd cmd = new SpawnObjectCmd(playerId);
					cmd.objectType = ObjectType.PLAYER;
					com.sendCommand(cmd);
				}

				if(lightId == -1) {
					lightId = IdGenerator.nextId();
					final SpawnObjectCmd cmd = new SpawnObjectCmd(lightId);
					cmd.objectType = ObjectType.LIGHT;
					com.sendCommand(cmd);

					final PositionUpdateCmd cmd2 = new PositionUpdateCmd(lightId);
					cmd2.x = 5;
					cmd2.y = 5;
					cmd2.z = 5;
					com.sendCommand(cmd2);
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
				log.debug("Starting new communication thread");
				new Thread(communicator).start();
			}
		}
	}

	public static void main(String[] args) {
		new AndoriaBrain().run();
	}
}
