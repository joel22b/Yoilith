package com.icsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.icsgame.Main;
import com.icsgame.objects.Button;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* ======================== ScrHighscores ================================
Implements a Screen
Displays the highscores to the player
Will grab data from a SQL database
================================================================== */

public class ScrHighscores implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnBack;
    BitmapFont fontHighscores, font;

    int[] arnScores;
    String[] arsScores;

    public ScrHighscores (Main _main){
        main = _main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnBack = new Button(660, 60, 600, 216, "btnBack.png", "btnBackPressed.png", "Blank.png");

        // Create Font
        fontHighscores = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontHighscores.setColor(Color.YELLOW);

        font = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        font.setColor(Color.WHITE);
        font.getData().setScale(1.8f);

        arnScores = new int[10];
        arsScores = new String[10];

        loadHighscores();
    }

    @Override
    public void render(float delta){
        batch.begin();

        // Draw Background
        sprBG.draw(batch);
        fontHighscores.draw( batch,"High Scores:", 60, main.nHeight-100);

        // Draw Highscores
        for (int i = 0; i < arnScores.length; i++) {
            font.draw(batch, arsScores[i], 400, 780-(i*50));
            font.draw(batch, Integer.toString(arnScores[i]), 900, 780-(i*50));
        }

        batch.end();

        // Update buttons
        btnBack.update(batch);

        // Checks if buttons are pressed
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnBack.justClicked()){
            main.changeScreen(0);
        }
    }

    public void loadHighscores() {

        // Load File
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("libs/highscores.properties");

            // load a properties file
            prop.load(input);

            for (int i = 0; i < 10; i++) {
                arnScores[i] = Integer.valueOf(prop.getProperty("score"+i));
                arsScores[i] = String.valueOf(prop.getProperty("name"+i));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
