package net.joesoft.andoria.brain.commands;

/**
 * When the client receives this command, he is forced to update the position
 * of the object with the given id;
 */
public class PositionUpdateCmd extends Command {
	/** position */
	public float x, y, z;

	public PositionUpdateCmd(int id) {
		super(id);
	}
}
