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

public class ScrScratch implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    int nScratchX = 50, nScratchY = 0;
    Button btnBack;
    Button[] arbtnScratches;
    String[] arsScratches;
    BitmapFont fontWhite, fontBlack;

    public ScrScratch(Main main) {
        this.main = main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnBack = new Button(660, 60, 600, 216, "btnBack.png", "btnBackPressed.png", "Blank.png");

        arbtnScratches = new Button[1];
        arsScratches = new String[1];
        arbtnScratches[0] = new Button(0, 0, 375, 375, "btnScratchTemplate.png", "btnScratchTemplate.png", "Blank.png");
        arsScratches[0] = "Line Of Sight";

        nScratchY = main.nHeight/2-100;

        // Setup Locations
        for (int i = 0; i < arbtnScratches.length; i++) {
            if ((arbtnScratches[i].getX()+arbtnScratches[i].getW()+50)*i+nScratchX <= main.nWidth) {
                arbtnScratches[i].setX((arbtnScratches[i].getX()+arbtnScratches[i].getW()+50)*i+nScratchX);
                arbtnScratches[i].setY(nScratchY);
            } else {
                arbtnScratches[i].setX((arbtnScratches[i].getX()+arbtnScratches[i].getW()+50)*i+nScratchX);
                arbtnScratches[i].setY((int)(((arbtnScratches[i].getX()+arbtnScratches[i].getW()+50)*i)/main.nWidth)
                *(-1)*(arbtnScratches[i].getH()+50)+nScratchY);
            }
        }

        // Create Font
        fontWhite = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontWhite.setColor(Color.WHITE);
        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);
    }

    @Override
    public void render(float delta) {
        batch.begin();

        // Draw Background
        sprBG.draw(batch);
        fontWhite.draw( batch,"Scratches:", 60, main.nHeight-100);

        batch.end();

        // Update buttons
        btnBack.update(batch);

        for (int i = 0; i < arbtnScratches.length; i++) {
            arbtnScratches[i].update(batch);

            batch.begin();
            fontBlack.draw( batch,arsScratches[i], arbtnScratches[i].getX()+20,
                    arbtnScratches[i].getY()+(arbtnScratches[i].getH()/2+20));
            batch.end();
        }

        // Checks if buttons are pressed
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnBack.justClicked()){
            main.changeScreen(0);
        }
        if (arbtnScratches[0].justClicked()) {
            // Line of Sight Scratch
            main.changeScreen(5);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
