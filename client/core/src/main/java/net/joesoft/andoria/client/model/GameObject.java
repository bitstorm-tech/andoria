package net.joesoft.andoria.client.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class GameObject {
	protected Vector3 position = new Vector3();
	protected float directionAngle = 0;

	public GameObject() {
	}

	public GameObject(float pPositionX, float pPositionY, float pPositionZ) {
		position = new Vector3(pPositionX, pPositionY, pPositionZ);
	}

	public Vector3 getPosition() {
		return position;
	}
}
