package net.joesoft.andoria.client.model;

import com.badlogic.gdx.math.Vector3;

public abstract class GameObject {
	public float x, y, z;
	public float lookX, lookY, lookZ;
	public float speed;

	public GameObject() {
	}

	public GameObject(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3 getPosition() {
		return new Vector3(x, y, z);
	}

	public Vector3 move(float distanceX, float distanceY, float distanceZ) {
//		final float offsetXX = MathUtils.cosDeg(directionAngle);
//		final float offsetXY = MathUtils.sinDeg(directionAngle);
//		getPosition().add(-distanceX * offsetXX, distanceX * offsetXY, distanceZ);
//
//		final float offsetYX = MathUtils.cosDeg(directionAngle - 90f);
//		final float offsetYY = MathUtils.sinDeg(directionAngle - 90f);
//		return getPosition().add(distanceY * offsetYX, -distanceY * offsetYY, distanceZ);
		return null;
	}

	public float rotate(float degree) {
//		directionAngle += degree;
//
//		if(directionAngle > 360f) {
//			directionAngle -= 360f;
//		}
//
//		return directionAngle;
		return 0;
	}
}
