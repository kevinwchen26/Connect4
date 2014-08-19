package com.pennypop.project.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SFXButtonListener extends ClickListener {
	@Override
	public void clicked(InputEvent event, float x, float y) {
		Sound button_click = Gdx.audio.newSound(Gdx.files
				.internal("button_click.wav"));
		button_click.play();
	}
}
