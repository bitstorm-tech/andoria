package net.joesoft.andoria;

import net.joesoft.andoria.gfx.GameScreen;
import net.joesoft.andoria.utils.Log;

import com.badlogic.gdx.Game;

public class AndoriaGame extends Game {
	private final Log log = new Log(this.getClass());

	public void create() {
		log.info("Andoria started");
		setScreen(new GameScreen());
	}

}
