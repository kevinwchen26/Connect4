package com.pennypop.project.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pennypop.project.view.GameScreen;

public class StartButtonListener extends ClickListener {

	private GameRunner game;

	public StartButtonListener(GameRunner game) {
		this.game = game;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		game.setScreen(new GameScreen(game));
	}

}
