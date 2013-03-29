package net.joesoft.andoria.brain.commands;

/**
 * When the client receives this command, the object with the given id
 * stopped moving.
 */
public class StopMoveCmd extends Command {
	public StopMoveCmd(int id) {
		super(id);
	}
}
