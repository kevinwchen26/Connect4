package com.pennypop.project.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pennypop.project.controller.ApiButtonListener;
import com.pennypop.project.controller.GameRunner;
import com.pennypop.project.controller.SFXButtonListener;
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
	private Stage stage;
	private SpriteBatch spriteBatch;
	private GameRunner game;
	private String apiURL = "http://api.openweathermap.org/data/2.5/weather?q=San%20Francisco,US";
	private Table table;

	public WelcomeScreen(GameRunner game) {

		this.game = game;
		createAssets();

		table = new Table(skin);

		Label pennypop = getLabel();
		ImageButton sfxButton = getSFXButton();
		ImageButton startButton = getGameButton();
		ImageButton apiButton = getApiButton();
		table.add(pennypop).width(100).height(100);
		table.row();
		table.add(sfxButton).width(100).height(100);
		table.add(apiButton).width(100).height(100);
		table.add(startButton).width(100).height(100);
		table.setFillParent(true);
		stage.addActor(table);
	}

	/**
	 * Used for API button
	 * 
	 * @param game
	 *            instance of the game
	 * @param data
	 *            array of data from api call
	 */
	public WelcomeScreen(GameRunner game, String[] data) {
		this(game);
		LabelStyle style = new LabelStyle(font, Color.BLACK);
		Label cityName = new Label(data[0], style);
		Label weather = new Label("Sky is " + data[1], style);
		Label temp = new Label(data[2] + " degrees, " + data[3] + "mph wind",
				style);
		Table apiTable = new Table(skin);
		apiTable.add(new Label("Current Weather", style));
		apiTable.row();
		apiTable.add(cityName);
		apiTable.row();
		apiTable.add(weather);
		apiTable.row();
		apiTable.add(temp);
		table.add(apiTable);
	}

	private void createAssets() {
		spriteBatch = new SpriteBatch();
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false, spriteBatch);

		font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
		skin = new Skin();
		buttonAtlas = new TextureAtlas("menuButtons.atlas");
		skin.addRegions(buttonAtlas);
	}

	private ImageButton getApiButton() {
		ImageButtonStyle style = new ImageButtonStyle();
		style.up = skin.getDrawable("apiButton");
		ImageButton button = new ImageButton(style);
		button.addListener(new ApiButtonListener(apiURL, game));
		return button;
	}

	/**
	 * Creates a button that plays a sound effect
	 * 
	 * @return the created button
	 */
	private ImageButton getSFXButton() {
		ImageButtonStyle style = new ImageButtonStyle();
		style.up = skin.getDrawable("sfxButton");
		ImageButton button = new ImageButton(style);
		button.addListener(new SFXButtonListener());
		return button;

	}

	/**
	 * Creates a button that starts the game
	 * 
	 * @return the created button
	 */
	private ImageButton getGameButton() {
		ImageButtonStyle style = new ImageButtonStyle();
		style.up = skin.getDrawable("gameButton");
		ImageButton button = new ImageButton(style);
		button.addListener(new StartButtonListener(game));
		return button;

	}

	/**
	 * Creates the PennyPop label
	 * 
	 * @return finished PennyPop label
	 */
	private Label getLabel() {
		LabelStyle style = new LabelStyle(font, Color.RED);
		Label pennypop = new Label("PennyPop", style);
		return pennypop;
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
		clearWhite();

		stage.act(delta);
		stage.draw();

	}

	private void clearWhite() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

}
