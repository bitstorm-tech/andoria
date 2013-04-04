package net.bitstorm.engine.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.tablelayout.Cell;
import net.bitstorm.engine.utils.Resources;
import net.bitstorm.engine.utils.Settings;

public class SettingsMenu extends Window {

	public SettingsMenu() {
		super("Settings Menu", Resources.getSkin());

		addCheckBox(" Light", Settings.Key.ENGINE_LIGHT).space(5);
		row();
		addCheckBox(" Wireframe Mode", Settings.Key.ENGINE_WIREFRAME).space(5);
		row();
		addCheckBox(" Show Normals", Settings.Key.ENGINE_NORMALS_SHOW).space(5);
		row();
		addCheckBox(" Show Coordinate System", Settings.Key.ENGINE_COORDINATESYSTEM_SHOW).space(5);
		row();
		addCheckBox(" Show FPS", Settings.Key.UI_SHOWFPS).space(5);
		row();
		addCheckBox(" Show Skybox", Settings.Key.ENGINE_SKYBOX_SHOW).space(5);

		padTop(30);
		setHeight(400);
		setWidth(250);
		setVisible(false);
	}

	private Cell addCheckBox (String text, Settings.Key key) {
		final CheckBox checkBox = new CheckBox(text, Resources.getSkin());
		final Settings.Key _key = key;

		checkBox.setChecked(Settings.getBoolean(_key));
		checkBox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				final boolean value = Settings.getBoolean(_key);
				Settings.setBoolean(_key, !value);
			}
		});

		return add(checkBox).left();
	}
}
