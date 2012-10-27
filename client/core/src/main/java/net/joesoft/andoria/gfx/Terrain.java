package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.VertexBuffer;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.Math3D;

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

		final int numVerticeCoords = sizeX * sizeY * 6 * 3;
		final int numTextureCoords = sizeX * sizeY * 6 * 2;
		final int numNormalsCoords = sizeX * sizeY * 6 * 3;

		LOG.info("Vertice Coords: " + numVerticeCoords);
		LOG.info("Texture Coords: " + numTextureCoords);
		LOG.info("Normals Coords: " + numNormalsCoords);

		mesh = new Mesh(true, numVerticeCoords + numTextureCoords + numNormalsCoords, 0, attributes);

		final Vector3[] verticCoordinates = new Vector3[sizeX * sizeY * 6];
		final Vector3[] normals = new Vector3[sizeX * sizeY * 6];
		final Vector2[] textureCoordinates = new Vector2[sizeX * sizeY * 6];

		int index = 0;
		for(int x = 0; x < sizeX; ++x) {
			for(int y = 0; y < sizeY; ++y) {
				// triangle 1 for quad
				final Vector3 p1 = new Vector3((0f + x), (0f + y), heightmap[x][y]); // vertic coordinate
				final Vector2 uv1 = new Vector2(0, 0); // texture coordinate

				final Vector3 p2 = new Vector3((1f + x), (0f + y), heightmap[x + 1][y]); // vertic coordinate
				final Vector2 uv2 = new Vector2(1, 0); // texture coordinate

				final Vector3 p3 = new Vector3((0f + x), (1f + y), heightmap[x][y + 1]); // vertic coordinate
				final Vector2 uv3 = new Vector2(0, 1); // texture coordinate

				// triangle 2 for quad
				final Vector3 p4 = new Vector3((0f + x), (1f + y), heightmap[x][y + 1]); // vertic coordinate
				final Vector2 uv4 = new Vector2(0, 1); // texture coordinate

				final Vector3 p5 = new Vector3((1f + x), (0f + y), heightmap[x + 1][y]); // vertic coordinate
				final Vector2 uv5 = new Vector2(1, 0); // texture coordinate

				final Vector3 p6 = new Vector3((1f + x), (1f + y), heightmap[x + 1][y + 1]); // vertic coordinate
				final Vector2 uv6 = new Vector2(1, 1); // texture coordinate


				final Vector3 n1 = Math3D.calcNormal(p1, p2, p3);
				final Vector3 n2 = Math3D.calcNormal(p4, p5, p6);

				verticCoordinates[index] = p1;
				textureCoordinates[index] = uv1;
				normals[index++] = n1;

				verticCoordinates[index] = p2;
				textureCoordinates[index] = uv2;
				normals[index++] = n1;

				verticCoordinates[index] = p3;
				textureCoordinates[index] = uv3;
				normals[index++] = n1;

				verticCoordinates[index] = p4;
				textureCoordinates[index] = uv4;
				normals[index++] = n2;

				verticCoordinates[index] = p5;
				textureCoordinates[index] = uv5;
				normals[index++] = n2;

				verticCoordinates[index] = p6;
				textureCoordinates[index] = uv6;
				normals[index++] = n2;
			}
		}

		final VertexBuffer buffer = new VertexBuffer(mesh.getMaxVertices(), attributes);
		buffer.setNormals(normals);
		buffer.setTextureCoordinates(textureCoordinates);
		buffer.setVerticCoordinates(verticCoordinates);

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
