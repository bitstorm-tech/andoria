package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import net.joesoft.andoria.model.MoveableObject;
import net.joesoft.andoria.utils.MeshGenerator;
import net.joesoft.andoria.utils.VertexBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ligth extends MoveableObject {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Mesh mesh;

	public Ligth() {
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, null),
			new VertexAttribute(VertexAttributes.Usage.Normal, 3, null)
		);

		final VertexBuffer buffer = MeshGenerator.generateCube(0.1f);
		buffer.setAttributes(attributes);
		buffer.addColors(Color.WHITE.toFloatBits());
		buffer.calculateNormals();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, attributes);
		mesh.setVertices(buffer.toFloatArray());
		move(0, 0, 3);
		setSpeed(1);
	}

	public void on() {
		Gdx.gl10.glShadeModel(GL10.GL_SMOOTH);
		Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, new float[]{1, 1, 1, 1}, 0);
		Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, new float[]{1, 1, 1, 1}, 0);
		Gdx.gl10.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[]{position.x, position.y, position.z, 1}, 0);
		Gdx.gl.glEnable(GL10.GL_LIGHTING);
		Gdx.gl.glEnable(GL10.GL_LIGHT0);
	}

	public void off() {
		Gdx.gl.glDisable(GL10.GL_LIGHTING);
		Gdx.gl.glDisable(GL10.GL_LIGHT0);
	}

	public void render() {
		Gdx.gl10.glPushMatrix();
		Gdx.gl10.glTranslatef(position.x, position.y, position.z);
		mesh.render(GL10.GL_TRIANGLES);
		Gdx.gl10.glPopMatrix();
	}
}
