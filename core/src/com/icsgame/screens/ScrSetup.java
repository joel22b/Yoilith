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

public class ScrSetup implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnStart, btnBack;
    BitmapFont fontHighscores;
    SelectionBox sbTheme;
    Texture[] txtThemeImages = new Texture[2];
    String[] arsThemeReturns = new String[2];

    public ScrSetup (Main _main){
        main = _main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnStart = new Button(20, 60, 600, 216, "btnStart.png", "btnStartPressed.png", "Blank.png");
        btnBack = new Button(660, 60, 600, 216, "btnBack.png", "btnBackPressed.png", "Blank.png");

        // Create Font
        fontHighscores = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontHighscores.setColor(Color.RED);

        // Create selection boxes
        arsThemeReturns[0] = "Desert";
        arsThemeReturns[1] = "Snow";
        for (int i = 0; i < 2; i++) {
            txtThemeImages[i] = new Texture("theme" + arsThemeReturns[i] + "/themeImage.png");
        }
        sbTheme = new SelectionBox(main.nWidth-500, 300, 360, 576, new Texture("selectionBackground.png"), "selectionButtonLeft.png", "selectionButtonRight.png", txtThemeImages, "Map Theme", arsThemeReturns);
    }

    @Override
    public void render(float delta){
        batch.begin();

        // Draw Background
        sprBG.draw(batch);
        fontHighscores.draw( batch,"Game Setup:", 60, main.nHeight-100);

        batch.end();

        // Update Buttons
        btnStart.update(batch);
        btnBack.update(batch);

        // Draw Selection Boxes
        sbTheme.render(batch);

        // Checks if buttons are pressed
        checkButtons();
        sbTheme.checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnStart.justClicked()){
            main.scrScrGame.setupGame();
            main.changeScreen(3);
        }
        if(btnBack.justClicked()){
            main.changeScreen(0);
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
