package net.joesoft.andoria.gfx;

import com.badlogic.gdx.math.Vector3;
import net.joesoft.andoria.utils.Log;

public class Terrain {
	private Log log = new Log(this.getClass());
	private final Vector3[][] coordinates;
	private final float sliceSize = 1f;
	private final int terrainSize = 100;

	public Terrain() {
		coordinates = new Vector3[terrainSize][terrainSize];
	}

	public float getSliceSize() {
		return sliceSize;
	}

	public int getTerrainSize() {
		return terrainSize;
	}

	public Vector3 getPosition(int x, int y) {
		if(x > terrainSize || y > terrainSize) {
			log.warn("Either x or y coordinate is outside of terrain");
			return null;
		}

		return coordinates[x][y];
	}

	public void generate() {
		for (int x = 0; x < 100; ++x) {
			for (int y = 0; y < 100; ++y) {
				coordinates[x][x] = new Vector3();
				coordinates[x][x].add(x, y, 0);
			}
		}
	}
}
