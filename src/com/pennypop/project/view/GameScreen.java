package com.pennypop.project.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.pennypop.project.controller.GameRunner;
import com.pennypop.project.model.Board;
import com.pennypop.project.model.ConnectButton;

/**
 * This is where you screen code will go, any UI should be in here
 * 
 * @author Richard Taylor
 */
public class GameScreen implements Screen {

	private final Stage stage;
	private final SpriteBatch spriteBatch;
	private BitmapFont font;
	private TextureAtlas buttonAtlas;
	private TextButtonStyle textButtonStyle;
	private Skin skin;
	private Game game;

	public GameScreen() {
		this.game = GameRunner.getGame();
		spriteBatch = new SpriteBatch();
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false, spriteBatch);
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.pack"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("button_grey");
		Board board = Board.getBoard();
		Table table = new Table(skin);
		for (int row = 0; row < board.getRows(); row++) {
			for (int col = 0; col < board.getColumns(); col++) {
				ConnectButton button = new ConnectButton("", textButtonStyle);
				button.setMrow(row);
				button.setMcol(col);
				table.add(button).width(100).height(100);

			}
			table.row();
		}
		table.setFillParent(true);

		stage.addActor(table);

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
