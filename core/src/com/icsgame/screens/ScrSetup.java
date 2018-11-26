package com.icsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
import com.icsgame.objects.Button;
import com.icsgame.objects.SelectionBox;

/* ======================== ScrSetup ================================
Implements a Screen
Lets the player configure the game and start a game
================================================================== */

public class ScrSetup implements Screen {

    Main main;
    ScrGame game;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnStart, btnBack;
    BitmapFont fontRed;
    SelectionBox sbTheme, sbGun, sbDifficulty;
    Texture[] txtThemeImages = new Texture[2], txtGunImages = new Texture[5], txtDifficultyImages = new Texture[5];
    String[] arsThemeReturns = new String[2], arsGunReturns = new String[5], arsDifficultyReturns = new String[5];

    public ScrSetup (Main _main, ScrGame game){
        main = _main;
        this.game = game;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnStart = new Button(20, 60, 600, 216, "btnStart.png", "btnStartPressed.png", "Blank.png");
        btnBack = new Button(660, 60, 600, 216, "btnBack.png", "btnBackPressed.png", "Blank.png");

        // Create Font
        fontRed = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontRed.setColor(Color.RED);

        // Create selection boxes
        arsThemeReturns[0] = "Desert";
        arsThemeReturns[1] = "Snow";
        for (int i = 0; i < 2; i++) {
            txtThemeImages[i] = new Texture("theme" + arsThemeReturns[i] + "/themeImage.png");
        }
        sbTheme = new SelectionBox(main.nWidth-340, 300, 250, 400, new Texture("selectionBackground.png"), "selectionButtonLeft.png", "selectionButtonRight.png", txtThemeImages, "Map Theme", arsThemeReturns);

        arsGunReturns[0] = "pistol";
        arsGunReturns[1] = "smg";
        arsGunReturns[2] = "sniper";
        arsGunReturns[3] = "shotgun";
        arsGunReturns[4] = "machine_gun";
        for (int i = 0; i < 5; i++) {
            txtGunImages[i] = new Texture("themeSnow/themeImage.png");
        }
        sbGun = new SelectionBox(main.nWidth-770, 300, 250, 400, new Texture("selectionBackground.png"), "selectionButtonLeft.png", "selectionButtonRight.png", txtGunImages, "Gun", arsGunReturns);

        arsDifficultyReturns[0] = "Grondin";
        txtDifficultyImages[0] = new Texture("Grondin.png");
        arsDifficultyReturns[1] = "Easy";
        txtDifficultyImages[1] = new Texture("thumbsUpGreen.png");
        arsDifficultyReturns[2] = "Normal";
        txtDifficultyImages[2] = new Texture("circleYellow.png");
        arsDifficultyReturns[3] = "Hard";
        txtDifficultyImages[3] = new Texture("thumbsDownRed.png");
        arsDifficultyReturns[4] = "Give up";
        txtDifficultyImages[4] = new Texture("skull.png");
        sbDifficulty = new SelectionBox(main.nWidth-1200, 300, 250, 400,
                new Texture("selectionBackground.png"), "selectionButtonLeft.png",
                "selectionButtonRight.png", txtDifficultyImages, "Difficulty", arsDifficultyReturns);
        sbDifficulty.setIndex(2);
    }

    @Override
    public void render(float delta){
        batch.begin();

        // Draw Background
        sprBG.draw(batch);
        fontRed.draw( batch,"Game Setup:", 60, main.nHeight-100);

        batch.end();

        // Update Buttons
        btnStart.update(batch);
        btnBack.update(batch);

        // Draw Selection Boxes
        sbTheme.render(batch);
        sbGun.render(batch);
        sbDifficulty.render(batch);

        // Checks if buttons are pressed
        checkButtons();
        sbTheme.checkButtons();
        sbGun.checkButtons();
        sbDifficulty.checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnStart.justClicked()){
            game.setupGame();
            main.changeScreen(3);
        }
        if(btnBack.justClicked()){
            main.changeScreen(0);
        }
    }

    public String getSbDifficultyReturn() { return sbDifficulty.getReturn(); }

    public String getSbGunReturn() { return sbGun.getReturn(); }

    public String getSbThemeReturn() { return sbTheme.getReturn(); }

    public void setScrGame(ScrGame game) {
        this.game = game;
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
