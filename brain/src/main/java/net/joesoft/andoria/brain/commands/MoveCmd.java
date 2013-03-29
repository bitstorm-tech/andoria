package net.joesoft.andoria.brain.commands;

/**
 * When the client receives this command, the object with the given id starts
 * to move in the given direction with the given speed.
 */
public class MoveCmd extends Command {
	/** direction */
	public float x, y, z;

	/** speed */
	public float speed;

	public MoveCmd(int id) {
		super(id);
	}
}
