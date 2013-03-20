package net.joesoft.andoria.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SettingsMenu {
	private final Stage stage = new Stage();
	private final Table table = new Table();
	private final TextButton button1;
	private boolean visible = false;

	public SettingsMenu() {
		final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Font.getBitmapFont();
		button1 = new TextButton("Mein erster Button", textButtonStyle);

		table.setFillParent(true);
		table.add(button1);
		stage.addActor(table);
	}

	/**
	 * Shows the settings menu.
	 */
	public void show() {
		if(visible) {
			stage.draw();
		}
	}

	/**
	 * This method either shows or hides the settings menu depending if it was shown or hidden befor.
	 */
	public void setVisibility(boolean visible) {
		this.visible = visible;
	}

	public boolean getVisibility() {
		return visible;
	}
}
