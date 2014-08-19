package com.pennypop.project.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pennypop.project.view.WelcomeScreen;

public class MenuButtonListener extends ClickListener {
	/**
	 * instance of the game
	 */
	private GameRunner game;

	/**
	 * 
	 * @param game
	 *            instance of the current game
	 */
	public MenuButtonListener(GameRunner game) {
		this.game = game;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		game.setScreen(new WelcomeScreen(game));
	}
}
