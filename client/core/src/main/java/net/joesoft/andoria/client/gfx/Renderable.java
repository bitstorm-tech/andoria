package net.joesoft.andoria.client.gfx;

/**
 * All classes implementing this interface can be rendered.
 */
public interface Renderable {
	public boolean isIlluminated();
	public void render();
}
