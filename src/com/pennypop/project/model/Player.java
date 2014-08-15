package com.pennypop.project.model;

/**
 * Represents a player
 * 
 * @author Kevin
 *
 */
public class Player {

	/**
	 * The players color
	 */
	private String color;
	/**
	 * The players score
	 */
	private int score;

	public Player(String color) {
		this.color = color;
		this.score = 0;
	}

	/**
	 * Increments the players score
	 * 
	 * @return updated version of the player object
	 */
	public Player addWin() {
		this.score = this.score + 1;
		return this;
	}

	public String getColor() {
		return this.color;
	}

	public int getScore() {
		return this.score;
	}
}
