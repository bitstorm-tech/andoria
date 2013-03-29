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

	/** userId to check if the user is allowed to do this move command */
	public int userId;

	public MoveCmd(int id) {
		super(id);
	}
}
