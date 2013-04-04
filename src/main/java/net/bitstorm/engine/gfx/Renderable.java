package net.bitstorm.engine.gfx;

/**
 * All classes implementing this interface can be rendered.
 */
public interface Renderable {
	public boolean isIlluminated();
	public boolean isVisible();
	public void render();
}
