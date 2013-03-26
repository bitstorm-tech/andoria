package net.joesoft.andoria.client;

import net.joesoft.andoria.client.gfx.GameScreen;

import com.badlogic.gdx.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndoriaGame extends Game {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public void create() {
		log.info("Andoria started");
		setScreen(new GameScreen());
	}
}
