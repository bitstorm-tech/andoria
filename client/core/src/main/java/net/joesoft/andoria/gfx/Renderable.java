package net.joesoft.andoria.gfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.VertexBuffer;

import java.util.List;

public abstract class Renderable {
	protected VertexBuffer buffer;
	protected Mesh mesh;

	/**
	 * Renders the normals of each vertex from the mesh. Be careful, this method
	 * is not optimized and can result in poor performance.
	 */
	public void renderNormals() {
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
			vBuffer.addColors(Color.WHITE.toFloatBits());
			vBuffer.addVertexCoordinates(new Vector3(normal.x, normal.y, normal.z));
			vBuffer.addColors(Color.WHITE.toFloatBits());
		}

		final Mesh normalsMesh = new Mesh(true, vBuffer.getBufferSize(), 0, attributes);
		normalsMesh.setVertices(vBuffer.toFloatArray());
		normalsMesh.render(GL10.GL_LINES);
	}

	abstract  public void render();
}
