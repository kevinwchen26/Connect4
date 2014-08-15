package com.pennypop.project.model;

public class Player {

	private String color;
	private int score;

	public Player(String color) {
		this.color = color;
		this.score = 0;
	}

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
