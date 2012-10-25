package net.joesoft.andoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import net.joesoft.andoria.gfx.GameScreen;
import net.joesoft.andoria.utils.Context;
import net.joesoft.andoria.utils.Log;

import com.badlogic.gdx.Game;

public class AndoriaGame extends Game {
	private final Log log = new Log(this.getClass());

	public void create() {
		log.info("Andoria started");
		final InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(Context.keyboardProcessor);
		multiplexer.addProcessor(Context.mouseProcessor);
		Gdx.input.setInputProcessor(multiplexer);
		setScreen(new GameScreen());
	}

}
