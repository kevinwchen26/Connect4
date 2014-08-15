package com.pennypop.project.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pennypop.project.controller.GameRunner;
import com.pennypop.project.model.Board;
import com.pennypop.project.model.ConnectButton;
import com.pennypop.project.model.Player;

/**
 * This is where you screen code will go, any UI should be in here
 * 
 * @author Kevin Chen
 */
public class GameScreen implements Screen {

	private Stage stage;
	private SpriteBatch spriteBatch;

	private GameRunner game;
	private Table root;

	public GameScreen(GameRunner game) {
		this.game = game;

	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		stage.dispose();
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);

		stage.act(delta);
		// Table.drawDebug(stage);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false, spriteBatch);
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(
				Gdx.files.internal("buttons.pack"));
		skin.addRegions(buttonAtlas);

		Board board = Board.getBoard(game);
		Table table = new Table(skin);
		for (int row = 0; row < board.getRows(); row++) {
			for (int col = 0; col < board.getColumns(); col++) {
				ConnectButton button = board.get(row, col);

				table.add(button).width(100).height(100);
			}
			table.row();
		}
		ArrayList<Player> players = game.getPlayers();
		root = new Table(skin).debug();
		LabelStyle labelStyle = new LabelStyle(font, Color.BLACK);
		Label currentPlayer = new Label(game.getCurrentPlayer().getColor()
				+ "'s turn", labelStyle);
		if (game.getCurrentWinner() != null) {
			Label winner = new Label(game.getCurrentWinner().getColor()
					+ " WINS!", labelStyle);
			root.add(winner);
		}
		root.row();
		root.add(currentPlayer).center();
		root.row();
		Label p_1 = new Label("Player " + players.get(0).getColor(), labelStyle);
		Label p_1_score = new Label(" " + players.get(0).getScore(), labelStyle);
		root.add(p_1);
		root.add(p_1_score);
		root.add(table);
		Label p_2 = new Label("Player " + players.get(1).getColor(), labelStyle);
		Label p_2_score = new Label(" " + players.get(1).getScore(), labelStyle);
		root.add(p_2);
		root.add(p_2_score);
		root.setFillParent(true);
		stage.addActor(root);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void pause() {
		// Irrelevant on desktop, ignore this
	}

	@Override
	public void resume() {
		// Irrelevant on desktop, ignore this
	}

}
