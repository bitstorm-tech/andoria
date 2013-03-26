package net.joesoft.andoria.client.utils;

import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VertexBuffer {
	private VertexAttributes attributes;
	private List<Float> colors = new ArrayList<Float>();
	private List<Vector3> vertexCoords = new ArrayList<Vector3>();
	private List<Vector2> textureCoords = new ArrayList<Vector2>();
	private List<Vector3> normals = new ArrayList<Vector3>();
	public static Vector2[] standardTextureCoordinates = new Vector2[] {
		new Vector2(0, 0),
		new Vector2(1, 0),
		new Vector2(0, 1),
		new Vector2(0, 1),
		new Vector2(1, 0),
		new Vector2(1, 1)
	};

	public VertexBuffer() {
	}

	public VertexBuffer(final VertexAttributes attributes) {
		this.attributes = attributes;
	}

	public float[] toFloatArray() {
		final float[] buffer = new float[getBufferSize()];
		final int informationLength = getVertexInformationSize();

		int offset = getOffset(VertexAttributes.Usage.Position);
		for(Vector3 coords : vertexCoords) {
			add(buffer, offset, coords.x, coords.y, coords.z);
			offset += informationLength;
		}

		if(textureCoords.size() != 0) {
			offset = getOffset(VertexAttributes.Usage.TextureCoordinates);
			for(Vector2 coords : textureCoords) {
				add(buffer, offset, coords.x, coords.y);
				offset += informationLength;
			}
		}

		if(normals.size() != 0) {
			offset = getOffset(VertexAttributes.Usage.Normal);
			for(Vector3 normal : normals) {
				add(buffer, offset, normal.x, normal.y, normal.z);
				offset += informationLength;
			}
		}

		if(colors.size() != 0) {
			offset = getOffset(VertexAttributes.Usage.ColorPacked);
			if(colors.size() > 1) {
				for(float color : colors) {
					add(buffer, offset, color);
					offset += informationLength;
				}
			} else {
				while(offset < getBufferSize()) {
					add(buffer, offset, colors.get(0));
					offset += informationLength;
				}
			}
		}

		return buffer;
	}

	private int getOffset(int type) {
		int offset = 0;
		boolean typeAvailable = false;
		for(int i = 0; i < attributes.size(); ++i) {
			final VertexAttribute attribute = attributes.get(i);

			if(type == attribute.usage) {
				typeAvailable = true;
				break;
			}

			offset += getNumberOfComponents(attributes.get(i));
		}

		if(!typeAvailable) {
			throw new IllegalArgumentException("Type not available");
		}

		return offset;
	}

	private int getVertexInformationSize() {
		int length = 0;
		for(int i = 0; i < attributes.size(); ++i) {
			length += getNumberOfComponents(attributes.get(i));
		}

		return length;
	}

	private int getNumberOfComponents(VertexAttribute attribute) {
		int number = 0;
		// ColorPacked must be treated different because it has
		// 4 components but needs only one float
		if(attribute.usage == VertexAttributes.Usage.ColorPacked) {
			number = 1;
		} else {
			number += attribute.numComponents;
		}

		return number;
	}

	public void addTextureCoordinates(Vector2... textureCoordinates) {
		this.textureCoords.addAll(Arrays.asList(textureCoordinates));
	}


	public void addVertexCoordinates(Vector3... verticCoordinates) {
		this.vertexCoords.addAll(Arrays.asList(verticCoordinates));
	}

	public void addColors(Float... colors) {
		this.colors.addAll(Arrays.asList(colors));
	}

	public List<Vector3> getNormals() {
		return normals;
	}

	public List<Vector3> getVertexCoordinates() {
		return vertexCoords;
	}

	public VertexAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(VertexAttributes attributes) {
		this.attributes = attributes;
	}

	public int getBufferSize() {
		return getVertexInformationSize() * getNumberOfVertics();
	}

	public int getNumberOfVertics() {
		return vertexCoords.size();
	}

	public void calculateNormals() {
		int index = 0;
		normals.clear();

		while(index < vertexCoords.size()) {
			final Vector3 p1 = vertexCoords.get(index++);
			final Vector3 p2 = vertexCoords.get(index++);
			final Vector3 p3 = vertexCoords.get(index++);

			final Vector3 normal = Math3D.calcNormal(p1, p2, p3);

			normals.add(normal);
			normals.add(normal);
			normals.add(normal);
		}
	}

	public void smoothNormals() {
		final List<List<Integer>> sameCoords = getSameCoordinates();

		for(List<Integer> same : sameCoords) {
			final int numSame = same.size();
			final Vector3[] _normals = new Vector3[numSame];
			final int[] indices = new int[numSame];

			for(int i = 0; i < numSame; ++i) {
				final int index = same.get(i);
				indices[i] = index;
				_normals[i] = normals.get(index);
			}

			final Vector3 result = Math3D.sum(_normals).nor();

			for(int i = 0; i < numSame; ++i) {
				normals.set(indices[i], result);
			}
		}
	}

	private List<List<Integer>> getSameCoordinates() {
		final List<List<Integer>> same = new ArrayList<List<Integer>>();
		int index = 0;

		for(Vector3 coordinate : vertexCoords) {
			same.add(index, new ArrayList<Integer>());

			for(int i = 0; i < vertexCoords.size(); ++i) {
				final Vector3 other = vertexCoords.get(i);

				if(coordinate.equals(other)) {
					same.get(index).add(i);
				}
			}

			// remove single coordinates
			if(same.get(index).size() <= 1) {
				same.remove(index);
			} else {
				index++;
			}
		}

		return same;
	}

	public void addStandardTextureCoordinates() {
		for(int i = 0; i < vertexCoords.size() / 6; ++i) {
			addTextureCoordinates(standardTextureCoordinates);
		}
	}

	private void add(final float[] buffer, int offset, float... data) {
		for(float f : data) {
			buffer[offset++] = f;
		}
	}
}
