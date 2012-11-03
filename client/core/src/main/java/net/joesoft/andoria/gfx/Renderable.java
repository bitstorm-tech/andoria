package net.joesoft.andoria.gfx;

import net.joesoft.andoria.utils.VertexBuffer;

public interface Renderable {
	public void render();
	public VertexBuffer getVertexBuffer();
}
