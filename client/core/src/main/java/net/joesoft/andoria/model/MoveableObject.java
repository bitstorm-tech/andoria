package net.joesoft.andoria.model;

import com.badlogic.gdx.math.Vector3;

public abstract  class MoveableObject extends GameObject {

	public MoveableObject() {
		super();
	}

	public MoveableObject(float x, float y, float z) {
		super(x, y, z);
	}

	/**
	 * Moves the object to the given position.
	 *
	 * @param x position x
	 * @param y position y
	 * @param z position z
	 *
	 * @return the new position
	 */
	public Vector3 move(float x, float y, float z) {
		return  getPosition().add(x, y, z);
	}
}
