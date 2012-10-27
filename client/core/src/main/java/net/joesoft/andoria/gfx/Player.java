package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import net.joesoft.andoria.model.MoveableObject;
import net.joesoft.andoria.utils.MeshGenerator;

public class Player extends MoveableObject {
	private final Mesh mesh;
	private final Texture texture;

	public Player() {
		mesh = MeshGenerator.generateCube();
		texture = new Texture(Gdx.files.classpath("textures/wood1.png"));
	}

	public void movePlyer(float x, float y, float z) {
		super.move(x, y, z);
	}

	public void render() {

		Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();
		mesh.render(GL20.GL_TRIANGLES);
	}
}
