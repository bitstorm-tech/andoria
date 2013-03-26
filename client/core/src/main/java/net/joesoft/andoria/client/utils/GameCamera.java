package net.joesoft.andoria.client.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class GameCamera {
	private float degree = 0;
	private float zoom = 5f;
	private final Camera camera;
	private final Vector3 lookAt = new Vector3();

	public GameCamera() {
		final int resX = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_X);
		final int resY = Settings.getInteger(Settings.Key.ENGINE_RESOLUTION_Y);
		camera = new PerspectiveCamera(60, resX, resY);
		camera.far = 1000f;
		camera.near = 0.1f;
		camera.translate(0, -5, 5);
		camera.lookAt(0, 0, 0);
		update();
	}

	/**
	 * Rotates the camera.
	 *
	 * @param deltaX the 2D change on the x axis
	 * @param deltaY the 2D change on the y axis
	 */
	public void rotate(float deltaX, float deltaY) {
		degree += deltaX;

		// the deltaX is always the rotation around the z axis
		camera.rotate(-deltaX, 0, 0, 1);

		// the deltaY is the rotation around the x and y axis depending of the direction
		final float x = MathUtils.cosDeg(degree);
		final float y = MathUtils.sinDeg(degree);
		camera.rotate(-deltaY, x, -y, 0);

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
		final String cameraMode = Settings.getString(Settings.Key.ENGINE_CAMERAMODE);
		float speed = Settings.getFloat(Settings.Key.INPUT_MOUSE_SCROLLSPEED);

		// In attached mode, the camera is as fast as the player moves
		if(cameraMode.equalsIgnoreCase(CameraMode.ATTACHED.name())) {
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
	 * @param ammount negative values means zoom in, positive values means zoom out
	 */
	public void zoom(float ammount) {
		zoom += ammount;
		camera.translate(-ammount * camera.direction.x, -ammount * camera.direction.y, -ammount * camera.direction.z);
		update();
	}

	public void lookAt(Vector3 lookAt) {
		this.lookAt.set(lookAt);
	}

	private void update() {
		correctDistance();
		camera.update();
		camera.apply(Gdx.gl10);
	}

	private void correctDistance() {
		camera.position.set(lookAt);
		camera.translate(-zoom * camera.direction.x, -zoom * camera.direction.y, -zoom * camera.direction.z);
	}
}
