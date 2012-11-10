package net.joesoft.andoria.utils;

import com.badlogic.gdx.math.Vector3;

public class Math3D {

	public static Vector3 calcNormal(final Vector3 p1, final Vector3 p2, final Vector3 p3) {
		final Vector3 u = p2.cpy().sub(p1);
		final Vector3 v = p3.cpy().sub(p1);

		return u.crs(v);
	}

	public static Vector3 sum(Vector3... vectors) {
		final int numVectors = vectors.length;
		final Vector3 result = new Vector3(vectors[0]);

		for(int i = 1; i < numVectors; ++i) {
			result.add(vectors[i]);
		}

		return result;
	}

	public static float calcHeight(final Vector3 p1, final Vector3 p2, final Vector3 p3, float x, float y) {
		final Vector3 n = calcNormal(p1, p2, p3);

		if(n.z == 0) {
			return 0;
		}

		return  (((n.x * (x - p1.x)) + (n.y * (y - p1.y))) / -n.z) + p1.z;
	}
}
