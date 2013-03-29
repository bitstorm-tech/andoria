package net.joesoft.andoria.brain.net;

import net.joesoft.andoria.brain.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communicator {
	private static final Logger log = LoggerFactory.getLogger(Communicator.class);
	private Socket socket;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;

	protected void openStreams(Socket socket) {
		this.socket = socket;

		try {
			log.info("Opening streams");
			objectOut = new ObjectOutputStream(socket.getOutputStream());
			objectOut.flush();
			objectIn = new ObjectInputStream(socket.getInputStream());
			log.info("Streams opened");
		} catch (IOException e) {
			//TODO nasty to throw a RuntimeException here!
			throw new RuntimeException(e);
		}
	}

	public boolean sendCommand(Command cmd) {
		if(socket.isClosed() || !socket.isConnected() || socket.isInputShutdown() || socket.isOutputShutdown()) {
			log.error("Socket is already closed");
			log.error("Socket closed:          {}", socket.isClosed());
			log.error("Socket connected:       {}", socket.isConnected());
			log.error("Input Stream shutdown:  {}", socket.isInputShutdown());
			log.error("Output Stream shutdown: {}", socket.isOutputShutdown());
			return false;
		}

		try {
			objectOut.writeObject(cmd);
		} catch (IOException e) {
			log.error("Error while sending command", e);
			return false;
		}

		return true;
	}

	public Command receiveCommand() {
		if(socket.isClosed() || !socket.isConnected() || socket.isInputShutdown() || socket.isOutputShutdown()) {
			log.error("Socket is already closed");
			log.error("Socket closed:          {}", socket.isClosed());
			log.error("Socket connected:       {}", socket.isConnected());
			log.error("Input Stream shutdown:  {}", socket.isInputShutdown());
			log.error("Output Stream shutdown: {}", socket.isOutputShutdown());
			return null;
		}

		try {
			return (Command)objectIn.readObject();
		} catch (IOException e) {
			log.error("Error while reading commands", e);
			return null;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
