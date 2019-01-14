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

import java.io.*;
import java.util.Properties;

public class ScrDeath implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnBack;
    BitmapFont font, fontBig, fontBigBack;

    int nScore = 0;
    String sName = "";

    public ScrDeath(Main main) {
        this.main = main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnBack = new Button(660, 60, 600, 216, "btnBack.png", "btnBackPressed.png", "Blank.png");

        // Create Font
        fontBig = new BitmapFont(Gdx.files.internal("fontCombo.fnt"));
        fontBig.setColor(Color.WHITE);
        fontBig.getData().setScale(2);

        fontBigBack = new BitmapFont(Gdx.files.internal("fontCombo.fnt"));
        fontBigBack.setColor(Color.BLACK);
        fontBigBack.getData().setScale(2);
    }

    @Override
    public void render(float delta) {
        batch.begin();

        // Draw Background
        sprBG.draw(batch);

        // Write Text
        fontBigBack.draw(batch, "Game Over!", (main.nWidth/2)-300+6, (main.nHeight/2)+300-6);
        fontBig.draw(batch, "Game Over!", (main.nWidth/2)-300, (main.nHeight/2)+300);

        // Write Score
        fontBigBack.draw(batch, "Score:", 100+6, (main.nHeight/2)+100-6);
        fontBig.draw(batch, "Score:", 100, (main.nHeight/2)+100);

        fontBigBack.draw(batch, Integer.toString(nScore), 450+6, (main.nHeight/2)+100-6);
        fontBig.draw(batch, Integer.toString(nScore), 450, (main.nHeight/2)+100);

        batch.end();

        // Update buttons
        btnBack.update(batch);

        // Checks if buttons are pressed
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnBack.justClicked()){
            main.changeScreen(2);
        }
    }

    /*public void saveToFile(){
        int[] arnScores = new int[10];

        Properties prop = new Properties();
        InputStream input = null;
        OutputStream output = null;

        try {
            input = new FileInputStream("libs/highscores.properties");
            output = new FileOutputStream("libs/highscores.properties");

            // set the properties value
            prop.setProperty("guiScale", Float.toString(guiScale));
            prop.setProperty("volumeGlobal", Float.toString(volumeGlobal));
            prop.setProperty("volumeMusic", Float.toString(volumeMusic));
            prop.setProperty("volumeSound", Float.toString(volumeSound));

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }*/

    public void setScore(int nScore) { this.nScore = nScore; }

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
