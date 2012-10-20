package net.joesoft.andoria;

import net.joesoft.andoria.gfx.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Logger;

public class AndoriaGame extends Game {
	private final Logger log = new Logger(AndoriaActivity.class.getCanonicalName());

	public void create() {
		log.info("Andoria started");
		setScreen(new GameScreen());
	}

}
