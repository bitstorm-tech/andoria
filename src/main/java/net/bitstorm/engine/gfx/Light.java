package net.bitstorm.engine.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import net.bitstorm.engine.model.GameObject;
import net.bitstorm.engine.utils.MeshGenerator;
import net.bitstorm.engine.utils.Settings;
import net.bitstorm.engine.utils.VertexBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Light extends GameObject implements Renderable {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final Mesh mesh;

	public Light() {
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
		speed = 1;
	}

	public Light(float x, float y, float z) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean isIlluminated() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return Settings.getBoolean(Settings.Key.ENGINE_LIGHT);
	}

	@Override
	public void render() {
		Gdx.gl10.glPushMatrix();
		Gdx.gl10.glTranslatef(x, y, z);
		mesh.render(GL10.GL_TRIANGLES);
		Gdx.gl10.glPopMatrix();
	}
}
