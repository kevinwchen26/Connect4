package com.pennypop.project.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pennypop.project.view.GameScreen;

public class StartButtonListener extends ChangeListener {

	@Override
	public void changed(ChangeEvent arg0, Actor arg1) {
		Game game = GameRunner.getGame();
		game.setScreen(new GameScreen());
	}

}
