package net.joesoft.andoria.utils;

import com.badlogic.gdx.math.Vector3;

public class MeshGenerator {
	
	public static VertexBuffer generateCube() {
		final VertexBuffer buffer = new VertexBuffer();

		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(1, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, 0, 1));
		buffer.addVertexCoordinates(new Vector3(0, 0, 1));
		buffer.addVertexCoordinates(new Vector3(1, 0, 0));
		buffer.addVertexCoordinates(new Vector3(1, 0, 1));

		buffer.addVertexCoordinates(new Vector3(1, 1, 0));
		buffer.addVertexCoordinates(new Vector3(0, 1, 0));
		buffer.addVertexCoordinates(new Vector3(1, 1, 1));
		buffer.addVertexCoordinates(new Vector3(1, 1, 1));
		buffer.addVertexCoordinates(new Vector3(0, 1, 0));
		buffer.addVertexCoordinates(new Vector3(0, 1, 1));

		buffer.addVertexCoordinates(new Vector3(1, 0, 0));
		buffer.addVertexCoordinates(new Vector3(1, 1, 0));
		buffer.addVertexCoordinates(new Vector3(1, 0, 1));
		buffer.addVertexCoordinates(new Vector3(1, 0, 1));
		buffer.addVertexCoordinates(new Vector3(1, 1, 0));
		buffer.addVertexCoordinates(new Vector3(1, 1, 1));

		buffer.addVertexCoordinates(new Vector3(0, 1, 0));
		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, 1, 1));
		buffer.addVertexCoordinates(new Vector3(0, 1, 1));
		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, 0, 1));

		buffer.addVertexCoordinates(new Vector3(0, 0, 1));
		buffer.addVertexCoordinates(new Vector3(1, 0, 1));
		buffer.addVertexCoordinates(new Vector3(0, 1, 1));
		buffer.addVertexCoordinates(new Vector3(0, 1, 1));
		buffer.addVertexCoordinates(new Vector3(1, 0, 1));
		buffer.addVertexCoordinates(new Vector3(1, 1, 1));

		buffer.addVertexCoordinates(new Vector3(0, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, 1, 0));
		buffer.addVertexCoordinates(new Vector3(1, 0, 0));
		buffer.addVertexCoordinates(new Vector3(1, 0, 0));
		buffer.addVertexCoordinates(new Vector3(0, 1, 0));
		buffer.addVertexCoordinates(new Vector3(1, 1, 0));

		return buffer;
	}
}
