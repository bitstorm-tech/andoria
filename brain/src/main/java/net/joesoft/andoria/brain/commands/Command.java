package net.joesoft.andoria.brain.commands;

import java.io.Serializable;

public abstract class Command implements Serializable {
	public final int id;

	public Command(int id) {
		this.id = id;
	}
}
