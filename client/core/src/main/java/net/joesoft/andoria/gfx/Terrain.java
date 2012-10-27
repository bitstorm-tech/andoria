package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.Log;

import java.util.Random;

public class Terrain {
	private final Log LOG = new Log(this.getClass());
	private Mesh mesh;
	private Texture texture;
	final int sizeX = 100;
	final int sizeY = 100;
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

		// a terrain with 10 x 10 quads
		// -> each quad has 6 vertices (2 triangles)
		// -> each vertic has 3 coordinates
		// -> 10 x 10 x 4 x 3
		// plus number of texture coordinates
		// plus a normal for each vertic
		final int numVerticeCoords = sizeX * sizeY * 6 * 3;
		final int numTextureCoords = sizeX * sizeY * 6 * 2;
		final int numNormalsCoords = sizeX * sizeY * 6 * 3;

		LOG.info("Vertice Coords: " + numVerticeCoords);
		LOG.info("Texture Coords: " + numTextureCoords);
		LOG.info("Normals Coords: " + numNormalsCoords);

		mesh = new Mesh(true, numVerticeCoords + numTextureCoords + numNormalsCoords, 0, attributes);
		final float[] vertices = new float[mesh.getMaxVertices()];

		int index = 0;
		for(int x = 0; x < sizeX; ++x) {
			for(int y = 0; y < sizeY; ++y) {
				// triangle 1 for quad
				final Vector3 p1 = new Vector3((0f + x), (0f + y), heightmap[x][y]);
				final Vector3 p2 = new Vector3((1f + x), (0f + y), heightmap[x + 1][y]);
				final Vector3 p3 = new Vector3((0f + x), (1f + y), heightmap[x][y + 1]);
				final Vector3 n1 = calcNormal(p1, p2, p3);


				// triangle 2 for quad
				final Vector3 p4 = new Vector3((0f + x), (1f + y), heightmap[x][y + 1]);
				final Vector3 p5 = new Vector3((1f + x), (0f + y), heightmap[x + 1][y]);
				final Vector3 p6 = new Vector3((1f + x), (1f + y), heightmap[x + 1][y + 1]);
				final Vector3 n2 = calcNormal(p4, p5, p6);

				// fill buffer
				vertices[index++] = p1.x; // x1
				vertices[index++] = p1.y; // y1
				vertices[index++] = p1.z; // z1
				vertices[index++] = 0f;   // u1
				vertices[index++] = 0f;   // v1
				vertices[index++] = n1.x; // nx1
				vertices[index++] = n1.y; // ny1
				vertices[index++] = n1.z; // nz1

				vertices[index++] = p2.x; // x2
				vertices[index++] = p2.y; // y2
				vertices[index++] = p2.z; // z2
				vertices[index++] = 1f;   // u2
				vertices[index++] = 0f;   // v2
				vertices[index++] = n1.x; // nx1
				vertices[index++] = n1.y; // ny1
				vertices[index++] = n1.z; // nz1

				vertices[index++] = p3.x; // x3
				vertices[index++] = p3.y; // y3
				vertices[index++] = p3.z; // z3
				vertices[index++] = 0f;   // u3
				vertices[index++] = 1f;   // v3
				vertices[index++] = n1.x; // nx1
				vertices[index++] = n1.y; // ny1
				vertices[index++] = n1.z; // nz1

				vertices[index++] = p4.x; // x4
				vertices[index++] = p4.y; // y4
				vertices[index++] = p4.z; // z4
				vertices[index++] = 0f;   // u4
				vertices[index++] = 1f;   // v4
				vertices[index++] = n2.x; // nx2
				vertices[index++] = n2.y; // ny2
				vertices[index++] = n2.z; // nz2

				vertices[index++] = p5.x; // x5
				vertices[index++] = p5.y; // y5
				vertices[index++] = p5.z; // z5
				vertices[index++] = 1f;   // u5
				vertices[index++] = 0f;   // v5
				vertices[index++] = n2.x; // nx2
				vertices[index++] = n2.y; // ny2
				vertices[index++] = n2.z; // nz2

				vertices[index++] = p6.x; // x6
				vertices[index++] = p6.y; // y6
				vertices[index++] = p6.z; // z6
				vertices[index++] = 1f;   // u6
				vertices[index++] = 1f;   // v6
				vertices[index++] = n2.x; // nx2
				vertices[index++] = n2.y; // ny2
				vertices[index++] = n2.z; // nz2
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

	private Vector3 calcNormal(final Vector3 p1, final Vector3 p2, final Vector3 p3) {
		final Vector3 u = p2.cpy().sub(p1);
		final Vector3 v = p3.cpy().sub(p1);
		float nx = u.x * v.z - u.z * v.y;
		float ny = u.z * v.x - u.x * v.z;
		float nz = u.x * v.y - u.y * v.x;

		return new Vector3(nx, ny, nz).nor();
	}
}
