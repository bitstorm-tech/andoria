package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector3;

public class CoordinateSystem {
	private Mesh axis;
	private boolean cameraAutoFollow = false;
	private Vector3 originPosition = new Vector3(0, 0, 0);

	public CoordinateSystem() {
		generate();
	}

	/**
	 * Render the coordinate system.
	 */
	public void render() {
		axis.render(GL20.GL_LINES);
	}

	/**
	 * Moves the origin of the coordinate system to the given position.
	 *
	 * @param pNewOriginPosition new coordinate system origin
	 */
	public void move(final Vector3 pNewOriginPosition) {
		originPosition = pNewOriginPosition;
	}

	/**
	 * Enable or disable the camera auto follow feature. If enabled the
	 * coordinate system will always be drawen at the center of the camera.
	 *
	 * @param pCameraAutoFollow true to enable camera auto follow
	 */
	public void setCameraAutoFollow(boolean pCameraAutoFollow) {
		cameraAutoFollow = pCameraAutoFollow;
	}

	/**
	 * Returns if the camera auto follow feature is enabled or disabled.
	 *
	 * @return true if camera auto follow is enabled
	 */
	public boolean getCameraAutoFollow() {
		return cameraAutoFollow;
	}

	private void generate() {
		axis = new Mesh(true, 18, 0,
			new VertexAttribute(VertexAttributes.Usage.Position, 3, "coordinates")
			//new VertexAttribute(VertexAttributes.Usage.Color, 4, "color")
		);

		axis.setVertices(new float[]{
			0, 0, 0, 2, 0, 0, //Color.toFloatBits(255, 0, 0, 255), // x-axis
			0, 0, 0, 0, 2, 0, //Color.toFloatBits(0, 255, 0, 255), // y-axis
			0, 0, 0, 0, 0, 2//, Color.toFloatBits(0, 0, 255, 255)  // z-axis
		});
	}
}
