package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.VertexBuffer;

import java.util.Random;

public class Terrain {
	private final Log LOG = new Log(this.getClass());
	private Mesh mesh;
	private Texture texture;
	final int sizeX = 30;
	final int sizeY = 30;
	private float [][] heightmap;

	public Terrain() {
		texture = new Texture(Gdx.files.classpath("textures/gras1.png"));
		generateHeightMap();
		generate();
	}

	public void render() {
		Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();
		mesh.render(GL20.GL_TRIANGLES);
	}

	private void generate() {
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, null),
			new VertexAttribute(VertexAttributes.Usage.Normal, 3, null)
		);

		final int numVerticeCoords = sizeX * sizeY * 6 * 3;
		final int numTextureCoords = sizeX * sizeY * 6 * 2;
		final int numNormalsCoords = sizeX * sizeY * 6 * 3;

		LOG.info("Vertice Coords: " + numVerticeCoords);
		LOG.info("Texture Coords: " + numTextureCoords);
		LOG.info("Normals Coords: " + numNormalsCoords);

		mesh = new Mesh(true, numVerticeCoords + numTextureCoords + numNormalsCoords, 0, attributes);
		final VertexBuffer buffer = new VertexBuffer(attributes);

		for(int x = 0; x < sizeX; ++x) {
			for(int y = 0; y < sizeY; ++y) {
				// triangle 1 for quad
				buffer.addVerticCoordinates(new Vector3((0f + x), (0f + y), heightmap[x][y]));
				buffer.addVerticCoordinates(new Vector3((1f + x), (0f + y), heightmap[x + 1][y]));
				buffer.addVerticCoordinates(new Vector3((0f + x), (1f + y), heightmap[x][y + 1]));

				// triangle 2 for quad
				buffer.addVerticCoordinates(new Vector3((0f + x), (1f + y), heightmap[x][y + 1]));
				buffer.addVerticCoordinates(new Vector3((1f + x), (0f + y), heightmap[x + 1][y]));
				buffer.addVerticCoordinates(new Vector3((1f + x), (1f + y), heightmap[x + 1][y + 1]));
			}
		}

		buffer.calculateNormals();
		buffer.addStandardTextureCoordinates();
		mesh.setVertices(buffer.toFloatArray());
	}

	private void generateHeightMap() {
		final Random rnd = new Random(System.currentTimeMillis());
		heightmap = new float[sizeX + 1][sizeY + 1];

		for(int x = 0; x < sizeX + 1; ++x) {
			for(int y = 0; y < sizeY + 1; ++y) {
				heightmap[x][y] = rnd.nextFloat();
			}
		}
	}
}
