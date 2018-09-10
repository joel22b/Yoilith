package com.icsgame.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
import com.icsgame.objects.Button;

public class ScrSetup implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnStart, btnBack;
    BitmapFont fontHighscores;

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
    }

    @Override
    public void render(float delta){
        batch.begin();
        // Draw Background
        sprBG.draw(batch);
        fontHighscores.draw( batch,"Player Setup:", 60, main.nHeight-100);

        // Draw Buttons
        btnStart.draw(batch);
        btnBack.draw(batch);

        batch.end();

        // Checks if buttons are pressed
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        checkButtonTextures();
        if(btnStart.justClicked()){
            main.scrGameMain.setupGame();
            main.changeScreen(3);
        }
        if(btnBack.justClicked()){
            main.changeScreen(0);
        }
    }

    private void checkButtonTextures(){
        if(btnStart.isMousedOver()){
            btnStart.changeTexture(1);
        } else {
            btnStart.changeTexture(0);
        }
        if(btnBack.isMousedOver()){
            btnBack.changeTexture(1);
        } else {
            btnBack.changeTexture(0);
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
