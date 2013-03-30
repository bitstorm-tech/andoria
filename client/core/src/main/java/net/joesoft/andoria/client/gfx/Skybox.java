package net.joesoft.andoria.client.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.client.model.GameObject;
import net.joesoft.andoria.client.utils.VertexBuffer;

import java.util.LinkedList;

public class Skybox implements Renderable {
	//TODO use texture region
	private final GameObject surrounded;
	private final LinkedList<Texture> textures = new LinkedList<Texture>();
	private final LinkedList<Mesh> meshes = new LinkedList<Mesh>();
	private final int renderSides;

	/**
	 * @param surrounded the object which shall be surrounded by the Skybox
	 * @param distance the distance between the surrounded object and the Skybox sides
	 * @param renderBottom if false the bottom of the skybox will not be rendered
	 */
	public Skybox(GameObject surrounded, float distance, boolean renderBottom) {
		this.surrounded = surrounded;

		if(renderBottom) {
			renderSides = 6;
		} else {
			renderSides = 5;
		}

		final VertexAttributes attributes = new VertexAttributes(
				new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
				new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, null)
		);

		VertexBuffer buffer;
		Mesh mesh;
		final float x = surrounded.x;
		final float y = surrounded.y;
		final Vector3 v1 = new Vector3(x - distance, y + distance, -distance);
		final Vector3 v2 = new Vector3(x + distance, y + distance, -distance);
		final Vector3 v3 = new Vector3(x - distance, y + distance,  distance);
		final Vector3 v4 = new Vector3(x - distance, y + distance,  distance);
		final Vector3 v5 = new Vector3(x + distance, y + distance, -distance);
		final Vector3 v6 = new Vector3(x + distance, y + distance,  distance);

		// Side 1 =============================================================================
		buffer = new VertexBuffer(attributes);
		buffer.addVertexCoordinates(v1);
		buffer.addVertexCoordinates(v2);
		buffer.addVertexCoordinates(v3);
		buffer.addVertexCoordinates(v4);
		buffer.addVertexCoordinates(v5);
		buffer.addVertexCoordinates(v6);
		buffer.addStandardTextureCoordinates();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, buffer.getAttributes());
		mesh.setVertices(buffer.toFloatArray());
		meshes.add(mesh);
		textures.add(new Texture(Gdx.files.classpath("textures/skybox/1.png")));

		// Side 2 =============================================================================
		buffer = new VertexBuffer(attributes);
		buffer.addVertexCoordinates(v1.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v2.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v3.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v4.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v5.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v6.rotate(-90, 0, 0, 1));
		buffer.addStandardTextureCoordinates();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, buffer.getAttributes());
		mesh.setVertices(buffer.toFloatArray());
		meshes.add(mesh);
		textures.add(new Texture(Gdx.files.classpath("textures/skybox/2.png")));

		// Side 3 =============================================================================
		buffer = new VertexBuffer(attributes);
		buffer.addVertexCoordinates(v1.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v2.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v3.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v4.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v5.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v6.rotate(-90, 0, 0, 1));
		buffer.addStandardTextureCoordinates();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, buffer.getAttributes());
		mesh.setVertices(buffer.toFloatArray());
		meshes.add(mesh);
		textures.add(new Texture(Gdx.files.classpath("textures/skybox/3.png")));

		// Side 4 =============================================================================
		buffer = new VertexBuffer(attributes);
		buffer.addVertexCoordinates(v1.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v2.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v3.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v4.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v5.rotate(-90, 0, 0, 1));
		buffer.addVertexCoordinates(v6.rotate(-90, 0, 0, 1));
		buffer.addStandardTextureCoordinates();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, buffer.getAttributes());
		mesh.setVertices(buffer.toFloatArray());
		meshes.add(mesh);
		textures.add(new Texture(Gdx.files.classpath("textures/skybox/4.png")));

		// Side top ============================================================================
		buffer = new VertexBuffer(attributes);
		buffer.addVertexCoordinates(v1.rotate(-90, 0, 0, 1).rotate(90, 1, 0, 0));
		buffer.addVertexCoordinates(v2.rotate(-90, 0, 0, 1).rotate(90, 1, 0, 0));
		buffer.addVertexCoordinates(v3.rotate(-90, 0, 0, 1).rotate(90, 1, 0, 0));
		buffer.addVertexCoordinates(v4.rotate(-90, 0, 0, 1).rotate(90, 1, 0, 0));
		buffer.addVertexCoordinates(v5.rotate(-90, 0, 0, 1).rotate(90, 1, 0, 0));
		buffer.addVertexCoordinates(v6.rotate(-90, 0, 0, 1).rotate(90, 1, 0, 0));
		buffer.addStandardTextureCoordinates();
		mesh = new Mesh(true, buffer.getBufferSize(), 0, buffer.getAttributes());
		mesh.setVertices(buffer.toFloatArray());
		meshes.add(mesh);
		textures.add(new Texture(Gdx.files.classpath("textures/skybox/t.png")));
	}

	@Override
	public boolean isIlluminated() {
		return false;
	}

	@Override
	public void render() {
		for(int i = 0; i < renderSides; ++i) {
			Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
			textures.get(i).bind();
//			Gdx.gl10.glPushMatrix();
//			Gdx.gl10.glTranslatef(x - 0.15f, y - 0.15f, z);
			meshes.get(i).render(GL20.GL_TRIANGLES);
//			Gdx.gl10.glPopMatrix();
			Gdx.gl10.glDisable(GL10.GL_TEXTURE_2D);
		}
	}
}
