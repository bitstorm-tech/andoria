package net.joesoft.andoria.client.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import net.joesoft.andoria.client.AndoriaGame;

/**
 * The "Main class" of Andoria Client.
 * 
 * @author josef.bauer.1st@gmail.com
 */
public class AndoriaActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		config.useGL20 = true;
		initialize(new AndoriaGame(), config);
	}
}
