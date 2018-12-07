package com.icsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
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
    Button btnStart, btnHighscores, btnScratch;
    Button btnMusicUp, btnMusicDown, btnSoundUp, btnSoundDown;
    BitmapFont font;
    Bar barMusic, barSound;

    public ScrMenu (Main _main){
        main = _main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnStart = new Button(main.nWidth/2-300, 330, 600, 216, "btnStart.png", "btnStartPressed.png", "Blank.png");
        btnHighscores = new Button(main.nWidth/2-300, 60, 600, 216, "btnHighscores.png", "btnHighscoresPressed.png", "Blank.png");
        btnScratch = new Button(main.nWidth-275, 60, 215, 136, "btnScratch.png", "btnScratchPressed.png", "Blank.png");

        // Create Audio Settings
        btnMusicUp = new Button(200, 70, 30, 30, "extra/btnPlus.png",
                "extra/btnPlus.png", "extra/btnPlus.png");
        btnMusicDown = new Button(170, 70, 30, 30, "extra/btnMinus.png",
                "extra/btnMinus.png", "extra/btnMinus.png");
        btnSoundUp = new Button(0, 0, 50, 50, "extra/btnPlus.png",
                "extra/btnPlus.png", "extra/btnPlus.png");
        btnSoundDown = new Button(0, 0, 50, 50, "extra/btnMinus.png",
                "extra/btnMinus.png", "extra/btnMinus.png");

        barMusic = new Bar("extra/grey.png", "extra/blue.png", 30, 30, 20, 1, 10,
                20, false);
        barSound = new Bar("extra/grey.png", "extra/blue.png", 0, 0, 20, 1, 10,
                20, false);

        // Create Font
        font = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        font.setColor(Color.WHITE);
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

        // Render audio settings
        btnMusicUp.update(batch);
        btnMusicDown.update(batch);
        barMusic.update((int)(main.scrScrGame.getSoundEngine().getVolMusic()*10), 10);
        batch.begin();
        barMusic.render(batch);
        batch.end();

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

        // Check Audio Buttons
        if (btnMusicUp.justClicked()) {

        }
        if (btnMusicDown.justClicked()) {

        }
        if (btnSoundUp.justClicked()) {

        }
        if (btnSoundDown.justClicked()) {

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
