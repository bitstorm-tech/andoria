package net.bitstorm.engine.utils;

import com.badlogic.gdx.math.Vector3;

public class MeshGenerator {
	
	public static VertexBuffer generateCube(float size) {
		final VertexBuffer buffer = new VertexBuffer();

		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(size, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, 0, size));
		buffer.addVertexCoordinates(new Vector3(0, 0, size));
		buffer.addVertexCoordinates(new Vector3(size, 0, 0));
		buffer.addVertexCoordinates(new Vector3(size, 0, size));

		buffer.addVertexCoordinates(new Vector3(size, size, 0));
		buffer.addVertexCoordinates(new Vector3(0, size, 0));
		buffer.addVertexCoordinates(new Vector3(size, size, size));
		buffer.addVertexCoordinates(new Vector3(size, size, size));
		buffer.addVertexCoordinates(new Vector3(0, size, 0));
		buffer.addVertexCoordinates(new Vector3(0, size, size));

		buffer.addVertexCoordinates(new Vector3(size, 0, 0));
		buffer.addVertexCoordinates(new Vector3(size, size, 0));
		buffer.addVertexCoordinates(new Vector3(size, 0, size));
		buffer.addVertexCoordinates(new Vector3(size, 0, size));
		buffer.addVertexCoordinates(new Vector3(size, size, 0));
		buffer.addVertexCoordinates(new Vector3(size, size, size));

		buffer.addVertexCoordinates(new Vector3(0, size, 0));
		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, size, size));
		buffer.addVertexCoordinates(new Vector3(0, size, size));
		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, 0, size));

		buffer.addVertexCoordinates(new Vector3(0, 0, size));
		buffer.addVertexCoordinates(new Vector3(size, 0, size));
		buffer.addVertexCoordinates(new Vector3(0, size, size));
		buffer.addVertexCoordinates(new Vector3(0, size, size));
		buffer.addVertexCoordinates(new Vector3(size, 0, size));
		buffer.addVertexCoordinates(new Vector3(size, size, size));

		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, size, 0));
		buffer.addVertexCoordinates(new Vector3(size, 0, 0));
		buffer.addVertexCoordinates(new Vector3(size, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, size, 0));
		buffer.addVertexCoordinates(new Vector3(size, size, 0));

		return buffer;
	}
}
