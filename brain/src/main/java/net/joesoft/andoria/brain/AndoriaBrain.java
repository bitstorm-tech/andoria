package net.joesoft.andoria.brain;

import net.joesoft.andoria.brain.commands.StopMoveCmd;
import net.joesoft.andoria.brain.net.CommunicationManager;
import net.joesoft.andoria.brain.net.Communicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AndoriaBrain extends Thread {
	private final static Logger log = LoggerFactory.getLogger(AndoriaBrain.class);
	private final CommunicationManager comMgr = new CommunicationManager();

	public AndoriaBrain() {
		log.info("A new brain was born");
		new Thread(comMgr).run();
	}

	@Override
	public void run() {
		while(true) {
			final Map<Integer, Communicator> coms = comMgr.getCommunicators();
			if(coms.size() > 0) {
				final Communicator com = coms.get(1);
				log.info("Sending stop move command");
				com.sendCommand(new StopMoveCmd(1));
			}
			try {
				Thread.sleep(429);
			} catch (InterruptedException e) {
			}
		}
	}

//	/**
//	 * Get the actual movement positioning information (MPI) for the given object. This method
//	 * should be called very often to avoid becoming asynchronous.
//	 *
//	 * @param id the id of the object for which the MPI shall be enquired
//	 * @return the movement positioning information of the given object or null if id is unknown
//	 */
//	public MovementPositioningInformation getMPI(int id) {
//		final GameObject o = gameObjects.get(id);
//
//		return o == null ? null : o.mpi;
//	}
//
//	/**
//	 * Set the movement positioning information (MPI). This is only valid for game objects which are not fully
//	 * under control of the brain. For instance it is allowed to set the MPI for its own player but it
//	 * is not allowed for NPCs (Non Player Characters).
//	 *
//	 * @param mpi the new movement positioning information
//	 * @throws IllegalArgumentException in case the movement positioning information belongs to a object which is
//	 * under fully control of the brain
//	 */
//	public void setMPI(MovementPositioningInformation mpi) throws IllegalArgumentException {
//		final int id = mpi.id;
//		if(id != player.id) {
//			throw new IllegalArgumentException("You are not allowed to update MPI for object with id " + id);
//		}
//
//		gameObjects.get(id).mpi.update(mpi);
//	}
//
//	/**
//	 * Returns all objects which are nearby the player.
//	 *
//	 * @return all objects nearby the player
//	 */
//	public Collection<GameObject> getNearObjects() {
//		return gameObjects.values();
//	}

	private void simulateLight() {

	}

	public static void main(String[] args) {
		new AndoriaBrain().run();
	}
}
