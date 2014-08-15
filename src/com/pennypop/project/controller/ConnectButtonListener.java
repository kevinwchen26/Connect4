package com.pennypop.project.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pennypop.project.model.Board;
import com.pennypop.project.model.ConnectButton;
import com.pennypop.project.model.Player;
import com.pennypop.project.view.GameScreen;

public class ConnectButtonListener extends ClickListener {

	private GameRunner game;

	public ConnectButtonListener(Game game) {
		this.game = (GameRunner) game;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		Sound button_click = Gdx.audio.newSound(Gdx.files
				.internal("button_click.wav"));
		button_click.play();
		ConnectButton button = (ConnectButton) event.getListenerActor();
		Board board = Board.getBoard(game);
		int row = button.getMrow();
		int col = button.getMcol();
		Player currentPlayer = game.getCurrentPlayer();
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(
				Gdx.files.internal("buttons.pack"));
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;

		if (currentPlayer.getColor().equals("Blue"))
			textButtonStyle.checked = skin.getDrawable("button_blue");
		else
			textButtonStyle.checked = skin.getDrawable("button_green");

		ConnectButton updatedButton = board.insertPiece(col,
				currentPlayer.getColor(), textButtonStyle);
		if (updatedButton != null) {
			board.replaceButton(updatedButton);
			if (board.checkWin(updatedButton)) {
				game.currentPlayerWin();
				Sound fanfare = Gdx.audio.newSound(Gdx.files
						.internal("victory_fanfare.mp3"));
				fanfare.play();
				WindowStyle style = new WindowStyle();
				style.titleFont = font;

				board.initializeBoard();

			}
		}
		GameRunner.nextPlayer();

		game.setScreen(new GameScreen(game));

	}
}
