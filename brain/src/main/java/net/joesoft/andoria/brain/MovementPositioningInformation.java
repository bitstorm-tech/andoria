package net.joesoft.andoria.brain;

import java.io.Serializable;

public class MovementPositioningInformation implements Serializable {
	public final int id;
	/** the actual position of a object */
	public float x, y, z;

	/** the direction (as unit length direction vector) a objects moves to or looks at */
	public float dirX, dirY, dirZ;

	/** the movement speed */
	public float speed;

	public MovementPositioningInformation(int id) {
		this.id = id;
	}

	public void update(MovementPositioningInformation other) {
		x = other.x;
		y = other.y;
		z = other.z;
		dirX = other.dirX;
		dirY = other.dirY;
		dirZ = other.dirZ;
		speed = other.speed;
	}
}
