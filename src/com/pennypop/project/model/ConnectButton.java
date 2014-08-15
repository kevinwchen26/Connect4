package com.pennypop.project.model;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ConnectButton extends TextButton {

	private int mrow;
	private int mcol;
	private String mcolor;

	public ConnectButton(String text, TextButtonStyle style) {
		super(text, style);
	}

	public void setStyle(TextButtonStyle style) {
		super.setStyle(style);
	}

	public int getMrow() {
		return mrow;
	}

	public void setMrow(int mrow) {
		this.mrow = mrow;
	}

	public int getMcol() {
		return mcol;
	}

	public void setMcol(int mcol) {
		this.mcol = mcol;
	}

	public String getMcolor() {
		return mcolor;
	}

	public void setMcolor(String mcolor) {
		this.mcolor = mcolor;
	}

}
