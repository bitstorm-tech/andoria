package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class RenderUtils {
	/**
	 * Renders the normals of each vertex from the given VertexBuffer. Be careful, this method
	 * is not optimized and can result in poor performance.
	 *
	 * @param buffer the VertexBuffer from which the normals shall be rendered
	 */
	public static void renderNormals(VertexBuffer buffer) {
		final List<Vector3> normals = buffer.getNormals();
		final List<Vector3> vertexCoords = buffer.getVertexCoordinates();
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, null)
		);
		final VertexBuffer vBuffer = new VertexBuffer(attributes);

		for(int i = 0; i < normals.size(); ++i) {
			final Vector3 vertex = vertexCoords.get(i);
			Vector3 normal = normals.get(i);
			normal = vertex.tmp().add(normal);

			vBuffer.addVertexCoordinates(new Vector3(vertex.x, vertex.y, vertex.z));
			vBuffer.addVertexCoordinates(new Vector3(normal.x, normal.y, normal.z));
		}

		vBuffer.addColors(Color.WHITE.toFloatBits());

		final Mesh normalsMesh = new Mesh(true, vBuffer.getBufferSize(), 0, attributes);
		normalsMesh.setVertices(vBuffer.toFloatArray());
		normalsMesh.render(GL10.GL_LINES);
	}
}
