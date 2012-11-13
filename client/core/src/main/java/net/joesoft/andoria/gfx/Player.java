package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import net.joesoft.andoria.model.MoveableObject;
import net.joesoft.andoria.utils.MeshGenerator;
import net.joesoft.andoria.utils.VertexBuffer;

public class Player extends MoveableObject {
	private final Texture texture;
	private final Mesh mesh;

	public Player() {
		super(1, 1, 0);
		setSpeed(2f);
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, null),
			new VertexAttribute(VertexAttributes.Usage.Normal, 3, null)
		);

		final VertexBuffer buffer = MeshGenerator.generateCube(0.3f);
		buffer.setAttributes(attributes);
		buffer.calculateNormals();
		buffer.smoothNormals();
		buffer.addStandardTextureCoordinates();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, buffer.getAttributes());
		mesh.setVertices(buffer.toFloatArray());
		texture = new Texture(Gdx.files.classpath("textures/wood1.png"));
	}

	public void render() {
		// textur binding
		Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();

		// positioning
		Gdx.gl10.glPushMatrix();
		// -0.15f to have the origin of the box in the middle (half of size)
		Gdx.gl10.glTranslatef(position.x - 0.15f, position.y - 0.15f, position.z);
		Gdx.gl10.glRotatef(-directionAngle, 0, 0, 1);
		mesh.render(GL20.GL_TRIANGLES);
		Gdx.gl10.glPopMatrix();

		// textur unbinding
		Gdx.gl10.glDisable(GL10.GL_TEXTURE_2D);
	}
}
