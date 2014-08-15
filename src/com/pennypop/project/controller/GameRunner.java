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

	private ArrayList<Player> players;

	private static Player currentPlayer;

	public static void main(String[] args) {
		new LwjglApplication(new GameRunner(), "PennyPop", 1280, 720, true);
	}

	private GameRunner() {
		Player blue = new Player("Blue");
		Player green = new Player("Green");
		players = new ArrayList<Player>();
		players.add(blue);
		players.add(green);
		currentPlayer = blue;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void nextPlayer() {
		for (Player player : players) {
			if (player.getColor() != currentPlayer.getColor()) {
				currentPlayer = player;
			}
		}
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
