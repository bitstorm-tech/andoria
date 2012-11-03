package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.Log;
import net.joesoft.andoria.utils.VertexBuffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Terrain {
	private final Log LOG = new Log(this.getClass());
	private Mesh mesh;
	private Texture texture;
	private final int sizeX = 50;
	private final int sizeY = 50;
	private float [][] heightmap;

	public Terrain() {
		texture = new Texture(Gdx.files.classpath("textures/ground1.jpg"));
		heightmap = new float[sizeX + 1][sizeY + 1];
//		generateHeightMap();
		loadHeightMap();
		generate();
	}

	public void render() {
		Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();
		mesh.render(GL20.GL_TRIANGLES);
		Gdx.gl10.glDisable(GL10.GL_TEXTURE_2D);
	}

	private void generate() {
		final VertexAttributes attributes = new VertexAttributes(
			new VertexAttribute(VertexAttributes.Usage.Position, 3, null),
			new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, null),
			new VertexAttribute(VertexAttributes.Usage.Normal, 3, null)
		);

		final int numVertexCoords = sizeX * sizeY * 6 * 3;
		final int numTextureCoords = sizeX * sizeY * 6 * 2;
		final int numNormalsCoords = sizeX * sizeY * 6 * 3;

		LOG.info("Vertex Coords:  " + numVertexCoords);
		LOG.info("Normal Coords:  " + numNormalsCoords);
		LOG.info("Texture Coords: " + numTextureCoords);

		mesh = new Mesh(true, numVertexCoords + numTextureCoords + numNormalsCoords, 0, attributes);
		final VertexBuffer buffer = new VertexBuffer(attributes);

		for(int x = 0; x < sizeX; ++x) {
			for(int y = 0; y < sizeY; ++y) {
				// triangle 1 for quad
				buffer.addVertexCoordinates(new Vector3((0f + x), (0f + y), heightmap[x][y]));
				buffer.addVertexCoordinates(new Vector3((1f + x), (0f + y), heightmap[x + 1][y]));
				buffer.addVertexCoordinates(new Vector3((0f + x), (1f + y), heightmap[x][y + 1]));

				// triangle 2 for quad
				buffer.addVertexCoordinates(new Vector3((0f + x), (1f + y), heightmap[x][y + 1]));
				buffer.addVertexCoordinates(new Vector3((1f + x), (0f + y), heightmap[x + 1][y]));
				buffer.addVertexCoordinates(new Vector3((1f + x), (1f + y), heightmap[x + 1][y + 1]));
			}
		}

		buffer.calculateNormals();
		buffer.smoothNormals();
		buffer.addStandardTextureCoordinates();
		mesh.setVertices(buffer.toFloatArray());
	}

	private void loadHeightMap() {
		try {
			final BufferedImage mapImage = ImageIO.read(Gdx.files.classpath("heightmaps/map1.png").read());

			for(int x = 0; x <= sizeX; ++x) {
				for(int y = 0; y <= sizeY; ++y) {
					final Color color = new Color(mapImage.getRGB(x, y));
					heightmap[x][y] = color.getGreen()/20;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void generateHeightMap() {
		final Random rnd = new Random(System.currentTimeMillis());

		for(int x = 0; x <= sizeX; ++x) {
			for(int y = 0; y <= sizeY; ++y) {
				heightmap[x][y] = rnd.nextFloat();
			}
		}
	}
}
