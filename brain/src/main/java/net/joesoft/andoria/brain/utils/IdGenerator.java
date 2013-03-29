package net.joesoft.andoria.brain.utils;

public class IdGenerator {
	private static int id = 1;

	public static int nextId() {
		return id++;
	}
}
