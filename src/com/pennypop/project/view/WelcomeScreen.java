package com.pennypop.project.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.pennypop.project.controller.GameRunner;
import com.pennypop.project.controller.StartButtonListener;

/**
 * Screen with the option of starting the game
 * 
 * @author Kevin
 *
 */
public class WelcomeScreen implements Screen {

	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;
	private TextButtonStyle buttonStyle;
	private GameRunner game;
	private Stage stage;
	private SpriteBatch spriteBatch;

	public WelcomeScreen(GameRunner game) {

		this.game = game;
		spriteBatch = new SpriteBatch();
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false, spriteBatch);

		font = new BitmapFont();
		skin = new Skin();
		Table table = new Table(skin);
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.pack"));
		skin.addRegions(buttonAtlas);
		buttonStyle = new TextButtonStyle();

		buttonStyle.font = font;
		buttonStyle.up = skin.getDrawable("button_grey");

		TextButton startButton = new TextButton("Start Game", buttonStyle);
		startButton.addListener(new StartButtonListener(game));

		table.add(startButton);
		table.setFillParent(true);
		stage.addActor(table);
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
	public void pause() {
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		stage.dispose();
	}

	@Override
	public void render(float delta) {

		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

}
