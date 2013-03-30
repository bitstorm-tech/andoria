package net.joesoft.andoria.brain.commands;

import java.io.Serializable;

public abstract class Command implements Serializable {
	public final int objectId;

	public Command(int objectId) {
		this.objectId = objectId;
	}
}
