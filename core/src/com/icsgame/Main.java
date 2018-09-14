package com.icsgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.icsgame.screens.ScrGame;
import com.icsgame.screens.ScrHighscores;
import com.icsgame.screens.ScrMenu;
import com.icsgame.screens.ScrSetup;

public class Main extends Game {

	// Screens
	ScrMenu scrMenu;
	ScrHighscores scrHighscores;
	ScrSetup scrSetup;
	public ScrGame scrScrGame;

	// Width and Height Of Screen
	public int nWidth, nHeight;

	@Override
	public void create () {
		nWidth = Gdx.graphics.getWidth();
		nHeight = Gdx.graphics.getHeight();

		scrMenu = new com.icsgame.screens.ScrMenu(this);
		scrHighscores = new com.icsgame.screens.ScrHighscores(this);
		scrSetup = new com.icsgame.screens.ScrSetup(this);
		scrScrGame = new ScrGame(this);
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
				// Highscores
				setScreen(scrHighscores);
				break;
			case 2:
				// Game Setup
				setScreen(scrSetup);
				break;
			case 3:
				// Game
				setScreen(scrScrGame);
				break;
			default:
				break;
		}
	}
}
