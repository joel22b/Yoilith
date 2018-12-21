package com.icsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
import com.icsgame.game.ui.AudioPanel;
import com.icsgame.objects.Bar;
import com.icsgame.objects.Button;

/* ======================== ScrMenu ================================
Implements a Screen
The Main Menu of the game
================================================================== */

public class ScrMenu implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnStart, btnHighscores, btnInstruct, btnScratch;
    AudioPanel audioPanel;

    public ScrMenu (Main _main){
        main = _main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnStart = new Button(main.nWidth/2-300, 330, 600, 216, "btnStart.png", "btnStartPressed.png", "Blank.png");
        btnHighscores = new Button(main.nWidth/2-300, 60, 600, 216, "btnHighscores.png", "btnHighscoresPressed.png", "Blank.png");
        btnScratch = new Button(main.nWidth-275, 60, 215, 136, "btnScratch.png", "btnScratchPressed.png", "Blank.png");
        btnInstruct = new Button(main.nWidth-150, main.nHeight-150, 100, 100, "extra/btnHelp.png",
                "extra/btnHelpPressed.png", "Blank.png");

        audioPanel = new AudioPanel(main.scrScrGame, 20, 20, "white", false);
    }

    @Override
    public void render(float delta){
        batch.begin();

        // Draw Background
        sprBG.draw(batch);

        batch.end();

        // Update buttons
        btnStart.update(batch);
        btnHighscores.update(batch);
        btnScratch.update(batch);
        btnInstruct.update(batch);

        audioPanel.update(batch);

        // Checks if buttons are pressed
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnStart.justClicked()){
            main.changeScreen(2);
        }
        if(btnHighscores.justClicked()){
            main.changeScreen(1);
        }
        if(btnScratch.justClicked()){
            main.changeScreen(4);
        }
        if(btnInstruct.justClicked()){
            main.changeScreen(7);
        }
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
