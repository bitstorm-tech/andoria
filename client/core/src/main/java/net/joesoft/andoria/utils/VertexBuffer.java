package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VertexBuffer {
	private final float[] buffer;
	private final VertexAttributes attributes;
	private final int informationLength;
	private List<Vector3> verticCoords = new ArrayList<Vector3>();
	private List<Vector2> textureCoords = new ArrayList<Vector2>();
	private List<Vector3> normals = new ArrayList<Vector3>();

	public VertexBuffer(int size, final VertexAttributes attributes) {
		buffer = new float[size];
		this.attributes = attributes;
		informationLength = getInformationLength();
	}

	public float[] toFloatArray() {
		int offset = getOffset(VertexAttributes.Usage.Position);

		for(Vector3 coords : verticCoords) {
			add(offset, coords.x, coords.y, coords.z);
			offset += informationLength;
		}

		offset = getOffset(VertexAttributes.Usage.TextureCoordinates);
		for(Vector2 coords : textureCoords) {
			add(offset, coords.x, coords.y);
			offset += informationLength;
		}

		offset = getOffset(VertexAttributes.Usage.Normal);
		for(Vector3 normal : normals) {
			add(offset, normal.x, normal.y, normal.z);
			offset += informationLength;
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

			offset += attribute.numComponents;
		}

		if(!typeAvailable) {
			throw new IllegalArgumentException("Type not available");
		}

		return offset;
	}

	private int getInformationLength() {
		int length = 0;
		for(int i = 0; i < attributes.size(); ++i) {
			length += attributes.get(i).numComponents;
		}

		return length;
	}

	public void addNormals(Vector3... normals) {
		this.normals.addAll(Arrays.asList(normals));
	}

	public void addTextureCoordinates(Vector2... textureCoordinates) {
		this.textureCoords.addAll(Arrays.asList(textureCoordinates));
	}


	public void addVerticCoordinates(Vector3... verticCoordinates) {
		this.verticCoords.addAll(Arrays.asList(verticCoordinates));
	}

	public void calculateNormals() {
		int index = 0;

		while(index < verticCoords.size()) {
			final Vector3 p1 = verticCoords.get(index++);
			final Vector3 p2 = verticCoords.get(index++);
			final Vector3 p3 = verticCoords.get(index++);

			final Vector3 normal = Math3D.calcNormal(p1, p2, p3);

			normals.add(normal);
			normals.add(normal);
			normals.add(normal);
		}
	}

	private void add(int offset, float... data) {
		for(float f : data) {
			buffer[offset++] = f;
		}
	}
}
