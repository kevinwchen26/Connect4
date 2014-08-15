package com.pennypop.project.controller;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.pennypop.project.model.Player;
import com.pennypop.project.view.WelcomeScreen;

/**
 * The {@link ApplicationListener} for this project, create(), resize() and
 * render() are the only methods that are relevant
 * 
 * @author Kevin Chen
 * */
public class GameRunner extends Game {

	private static GameRunner game;
	Player blue;
	Player green;

	private static Player currentPlayer;

	public static void main(String[] args) {
		new LwjglApplication(new GameRunner(), "PennyPop", 1280, 720, true);
	}

	private GameRunner() {
		blue = new Player("Blue");
		green = new Player("Green");

		currentPlayer = blue;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void nextPlayer() {
		if (currentPlayer.getColor().equals("Blue"))
			currentPlayer = green;
		else
			currentPlayer = blue;
	}

	public static GameRunner getGame() {
		if (game == null)
			game = new GameRunner();
		return game;
	}

	@Override
	public void create() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		setScreen(new WelcomeScreen(this));
	}

}
