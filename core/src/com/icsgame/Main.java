package com.icsgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.icsgame.game.GameMain;
import com.icsgame.menu.ScrHighscores;
import com.icsgame.menu.ScrMenu;
import com.icsgame.menu.ScrSetup;

public class Main extends Game {

	// Screens
	ScrMenu scrMenu;
	ScrHighscores scrHighscores;
	ScrSetup scrSetup;
	GameMain scrGameMain;

	// Width and Height Of Screen
	public int nWidth, nHeight;

	@Override
	public void create () {
		nWidth = Gdx.graphics.getWidth();
		nHeight = Gdx.graphics.getHeight();

		scrMenu = new com.icsgame.menu.ScrMenu(this);
		scrHighscores = new com.icsgame.menu.ScrHighscores(this);
		scrSetup = new com.icsgame.menu.ScrSetup(this);
		scrGameMain = new com.icsgame.game.GameMain(this);
		changeScreen(0);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
	}

	public void changeScreen(int nScreen) {
		switch (nScreen) {
			case 0:
				// Main Menu
				setScreen(scrMenu);
				break;
			case 1:
				// Instructions
				setScreen(scrHighscores);
				break;
			case 2:
				// Options
				setScreen(scrSetup);
				break;
			case 3:
				// Highscores
				setScreen(scrGameMain);
				break;
			default:
				break;
		}
	}
}
