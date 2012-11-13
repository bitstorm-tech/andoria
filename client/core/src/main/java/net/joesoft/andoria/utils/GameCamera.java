package net.joesoft.andoria.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class GameCamera {
	private float degree = 0;
	private final Camera camera;

	public GameCamera() {
		camera = new PerspectiveCamera(60, Context.resolutionX, Context.resolutionY);
		camera.far = 1000f;
		camera.near = 0.1f;
		camera.translate(0, -5, 5);
		camera.lookAt(0, 0, 0);
		update();
	}

	/**
	 * Rotates the camera around itself.
	 *
	 * @param deltaX the 2D change on the x axis
	 * @param deltaY the 2D change on the y axis
	 */
	public void rotate(float deltaX, float deltaY) {
		rotate(deltaX, deltaY, camera.position);
	}

	/**
	 * Rotates the camera around the given object position.
	 *
	 * @param deltaX the 2D change on the x axis
	 * @param deltaY the 2D change on the y axis
	 * @param objectPosition the object position where the camera shall rotate around
	 */
	public void rotate(float deltaX, float deltaY, Vector3 objectPosition) {
		degree += deltaX;

		final float distance = objectPosition.dst(camera.position);
		camera.position.set(objectPosition);

		// the deltaX is always the rotation around the z axis
		camera.rotate(-deltaX, 0, 0, 1);

		// the deltaY is the rotation around the x and y axis
		// depending of the direction
		final float x = MathUtils.cosDeg(degree);
		final float y = MathUtils.sinDeg(degree);
		camera.rotate(-deltaY, x, -y, 0);

		camera.translate(camera.direction.cpy().mul(-distance));

		update();
	}

	/**
	 * Translates a two dimensional input into the resulting
	 * 3 dimensional change of the position of the camera.
	 *
	 * @param deltaX the 2D change on the x axis
	 * @param deltaY the 2D change on the y axis
	 */
	public void move(float deltaX, float deltaY) {
		move(deltaX, deltaY, 0);
	}

	/**
	 * Translates a two dimensional input into the resulting
	 * 3 dimensional change of the position of the camera.
	 *
	 * @param deltaX the 2D change on the x axis
	 * @param deltaY the 2D change on the y axis
	 * @param deltaZ the 2D change on the z axis
	 */
	public void move(float deltaX, float deltaY, float deltaZ) {
		float speed = Context.scrollSpeed;

		// In attached mode, the camera is as fast as the player moves
		if(Context.cameraMode == CameraMode.ATTACHED) {
			speed = 1;
		}

		// compute deltaY -> split it in a x and a y component
		final float yx = (deltaY * MathUtils.sinDeg(degree)) / speed;
		final float yy = (deltaY * MathUtils.cosDeg(degree)) / speed;
		camera.translate(yx, yy, deltaZ);

		// compute deltaX -> split it in a x and a y component
		final float xx = (deltaX * MathUtils.sinDeg(degree - 90)) / speed;
		final float xy = (deltaX * MathUtils.cosDeg(degree - 90)) / speed;
		camera.translate(xx, xy, deltaZ);
		update();
	}

	/**
	 * Moves (zooms) the camera in the "look at" direction.
	 *
	 * @param direction negative values means zoom in, positive values means zoom out
	 */
	public void zoom(int direction) {
		camera.translate(-direction * camera.direction.x, -direction * camera.direction.y, -direction * camera.direction.z);
		update();
	}

	private void update() {
		camera.update();
		camera.apply(Gdx.gl10);
	}
}
