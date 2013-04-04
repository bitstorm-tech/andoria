package net.bitstorm.engine.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import net.bitstorm.engine.model.GameObject;
import net.bitstorm.engine.utils.MeshGenerator;
import net.bitstorm.engine.utils.VertexBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player extends GameObject implements Renderable {
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	private final Texture texture;
	private final Mesh mesh;

	public Player() {
		speed = 2f;
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

	@Override
	public boolean isIlluminated() {
		return true;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void render() {
		// textur binding
		Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();

		// positioning
		Gdx.gl10.glPushMatrix();
		// -0.15f to have the origin of the box in the middle (half of size)
		Gdx.gl10.glTranslatef(x - 0.15f, y - 0.15f, z);
//		Gdx.gl10.glRotatef(-direction, 0, 0, 1);
		mesh.render(GL20.GL_TRIANGLES);
		Gdx.gl10.glPopMatrix();

		// textur unbinding
		Gdx.gl10.glDisable(GL10.GL_TEXTURE_2D);
	}
}
