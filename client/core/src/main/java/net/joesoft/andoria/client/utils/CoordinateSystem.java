package net.joesoft.andoria.client.utils;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.client.gfx.Renderable;

public class CoordinateSystem implements Renderable {
	private Mesh axis;
	private boolean cameraAutoFollow = false;
	private Vector3 originPosition = new Vector3(0, 0, 0);
	private float colorX = Color.RED.toFloatBits();
	private float colorY = Color.GREEN.toFloatBits();
	private float colorZ = Color.BLUE.toFloatBits();

	public CoordinateSystem() {
		generate();
	}

	@Override
	public boolean isIlluminated() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return Settings.getBoolean(Settings.Key.ENGINE_SHOWCOORDINATESYSTEM);
	}

	/**
	 * Render the coordinate system.
	 */
	@Override
	public void render() {
		axis.render(GL20.GL_LINES, 0, 6);
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
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, null)
		);

		axis.setVertices(new float[]{
			0, 0, 0, colorX, 2, 0, 0, colorX, // x-axis
			0, 0, 0, colorY, 0, 2, 0, colorY, // y-axis
			0, 0, 0, colorZ, 0, 0, 2, colorZ  // z-axis
		});
	}
}
