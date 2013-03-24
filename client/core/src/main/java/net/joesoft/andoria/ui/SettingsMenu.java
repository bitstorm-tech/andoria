package net.joesoft.andoria.ui;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import net.joesoft.andoria.utils.Settings;

public class SettingsMenu {
	private final Stage stage = new Stage();
	private final Window window;

	public SettingsMenu() {
		final Skin skin = Resources.getSkin();
		window = new Window("Settings Menu", skin);
		final CheckBox checkLight = new CheckBox("Light", skin);
		final CheckBox checkWireframe = new CheckBox("Wireframe Mode", skin);
		final CheckBox checkNormals = new CheckBox("Show Normals", skin);
		final CheckBox checkCoordinateSystem = new CheckBox("Show Coordinate System", skin);

		// layout ============================================================================================
		checkLight.setChecked(Settings.isLight());
		checkLight.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Settings.setLight(!Settings.isLight());
			}
		});
		window.add(checkLight).left();
		window.row();

		checkWireframe.setChecked(Settings.isWireframe());
		checkWireframe.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Settings.setWireframe(!Settings.isWireframe());
			}
		});
		window.add(checkWireframe).left();
		window.row();

		checkNormals.setChecked(Settings.isShowNormals());
		checkNormals.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Settings.setShowNormals(!Settings.isShowNormals());
			}
		});
		window.add(checkNormals).left();
		window.row();

		checkCoordinateSystem.setChecked(Settings.isShowCoordinateSystem());
		checkCoordinateSystem.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Settings.setShowCoordinateSystem(!Settings.isShowCoordinateSystem());
			}
		});
		window.add(checkCoordinateSystem).left();

		window.padTop(30);
		window.size(200, 400);
		window.setVisible(false);
		stage.addActor(window);
	}

	/**
	 * Shows the settings menu.
	 */
	public void render() {
			stage.draw();
	}

	/**
	 * This method either shows or hides the settings menu depending if it was shown or hidden befor.
	 */
	public void setVisibility(boolean visible) {
		window.setVisible(visible);
	}

	public boolean getVisibility() {
		return window.isVisible();
	}

	public InputAdapter getInputAdapter() {
		return stage;
	}
}
