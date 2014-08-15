package com.pennypop.project.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pennypop.project.view.GameScreen;

public class StartButtonListener extends ChangeListener {

	private GameRunner game;

	public StartButtonListener(GameRunner game) {
		this.game = game;
	}

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		game.setScreen(new GameScreen(game));
	}

}
