package net.joesoft.andoria.brain.commands;

import net.joesoft.andoria.brain.ObjectType;

/**
 * When the client receives this command, a new object must be created because the player
 * is now (or very soon) able to see and interact with it.
 */
public class SpawnObjectCmd extends Command {
	public float x, y, z;
	public ObjectType objectType;

	public SpawnObjectCmd(int id) {
		super(id);
	}
}
