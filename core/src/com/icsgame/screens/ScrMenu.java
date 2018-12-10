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
import com.icsgame.soundEngine.SoundEngine;

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
    Texture txtAudioBackground;
    Sprite sprAudioBackground;
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
        txtAudioBackground = new Texture("extra/white.png");
        sprAudioBackground = new Sprite(txtAudioBackground, 100 , 100 , 100, 100);
        sprAudioBackground.setRegion(txtAudioBackground);
        sprAudioBackground.setAlpha(0.5f);
        sprAudioBackground.setPosition(20, 20);
        sprAudioBackground.setSize(220, 210);

        btnMusicUp = new Button(200, 70, 30, 30, "extra/btnPlus.png",
                "extra/btnPlus.png", "extra/btnPlus.png");
        btnMusicDown = new Button(170, 70, 30, 30, "extra/btnMinus.png",
                "extra/btnMinus.png", "extra/btnMinus.png");
        btnSoundUp = new Button(200, 170, 30, 30, "extra/btnPlus.png",
                "extra/btnPlus.png", "extra/btnPlus.png");
        btnSoundDown = new Button(170, 170, 30, 30, "extra/btnMinus.png",
                "extra/btnMinus.png", "extra/btnMinus.png");

        barMusic = new Bar("extra/greyLight.png", "extra/grey.png", 30, 30, 20, 1,
                10, 20, false);
        barSound = new Bar("extra/greyLight.png", "extra/grey.png", 30, 130, 20, 1,
                10, 20, false);

        // Create Font
        font = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(1f);
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
        batch.begin();
        sprAudioBackground.draw(batch);
        batch.end();

        btnMusicUp.update(batch);
        btnMusicDown.update(batch);
        barMusic.update((int)(main.scrScrGame.getSoundEngine().getVolMusic()*10), 10);

        btnSoundUp.update(batch);
        btnSoundDown.update(batch);
        barSound.update((int)(main.scrScrGame.getSoundEngine().getVolSound()*10), 10);

        batch.begin();
        barMusic.render(batch);
        font.draw(batch, "Music:", 30, 100);

        barSound.render(batch);
        font.draw(batch, "Sound:", 30, 200);
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
            if (main.scrScrGame.getSoundEngine().getVolMusic() < 1f) {
                main.scrScrGame.getSoundEngine().addVolMusic(0.1f);
            }
            System.out.println(main.scrScrGame.getSoundEngine().getVolMusic());
        }
        if (btnMusicDown.justClicked()) {
            if (main.scrScrGame.getSoundEngine().getVolMusic() > 0.1f) {
                main.scrScrGame.getSoundEngine().addVolMusic(-0.1f);
            } else if (main.scrScrGame.getSoundEngine().getVolMusic() > 0f) {
                main.scrScrGame.getSoundEngine().setVolMusic(0f);
            }
            System.out.println(main.scrScrGame.getSoundEngine().getVolMusic());
        }
        if (btnSoundUp.justClicked()) {
            if (main.scrScrGame.getSoundEngine().getVolSound() < 1f) {
                main.scrScrGame.getSoundEngine().addVolSound(0.1f);
            }
        }
        if (btnSoundDown.justClicked()) {
            if (main.scrScrGame.getSoundEngine().getVolSound() > 0f) {
                main.scrScrGame.getSoundEngine().addVolSound(-0.1f);
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
