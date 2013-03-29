package net.joesoft.andoria.brain.commands;

/**
 * When the client receives this command, the direction of the object with the
 * given id changed.
 */
public class ChangeDirectionCmd extends Command {
	/** direction */
	public float x, y, z;

	public ChangeDirectionCmd(int id) {
		super(id);
	}
}
