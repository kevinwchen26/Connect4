package com.pennypop.project.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pennypop.project.view.GameScreen;

/**
 * Listener for the start button on the menu screen
 * 
 * @author Kevin
 *
 */
public class StartButtonListener extends ClickListener {

	/**
	 * instance of the game
	 */
	private GameRunner game;

	/**
	 * 
	 * @param game
	 *            instance of the current game
	 */
	public StartButtonListener(GameRunner game) {
		this.game = game;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		game.setScreen(new GameScreen(game));
	}

}
