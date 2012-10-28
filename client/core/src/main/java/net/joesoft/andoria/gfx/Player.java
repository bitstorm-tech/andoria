package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import net.joesoft.andoria.utils.MeshGenerator;

public class Player extends Renderable {
	private final Texture texture;

	public Player() {
		buffer = MeshGenerator.generateCube();
		buffer.calculateNormals();
		buffer.smoothNormals();
		buffer.addStandardTextureCoordinates();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, buffer.getAttributes());
		mesh.setVertices(buffer.toFloatArray());
		texture = new Texture(Gdx.files.classpath("textures/wood1.png"));
	}

	@Override
	public void render() {
		Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();
		mesh.render(GL20.GL_TRIANGLES);
		Gdx.gl10.glDisable(GL10.GL_TEXTURE_2D);
	}
}
