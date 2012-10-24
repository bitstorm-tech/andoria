package net.joesoft.andoria.gfx;

import com.badlogic.gdx.graphics.*;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Properties;

public class Terrain {
	private Log log = new Log(this.getClass());
	private Mesh mesh;

	public Terrain() {
		generate();
	}

	public void render() {
		final Camera camera = Properties.getCamera();
		mesh.render(GL20.GL_TRIANGLES);
	}

	public void generate() {
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null)
		);

		mesh = new Mesh(true, 4*3, 0, attributes);

		final float vertices[] = new float[]{
			0, 0, 0,
			1, 0, 0,
			0, 1, 0
		};

		mesh.setVertices(vertices);
	}
}
