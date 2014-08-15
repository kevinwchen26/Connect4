package com.pennypop.project.model;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Acts as a Connect 4 Square on the board
 * 
 * @author Kevin
 *
 */
public class ConnectButton extends TextButton {

	/**
	 * The row that the button is in
	 */
	private int mRow;
	/**
	 * The column that the button is in
	 */
	private int mCol;
	/**
	 * The color of the button, grey if "empty", blue or green otherwise
	 */
	private String mColor;
	/**
	 * true if the square has a piece in it, false otherwise
	 */
	private boolean filled = false;

	/**
	 * 
	 * @param text
	 *            the text for the button, should be null;
	 * @param style
	 *            the style for the button
	 * @param row
	 *            the row the button is in
	 * @param col
	 *            the column the button is in
	 * @param color
	 *            the color of the piece that fills the square. Empty string if
	 *            the square is empty
	 */
	public ConnectButton(String text, TextButtonStyle style, int row, int col,
			String color) {
		super(text, style);
		this.mRow = row;
		this.mCol = col;
		this.mColor = color;
	}

	/**
	 * Gets the row that the square is in
	 * 
	 * @return the row that the square is in
	 */
	public int getMrow() {
		return mRow;
	}

	/**
	 * Gets the column that the square is in
	 * 
	 * @return the column that the square is in
	 */
	public int getMcol() {
		return mCol;
	}

	/**
	 * Gets the color of the piece that fills the square
	 * 
	 * @return the color of the piece that fills the square, empty string if the
	 *         square is emtpy
	 */
	public String getMcolor() {
		return mColor;
	}

	/**
	 * Called when a piece is inserted into the square
	 */
	public void fill() {
		this.filled = true;
	}

	/**
	 * Checks if the square contains a piece
	 * 
	 * @return true if the square contains a piece, false otherwise
	 */
	public boolean isFilled() {
		return this.filled;
	}

}
