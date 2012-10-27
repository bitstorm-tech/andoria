package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;

public class MeshGenerator {
	
	public static Mesh generateCube() {
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, null),
			new VertexAttribute(VertexAttributes.Usage.Normal, 3, null)
		);

		final int numVerticsCoords = 6 * 6 * 3;
		final int numTextureCoords = 6 * 4;
		final int numNormalCoords = 6 * 6 * 3;

		final Mesh mesh = new Mesh(true, numVerticsCoords + numTextureCoords + numNormalCoords, 0, attributes);
		final VertexBuffer buffer = new VertexBuffer(attributes);

		buffer.addVerticCoordinates(new Vector3(0, 0, 0));
		buffer.addVerticCoordinates(new Vector3(1, 0, 0));
		buffer.addVerticCoordinates(new Vector3(0, 0, 1));
		buffer.addVerticCoordinates(new Vector3(0, 0, 1));
		buffer.addVerticCoordinates(new Vector3(1, 0, 0));
		buffer.addVerticCoordinates(new Vector3(1, 0, 1));

		buffer.addVerticCoordinates(new Vector3(1, 1, 0));
		buffer.addVerticCoordinates(new Vector3(0, 1, 0));
		buffer.addVerticCoordinates(new Vector3(1, 1, 1));
		buffer.addVerticCoordinates(new Vector3(1, 1, 1));
		buffer.addVerticCoordinates(new Vector3(0, 1, 0));
		buffer.addVerticCoordinates(new Vector3(0, 1, 1));

		buffer.addVerticCoordinates(new Vector3(1, 0, 0));
		buffer.addVerticCoordinates(new Vector3(1, 1, 0));
		buffer.addVerticCoordinates(new Vector3(1, 0, 1));
		buffer.addVerticCoordinates(new Vector3(1, 0, 1));
		buffer.addVerticCoordinates(new Vector3(1, 1, 0));
		buffer.addVerticCoordinates(new Vector3(1, 1, 1));

		buffer.addVerticCoordinates(new Vector3(0, 1, 0));
		buffer.addVerticCoordinates(new Vector3(0, 0, 0));
		buffer.addVerticCoordinates(new Vector3(0, 1, 1));
		buffer.addVerticCoordinates(new Vector3(0, 1, 1));
		buffer.addVerticCoordinates(new Vector3(0, 0, 0));
		buffer.addVerticCoordinates(new Vector3(0, 0, 1));

		buffer.addVerticCoordinates(new Vector3(0, 0, 1));
		buffer.addVerticCoordinates(new Vector3(1, 0, 1));
		buffer.addVerticCoordinates(new Vector3(0, 1, 1));
		buffer.addVerticCoordinates(new Vector3(0, 1, 1));
		buffer.addVerticCoordinates(new Vector3(1, 0, 1));
		buffer.addVerticCoordinates(new Vector3(1, 1, 1));

		buffer.addVerticCoordinates(new Vector3(0, 0, 0));
		buffer.addVerticCoordinates(new Vector3(0, 1, 0));
		buffer.addVerticCoordinates(new Vector3(1, 0, 0));
		buffer.addVerticCoordinates(new Vector3(1, 0, 0));
		buffer.addVerticCoordinates(new Vector3(1, 1, 0));
		buffer.addVerticCoordinates(new Vector3(0, 1, 0));

		buffer.calculateNormals();
		buffer.addStandardTextureCoordinates();
		mesh.setVertices(buffer.toFloatArray());

		return mesh;
	}
}
