package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector3;

public class CoordinateSystem {
	private Mesh axis;

	public CoordinateSystem() {
		generate();
	}

	public void render() {
		axis.render(GL20.GL_LINES);
	}

	public void translate(final Vector3 pNewPosition) {

	}

	private void generate() {
		axis = new Mesh(true, 6, 3,
			new VertexAttribute(VertexAttributes.Usage.Position, 3, "coordinates"),
			new VertexAttribute(VertexAttributes.Usage.Color, 4, "color")
		);

		axis.setVertices(new float[]{
			0, 0, 0, 100, 0, 0, Color.toFloatBits(255, 0, 0, 255), // x-axis
			0, 0, 0, 0, 100, 0, Color.toFloatBits(0, 255, 0, 255), // y-axis
			0, 0, 0, 0, 0, 100, Color.toFloatBits(0, 0, 255, 255)  // z-axis
		});
	}
}
