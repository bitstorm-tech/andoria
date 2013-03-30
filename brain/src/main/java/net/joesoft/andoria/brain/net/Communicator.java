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
 * The Communicator manages the communication between server and client. Because the Communicator
 * implements the {@link Runnable} interface, you can run it in a own thread.
 * <p>
 * When running in his own thread the Communicator queues all incoming commands automatically. In this
 * case you must use the method {@link net.joesoft.andoria.brain.net.Communicator#getCommand()}.
 * <b>Attention:</b> you have to take care to call the method {@link net.joesoft.andoria.brain.net.Communicator#getCommand()}
 * often enough, otherwise the growing queue will cause memory problems. You get a warning in the log if
 * the size becomes too big.
 * </p>
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

	/**
	 * @param cmd the Command which shall be transmitted
	 * @return true if transmission was successful, otherwise false
	 */
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

	/**
	 * This method must be used if the Communicator is not running in his own thread.
	 *
	 * @return the next incoming command
	 */
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

	@Override
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

	/**
	 * @return true if commands are in the queue, otherwise false
	 */
	public boolean hasCommandsQueued() {
		return !incomingCommands.isEmpty();
	}

	/**
	 * This method must be used if the Communicator is running in his own thread.
	 *
	 * @return the oldest command from the queue which will be also removed from the queue
	 */
	public Command getCommand() {
		return incomingCommands.remove();
	}

	/**
	 * @return true if this Communicator is running in a own thread
	 */
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
