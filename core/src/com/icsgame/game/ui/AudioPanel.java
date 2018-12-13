package com.icsgame.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.objects.Bar;
import com.icsgame.objects.Button;
import com.icsgame.screens.ScrGame;

public class AudioPanel {

    // Width: 220
    // Height: 210

    ScrGame game;

    Vector2 vPos;
    boolean bCamera;

    Texture txtAudioBackground;
    Sprite sprAudioBackground;
    Button btnMusicUp, btnMusicDown, btnSoundUp, btnSoundDown;
    BitmapFont font;
    Bar barMusic, barSound;

    public AudioPanel(ScrGame game, float fX, float fY, String sBackgroundColor, boolean bCamera) {
        this.game = game;
        this.bCamera = bCamera;

        vPos = new Vector2(fX, fY);

        // Create Audio Settings
        txtAudioBackground = new Texture("extra/"+sBackgroundColor+".png");
        sprAudioBackground = new Sprite(txtAudioBackground, 100 , 100 , 100, 100);
        sprAudioBackground.setRegion(txtAudioBackground);
        sprAudioBackground.setAlpha(0.5f);
        sprAudioBackground.setPosition(vPos.x, vPos.y);
        sprAudioBackground.setSize(220, 210);

        btnMusicUp = new Button((int)vPos.x + 180, (int)vPos.y + 50, 30, 30, "extra/btnPlus.png",
                "extra/btnPlus.png", "extra/btnPlus.png");
        btnMusicDown = new Button((int)vPos.x + 150, (int)vPos.y + 50, 30, 30, "extra/btnMinus.png",
                "extra/btnMinus.png", "extra/btnMinus.png");
        btnSoundUp = new Button((int)vPos.x + 180, (int)vPos.y + 150, 30, 30, "extra/btnPlus.png",
                "extra/btnPlus.png", "extra/btnPlus.png");
        btnSoundDown = new Button((int)vPos.x + 150, (int)vPos.y + 150, 30, 30, "extra/btnMinus.png",
                "extra/btnMinus.png", "extra/btnMinus.png");

        barMusic = new Bar("extra/greyLight.png", "extra/grey.png", (int)vPos.x + 10,
                (int)vPos.y + 10, 20, 1, 10, 20, false);
        barSound = new Bar("extra/greyLight.png", "extra/grey.png", (int)vPos.x + 10,
                (int)vPos.y + 110, 20, 1, 10, 20, false);

        // Create Font
        font = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(1f);
    }

    public void update(SpriteBatch batch) {
        if (bCamera) {
            checkButtonsCamera();
        } else {
            checkButtons();
        }

        // Render audio settings
        batch.begin();
        sprAudioBackground.draw(batch);
        batch.end();

        btnMusicUp.update(batch);
        btnMusicDown.update(batch);
        barMusic.update((int)vPos.x + 10, (int)vPos.y + 10, (int)(game.getSoundEngine().getVolMusic()*10), 10);

        btnSoundUp.update(batch);
        btnSoundDown.update(batch);
        barSound.update((int)vPos.x + 10, (int)vPos.y + 110, (int)(game.getSoundEngine().getVolSound()*10), 10);

        batch.begin();
        barMusic.render(batch);
        font.draw(batch, "Music:", (int)vPos.x + 10, (int)vPos.y + 80);

        barSound.render(batch);
        font.draw(batch, "Sound:", (int)vPos.x + 10, (int)vPos.y + 180);
        batch.end();
    }

    public void checkButtons() {
        // Check Audio Buttons
        if (btnMusicUp.justClicked()) {
            if (game.getSoundEngine().getVolMusic() < 1f) {
                game.getSoundEngine().addVolMusic(0.1f);
            }
            System.out.println(game.getSoundEngine().getVolMusic());
        }
        if (btnMusicDown.justClicked()) {
            if (game.getSoundEngine().getVolMusic() > 0.1f) {
                game.getSoundEngine().addVolMusic(-0.1f);
            } else if (game.getSoundEngine().getVolMusic() > 0f) {
                game.getSoundEngine().setVolMusic(0f);
            }
            System.out.println(game.getSoundEngine().getVolMusic());
        }
        if (btnSoundUp.justClicked()) {
            if (game.getSoundEngine().getVolSound() < 1f) {
                game.getSoundEngine().addVolSound(0.1f);
            }
        }
        if (btnSoundDown.justClicked()) {
            if (game.getSoundEngine().getVolSound() > 0f) {
                game.getSoundEngine().addVolSound(-0.1f);
            }
        }
    }

    public void checkButtonsCamera() {
        // Check Audio Buttons
        if (btnMusicUp.justClicked(game.getCamera().getCamera())) {
            if (game.getSoundEngine().getVolMusic() < 1f) {
                game.getSoundEngine().addVolMusic(0.1f);
            }
            System.out.println(game.getSoundEngine().getVolMusic());
        }
        if (btnMusicDown.justClicked(game.getCamera().getCamera())) {
            if (game.getSoundEngine().getVolMusic() > 0.1f) {
                game.getSoundEngine().addVolMusic(-0.1f);
            } else if (game.getSoundEngine().getVolMusic() > 0f) {
                game.getSoundEngine().setVolMusic(0f);
            }
            System.out.println(game.getSoundEngine().getVolMusic());
        }
        if (btnSoundUp.justClicked(game.getCamera().getCamera())) {
            if (game.getSoundEngine().getVolSound() < 1f) {
                game.getSoundEngine().addVolSound(0.1f);
            }
        }
        if (btnSoundDown.justClicked(game.getCamera().getCamera())) {
            if (game.getSoundEngine().getVolSound() > 0f) {
                game.getSoundEngine().addVolSound(-0.1f);
            }
        }
    }

    public void setPosition(Vector2 vPos) {
        this.vPos.set(vPos);

        sprAudioBackground.setPosition(vPos.x, vPos.y);

        btnMusicUp.setX(vPos.x + 180);
        btnMusicUp.setY(vPos.y + 50);
        btnMusicDown.setX(vPos.x + 150);
        btnMusicDown.setY(vPos.y + 50);

        btnSoundUp.setX(vPos.x + 180);
        btnSoundUp.setY(vPos.y + 150);
        btnSoundDown.setX(vPos.x + 150);
        btnSoundDown.setY(vPos.y + 150);
    }
}
