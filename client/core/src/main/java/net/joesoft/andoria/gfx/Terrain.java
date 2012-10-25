package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import net.joesoft.andoria.utils.Log;

import java.util.Random;

public class Terrain {
	private final Log LOG = new Log(this.getClass());
	private Mesh mesh;
	private Texture texture;
	final int sizeX = 50;
	final int sizeY = 50;
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
			new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, null)
		);

		// a terrain with 10 x 10 quads
		// -> each quad has 6 vertices (2 triangles)
		// -> each vertic has 3 coordinates
		// -> 10 x 10 x 4 x 3
		// plus number of texture coordinates
		final int numVertices = sizeX * sizeY * 6 * 3;
		final int numTextureCoords = sizeX * sizeY * 6 * 2;

		LOG.info("Terrain size: " + numVertices + numTextureCoords + " vertices");

		mesh = new Mesh(true, numVertices + numTextureCoords, 0, attributes);
		final float[] vertices = new float[numVertices + numTextureCoords];

		int index = 0;
		for(int x = 0; x < sizeX; ++x) {
			for(int y = 0; y < sizeY; ++y) {
				// triangle 1 for quad
				vertices[index++] = 0f + x; // x1
				vertices[index++] = 0f + y; // y1
				vertices[index++] = heightmap[x][y]; // z1
				vertices[index++] = 0f; // u1
				vertices[index++] = 0f; // v1

				vertices[index++] = 1f + x; // x2
				vertices[index++] = 0f + y; // y2
				vertices[index++] = heightmap[x + 1][y]; // z2
				vertices[index++] = 1f; // u2
				vertices[index++] = 0f; // v2

				vertices[index++] = 0f + x; // x3
				vertices[index++] = 1f + y; // y3
				vertices[index++] = heightmap[x][y + 1]; // z3
				vertices[index++] = 0f; // u3
				vertices[index++] = 1f; // v3

				// triangle 2 for quad
				vertices[index++] = 0f + x; // x4
				vertices[index++] = 1f + y; // y4
				vertices[index++] = heightmap[x][y + 1]; // z4
				vertices[index++] = 0f; // u4
				vertices[index++] = 1f; // v4

				vertices[index++] = 1f + x; // x5
				vertices[index++] = 0f + y; // y5
				vertices[index++] = heightmap[x + 1][y]; // z5
				vertices[index++] = 1f; // u5
				vertices[index++] = 0f; // v5

				vertices[index++] = 1f + x; // x6
				vertices[index++] = 1f + y; // y6
				vertices[index++] = heightmap[x + 1][y + 1]; // z6
				vertices[index++] = 1f; // u6
				vertices[index++] = 1f; // v6
			}
		}

		mesh.setVertices(vertices);
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
