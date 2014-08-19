package com.pennypop.project.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pennypop.project.view.WelcomeScreen;

public class ApiButtonListener extends ClickListener implements EventListener {

	private String URL;
	private GameRunner game;

	/**
	 * @param URL
	 *            the url for the HTTP get
	 * @param game
	 */
	public ApiButtonListener(String URL, GameRunner game) {
		this.URL = URL;
		this.game = game;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
		httpGet.setUrl(URL);
		Gdx.net.sendHttpRequest(httpGet, new HttpResponseListener() {
			private String response;

			public void handleHttpResponse(HttpResponse httpResponse) {
				response = httpResponse.getResultAsString();
				JsonParser parser = new JsonParser();
				JsonElement elements = parser.parse(response);
				JsonObject array = elements.getAsJsonObject();
				JsonElement name = array.get("name");

				JsonArray weather = array.get("weather").getAsJsonArray();
				JsonObject obj = weather.get(0).getAsJsonObject();
				JsonElement main = obj.get("main");
				JsonElement speed = array.get("wind").getAsJsonObject()
						.get("speed");
				JsonElement temp = array.get("main").getAsJsonObject()
						.get("temp");

				String[] data = { name.getAsString(), main.getAsString(),
						temp.getAsString(), speed.getAsString() };
				game.setScreen(new WelcomeScreen(game, data));
			}

			public void failed(Throwable t) {
				response = "failed";
				// do stuff here based on the failed attempt
			}
		});

	}
}
