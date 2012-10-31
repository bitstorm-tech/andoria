package net.joesoft.andoria;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class WindowsClient {
	public static void main(String[] args) {
		new LwjglApplication(new AndoriaGame(), "Andoria", 1280, 800, false);
	}
}
