package net.joesoft.andoria.client.model;

import net.joesoft.andoria.client.utils.VertexBuffer;

public interface Renderable {
	public void render();
	public VertexBuffer getVertexBuffer();
}
