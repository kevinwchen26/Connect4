package com.pennypop.project.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.pennypop.project.controller.ConnectButtonListener;

/**
 * Represents a connect 4 board
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
	private Game game;

	/**
	 * Contains the pieces that have been inserted. The top right corner of the
	 * board is 0,0. The bottom left corner is numColumns,numRows
	 */
	private static ConnectButton[][] pieces;

	/**
	 * Constructor for a board with with default size of 7 columns and 6 rows
	 * with 4 pieces of the same color that need to be lined up.
	 */
	public Board(Game game) {
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
		int numUp = countPieces(newPiece, -1, 0);
		int numDown = countPieces(newPiece, 1, 0);
		int numRight = countPieces(newPiece, 0, 1);
		int numLeft = countPieces(newPiece, 0, -1);
		int numUpLeft = countPieces(newPiece, -1, -1);
		int numUpRight = countPieces(newPiece, -1, 1);
		int numDownLeft = countPieces(newPiece, 1, -1);
		int numDownRight = countPieces(newPiece, 1, 1);
		if (numUp + numDown >= this.connectN)
			return true;
		if (numRight + numLeft >= this.connectN)
			return true;
		if (numUpLeft + numDownRight >= this.connectN)
			return true;
		if (numUpRight + numDownLeft >= this.connectN)
			return true;
		return false;

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
	private int countPieces(ConnectButton piece, int vertical, int horizontal) {
		int row = piece.getMrow();
		int col = piece.getMcol();
		if (!checkCoordinates(row + horizontal, col + vertical))
			return 0;
		ConnectButton neighbor = this
				.getPiece(row + horizontal, col + vertical);
		if (neighbor == null || neighbor.getColor() != piece.getColor())
			return 0;
		return countPieces(neighbor, vertical, horizontal) + 1;
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
	private boolean checkCoordinates(int x, int y) {
		if (x < 0 || x >= this.numColumns)
			return false;
		if (y < 0 || y >= this.numRows)
			return false;
		return true;
	}

	private ConnectButton getPiece(int i, int j) {
		return Board.pieces[i][j];
	}

	/**
	 * Initialized the game board by filling all of the spaces with null values
	 */
	private void initializeBoard() {
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(
				Gdx.files.internal("buttons.pack"));
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("button_grey");
		Board.pieces = new ConnectButton[numRows][numColumns];
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				ConnectButton button = new ConnectButton("", textButtonStyle);
				button.addListener(new ConnectButtonListener(game));

				button.setMrow(i);
				button.setMcol(j);
				button.setMcolor("");
				Board.pieces[i][j] = button;

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
				ConnectButton piece = new ConnectButton("", style);
				piece.setMrow(row);
				piece.setMcol(column);
				piece.setMcolor(color);
				piece.setChecked(true);
				Board.pieces[row][column] = piece;
				return piece;
			}
		}
		return null;

	}

	public static Board getBoard(Game game) {
		if (board == null)
			board = new Board(game);
		return board;
	}

	public void replaceButton(ConnectButton updatedButton) {
		int row = updatedButton.getMrow();
		int col = updatedButton.getMcol();
		updatedButton.fill();
		pieces[row][col] = updatedButton;
	}

	public ConnectButton get(int row, int col) {
		// TODO Auto-generated method stub
		return pieces[row][col];
	}
}
