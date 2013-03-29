package net.joesoft.andoria.brain.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class ClientCommunicator extends Communicator {
	private static final Logger log = LoggerFactory.getLogger(ClientCommunicator.class);

	/**
	 * Creates a connection for the user with the given id. If the user has already a connection
	 * opened, the call of this method does nothing.
	 *
	 * @param userId the id of the user who wants to open a connection
	 * @return true if connection was successful, false if not
	 */
	public boolean connect(int userId) {
		Socket socket;

		try {
			log.debug("Before new Socket()");
			socket = new Socket("localhost", 14488);
			log.debug("After new Socket()");
		} catch (IOException e) {
			log.error("Can't connect to brain", e);
			return false;
		}

		openStreams(socket);
		log.info("Connected to the brain ... I am part of the swarm");
		return true;
	}
}
