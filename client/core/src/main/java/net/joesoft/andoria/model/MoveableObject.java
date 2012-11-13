package net.joesoft.andoria.model;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.Log;

public abstract  class MoveableObject extends GameObject {
	private final Log log = new Log(this.getClass());
	protected float speed;

	public MoveableObject() {
		super();
	}

	public MoveableObject(float x, float y, float z) {
		super(x, y, z);
	}

	public Vector3 move(float distanceX, float distanceY, float distanceZ) {
		final float offsetXX = MathUtils.cosDeg(directionAngle);
		final float offsetXY = MathUtils.sinDeg(directionAngle);
		getPosition().add(-distanceX * offsetXX, distanceX * offsetXY, distanceZ);

		final float offsetYX = MathUtils.cosDeg(directionAngle - 90f);
		final float offsetYY = MathUtils.sinDeg(directionAngle - 90f);
		return getPosition().add(distanceY * offsetYX, -distanceY * offsetYY, distanceZ);
	}

	public float rotate(float degree) {
		directionAngle += degree;

		if(directionAngle > 360f) {
			directionAngle -= 360f;
		}

		return directionAngle;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
