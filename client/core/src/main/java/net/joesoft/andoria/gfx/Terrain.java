package net.joesoft.andoria.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.Math3D;
import net.joesoft.andoria.utils.VertexBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Terrain {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private Mesh mesh;
	private Texture texture;
	private final int sizeX = 50;
	private final int sizeY = 50;
	private float [][] heightmap;

	public Terrain() {
		texture = new Texture(Gdx.files.classpath("textures/gras1.png"));
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

		log.info("Vertex Coords:  " + numVertexCoords);
		log.info("Normal Coords:  " + numNormalsCoords);
		log.info("Texture Coords: " + numTextureCoords);

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

	public float getHeight(float x, float y) {
		// when we are outside the terrain, return 0 by default
		if(x > sizeX || y > sizeY || x < 0 || y < 0) {
			return 0;
		}

		float fractionalX = x - (int)x;
		float fractionalY = y - (int)y;

		// bottom left
		int x1 = (int)x;
		int y1 = (int)y;

		// bottom right
		int x2 = x1 + 1;
		int y2 = y1;

		// top left
		int x3 = x1;
		int y3 = y1 + 1;

		Vector3 p1;
		Vector3 p2;
		Vector3 p3;

		// upper triangle
		if(fractionalX + fractionalY > 1f) {
			x1++;
			y1++;
			p1 = new Vector3(x1, y1, heightmap[x1][y1]);
			p2 = new Vector3(x3, y3, heightmap[x3][y3]);
			p3 = new Vector3(x2, y2, heightmap[x2][y2]);
		}
		// lower triangle
		else {
			p1 = new Vector3(x1, y1, heightmap[x1][y1]);
			p2 = new Vector3(x2, y2, heightmap[x2][y2]);
			p3 = new Vector3(x3, y3, heightmap[x3][y3]);
		}

		return Math3D.calcHeight(p1, p2, p3, x, y);
	}
}
