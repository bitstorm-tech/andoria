package net.joesoft.andoria.brain.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;

public class CommunicationManager implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(CommunicationManager.class);
	private final Map communicators = new Hashtable<Integer, Communicator>();

	public void listen() {
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(14488);
		} catch (IOException e) {
			log.error("Error while listening to port 14488" , e);
			throw new RuntimeException(e);
		}

		Socket socket;
		try {
			socket = serverSocket.accept();
			log.info("Client connected ... I am the swarm");
		} catch (IOException e) {
			log.error("Error while opening socket", e);
			throw new RuntimeException(e);
		}

		final Communicator communicator = new Communicator();
		communicator.openStreams(socket);
		communicators.put(1, communicator);
	}

	public Map<Integer, Communicator> getCommunicators() {
		return communicators;
	}

	@Override
	public void run() {
		listen();
	}
}
