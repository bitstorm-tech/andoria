package net.joesoft.andoria.utils;

import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class VertexBuffer {
	private final float[] buffer;
	private final VertexAttributes attributes;
	private final int informationLength;

	public VertexBuffer(int size, final VertexAttributes pAttributes) {
		buffer = new float[size];
		attributes = pAttributes;
		informationLength = getInformationLength();
	}

	public float[] toFloatArray() {
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

	public void setNormals(Vector3... normals) {
		int offset = getOffset(VertexAttributes.Usage.Normal);

		for(Vector3 normal : normals) {
			add(offset, normal.x, normal.y, normal.z);
			offset += informationLength;
		}
	}

	public void setTextureCoordinates(Vector2... textureCoordinates) {
		int offset = getOffset(VertexAttributes.Usage.TextureCoordinates);

		for(Vector2 coords : textureCoordinates) {
			add(offset, coords.x, coords.y);
			offset += informationLength;
		}
	}


	public void setVerticCoordinates(Vector3... verticCoordinates) {
		int offset = getOffset(VertexAttributes.Usage.Position);

		for(Vector3 coords : verticCoordinates) {
			add(offset, coords.x, coords.y, coords.z);
			offset += informationLength;
		}
	}

	private void add(int offset, float... data) {
		for(float f : data) {
			buffer[offset++] = f;
		}
	}
}
