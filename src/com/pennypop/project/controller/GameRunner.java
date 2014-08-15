package com.pennypop.project.controller;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.pennypop.project.model.Player;
import com.pennypop.project.view.WelcomeScreen;

/**
 * The {@link ApplicationListener} for this project, create(), resize() and
 * render() are the only methods that are relevant
 * 
 * Initializes and runs the game
 * 
 * @author Kevin Chen
 * */
public class GameRunner extends Game {

	/**
	 * The two players of the game, Players Blue and Green
	 */
	static Player blue;
	static Player green;
	/**
	 * The last winner
	 */
	static Player currentWinner = null;
	/**
	 * The current Player
	 */
	private static Player currentPlayer;

	public static void main(String[] args) {
		new LwjglApplication(new GameRunner(), "Connect 4", 1280, 720, true);
	}

	/**
	 * instantiates the players and sets the current player to be Player Blue
	 */
	private GameRunner() {
		blue = new Player("Blue");
		green = new Player("Green");

		currentPlayer = blue;
	}

	/**
	 * Gets the current player
	 * 
	 * @return the Player who needs to make a move
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Switches players
	 */
	public static void nextPlayer() {
		if (currentPlayer.getColor().equals("Blue"))
			currentPlayer = green;
		else
			currentPlayer = blue;
	}

	@Override
	public void create() {
		setScreen(new WelcomeScreen(this));
	}

	/**
	 * Increments the score for the winner and sets the current player as the
	 * winner
	 */
	public void currentPlayerWin() {
		if (currentPlayer.getColor().equals("Blue"))
			blue = blue.addWin();
		else
			green = green.addWin();
		currentWinner = currentPlayer;
	}

	/**
	 * Returns a list of players. Used to print the scoreboard
	 * 
	 * @return a list containing the players
	 */
	public ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(blue);
		players.add(green);
		return players;
	}

	/**
	 * 
	 * @return the current winner. Used to print the winner.
	 */
	public Player getCurrentWinner() {
		return currentWinner;
	}

	/**
	 * Used to remove the winner label
	 */
	public void removeWinner() {
		if (currentWinner != null)
			currentWinner = null;
	}

}
