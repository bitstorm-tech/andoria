package net.joesoft.andoria.client.net;

import net.joesoft.andoria.brain.net.ServerCommunicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The CommunicationManager manages the incoming (and only the incoming!) commands sended by
 * the Andoria server. Outgoing commands must be handled seperately. All incoming commands
 * are stored in a queue which must be set in the constructor of this class.
 */
public class CommunicationManager {
	private final Logger log = LoggerFactory.getLogger(CommunicationManager.class);
	private final ServerCommunicator communicator;

	/**
	 * Constructs a new CommunicationManager. The given communicator must already connected to
	 * the server. The CommunicationManager will <b>not</b> doing the connection work. If you
	 * pass an unconnected Communicator, the CommunicationManager will not work!
	 *
	 * @param communicator the Communicator which must be already connected to a server
	 */
	public CommunicationManager(ServerCommunicator communicator) {
		this.communicator = communicator;
	}
}
