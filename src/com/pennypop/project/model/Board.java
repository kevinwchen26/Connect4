package com.pennypop.project.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.pennypop.project.controller.ConnectButtonListener;

/**
 * Represents a Connect 4 board
 * 
 * @author Kevin
 *
 */
public class Board {
	private static Board board;
	/**
	 * number of spaces along the width. Default is 7
	 */
	private int numColumns = 7;
	/**
	 * number of spaces along the height. Default is 6
	 */
	private int numRows = 6;

	/**
	 * number of pieces of the same color that need to be lined up. Default is
	 * 4.
	 */
	private int connectN = 4;

	/**
	 * Instance of the current game
	 */
	private Game game;

	/**
	 * Contains the squares of the board, pieces[0][0] is the top left square
	 */
	private static ConnectButton[][] pieces;

	/**
	 * Constructor for a board with with default size of 7 columns and 6 rows
	 * with 4 pieces of the same color that need to be lined up.
	 */
	private Board(Game game) {
		this.game = game;
		initializeBoard();

	}

	/**
	 * Gets the number of columns in this board
	 * 
	 * @return the number of column on the board
	 */
	public int getColumns() {
		return this.numColumns;
	}

	/**
	 * Gets the number of rows in this board
	 * 
	 * @return the number of rows on the board
	 */
	public int getRows() {
		return this.numRows;
	}

	/**
	 * Checks if a newly inserted piece wins the game
	 * 
	 * @param newPiece
	 *            the piece that was just inserted
	 * @return true if the piece wins the game, false otherwise
	 */
	public boolean checkWin(ConnectButton newPiece) {
		int numUp = countPieces(newPiece, -1, 0, 0);
		int numDown = countPieces(newPiece, 1, 0, 0);
		int numRight = countPieces(newPiece, 0, 1, 0);
		int numLeft = countPieces(newPiece, 0, -1, 0);
		int numUpLeft = countPieces(newPiece, -1, -1, 0);
		int numUpRight = countPieces(newPiece, -1, 1, 0);
		int numDownLeft = countPieces(newPiece, 1, -1, 0);
		int numDownRight = countPieces(newPiece, 1, 1, 0);
		boolean win = false;
		if (numUp + numDown + 1 >= this.connectN)
			win = true;
		if (numRight + numLeft + 1 >= this.connectN)
			win = true;
		if (numUpLeft + numDownRight + 1 >= this.connectN)
			win = true;
		if (numUpRight + numDownLeft + 1 >= this.connectN)
			win = true;

		return win;

	}

	/**
	 * Checks the pieces in a given direction to see if they have the same color
	 * as the given piece.
	 * 
	 * @param piece
	 *            the piece to check the neighbors of
	 * @param vertical
	 *            vertical direction to move in. -1 to move up on the board, +1
	 *            to move down on the board,0 to stay on the same column
	 * @param horizontal
	 *            horizontal direction to move in. +1 to move right on the
	 *            board, -1 to move left on the board. 0 to stay on the same row
	 * @return the number of continous pieces in the given direction that have
	 *         the same color as the give piece
	 */
	private int countPieces(ConnectButton piece, int vertical, int horizontal,
			int count) {
		int row = piece.getMrow();
		int col = piece.getMcol();
		// check if given coordinates is within the board
		if (!checkCoordinates(row + horizontal, col + vertical))
			return count;
		ConnectButton neighbor = get(row + horizontal, col + vertical);
		// checks if a neighbor has a piece and if that piece is the same color
		if (!neighbor.isFilled()
				|| !neighbor.getMcolor().equals(piece.getMcolor()))
			return count;
		return countPieces(neighbor, vertical, horizontal, count + 1);
	}

	/**
	 * Checks if the given coordinates are within the game board
	 * 
	 * @param x
	 *            the x coordinates to check
	 * @param y
	 *            the y coordinates to check
	 * @return true if the coordinates are within the board, false otherwise
	 */
	private boolean checkCoordinates(int row, int col) {
		if (col < 0 || col >= this.numColumns)
			return false;
		if (row < 0 || row >= this.numRows)
			return false;
		return true;
	}

	/**
	 * Initialized the game board
	 */
	public void initializeBoard() {
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(
				Gdx.files.internal("buttons.pack"));
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("button_grey");

		Board.pieces = new ConnectButton[numRows][numColumns];
		for (int row = 0; row < this.numRows; row++) {
			for (int col = 0; col < this.numColumns; col++) {
				ConnectButton button = new ConnectButton("", textButtonStyle,
						row, col, "");// new buttons have no text and no color
				button.addListener(new ConnectButtonListener(game));

				Board.pieces[row][col] = button;

			}
		}

	}

	/**
	 * Inserts a piece into a column
	 * 
	 * @param column
	 *            the column to insert into
	 * @param piece
	 *            the piece to insert
	 * @return new piece if the insertion was successful, null otherwise
	 */
	public ConnectButton insertPiece(int column, String color,
			TextButtonStyle style) {
		if (!checkCoordinates(0, column) || Board.pieces[0][column].isFilled())
			return null;
		for (int row = numRows - 1; row >= 0; row--) {
			if (!Board.pieces[row][column].isFilled()) {
				ConnectButton piece = new ConnectButton("", style, row, column,
						color);
				piece.setChecked(true);
				Board.pieces[row][column] = piece;
				return piece;
			}
		}
		return null;

	}

	/**
	 * Returns the singleton instance of the board
	 * 
	 * @param game
	 *            an instance of the board
	 * @return an instance of the board
	 */
	public static Board getBoard(Game game) {
		if (board == null)
			board = new Board(game);
		return board;
	}

	/**
	 * Updates the board with the newly inserted piece
	 * 
	 * @param updatedButton
	 *            the square with the coloring to match the newly inserted piece
	 */
	public void replaceButton(ConnectButton updatedButton) {
		int row = updatedButton.getMrow();
		int col = updatedButton.getMcol();
		updatedButton.fill();
		updatedButton.setDisabled(true);
		pieces[row][col] = updatedButton;
	}

	/**
	 * 
	 * @param row
	 *            the row of the square
	 * @param col
	 *            the column of the square
	 * @return The square at the given coordinates
	 */
	public ConnectButton get(int row, int col) {
		return pieces[row][col];
	}
}
