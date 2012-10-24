package net.joesoft.andoria.gfx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import net.joesoft.andoria.utils.Log;

public class Terrain {
	private Log log = new Log(this.getClass());
	private Mesh mesh;

	public Terrain() {
		generate();
	}

	public void render() {
		mesh.render(GL20.GL_TRIANGLES);
	}

	public void generate() {
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null)
		);

		mesh = new Mesh(true, 4*3, 0, attributes);

		final float vertices[] = new float[]{
			0, 0, 0,
			10, 0, 0,
			0, 10, 0
		};

		mesh.setVertices(vertices);
	}
}
