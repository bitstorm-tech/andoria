package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;

public class MeshGenerator {
	
	public static VertexBuffer generateCube() {
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, null),
			new VertexAttribute(VertexAttributes.Usage.Normal, 3, null)
		);

		final VertexBuffer buffer = new VertexBuffer(attributes);

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
