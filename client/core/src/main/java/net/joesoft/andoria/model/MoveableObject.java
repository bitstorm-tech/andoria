package net.joesoft.andoria.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract  class MoveableObject extends GameObject {
	protected final Vector2 direction = new Vector2(0, 0);
	protected float speed;

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
	public Vector3 moveObject(float x, float y, float z) {
		return  getPosition().add(x, y, z);
	}

	public void setDirection(float x, float y) {
		direction.set(x, y);
	}

	public Vector2 getDirection() {
		return direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
