package net.joesoft.andoria.brain.net;

import net.joesoft.andoria.brain.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The CommunicationManager manages the incoming (and only the incoming!) commands sent by
 * the Andoria server. Outgoing commands must be handled separately. All incoming commands
 * are stored in a queue which must be set in the constructor of this class.
 */
public class Communicator implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(Communicator.class);
	private final ConcurrentLinkedQueue<Command> incomingCommands = new ConcurrentLinkedQueue<Command>();
	private boolean isRunning = false;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	protected void openStreams(Socket socket) {
		this.socket = socket;

		try {
			log.info("Opening streams");
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			log.info("Streams opened ... communication channels established");
		} catch (IOException e) {
			//TODO nasty to throw a RuntimeException here!
			throw new RuntimeException(e);
		}
	}

	public boolean sendCommand(Command cmd) {
		if(!channelsAlive()) {
			return false;
		}

		try {
			out.writeObject(cmd);
		} catch (IOException e) {
			log.error("Error while sending command: {}", e.getMessage());
			return false;
		}

		return true;
	}

	public Command receiveCommand() {
		if(!channelsAlive()) {
			return null;
		}

		try {
			return (Command) in.readObject();
		} catch (IOException e) {
			log.error("Error while receive command: {}", e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void run() {
		isRunning = true;
		while(true) {
			final Command command = receiveCommand();
			incomingCommands.add(command);

			if(incomingCommands.size() > 500000) {
				log.warn("Too many queued incoming commands: {}", incomingCommands.size());
			}
		}
	}

	public ConcurrentLinkedQueue<Command> getCommandQueue() {
		return incomingCommands;
	}

	public boolean isRunning() {
		return isRunning;
	}

	private boolean channelsAlive() {
		if(socket.isClosed() || !socket.isConnected() || socket.isInputShutdown() || socket.isOutputShutdown()) {
			log.error("Communication is broken");
			log.error("Socket closed:          {}", socket.isClosed());
			log.error("Socket connected:       {}", socket.isConnected());
			log.error("Input Stream shutdown:  {}", socket.isInputShutdown());
			log.error("Output Stream shutdown: {}", socket.isOutputShutdown());
			return false;
		}

		return true;
	}
}
