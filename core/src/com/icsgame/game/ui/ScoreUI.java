package com.icsgame.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.game.utils.Camera;

public class ScoreUI {

    Camera camera;

    int nScore;

    BitmapFont font;

    Texture txtBackground;
    Sprite sprBackground;

    public ScoreUI(Camera camera) {
        this.camera = camera;

        txtBackground = new Texture("extra/black.png");
        sprBackground = new Sprite(txtBackground, 100 , 100 , 100, 100);
        sprBackground.setRegion(txtBackground);
        sprBackground.setAlpha(0.5f);
        sprBackground.setSize(400, 80);

        nScore = 0;

        font = new BitmapFont(Gdx.files.internal("fontScore.fnt"));
        font.setColor(Color.WHITE);
    }

    public void render(SpriteBatch batch) {
        batch.begin();

        // Render Black Score Background
        sprBackground.setPosition(camera.getX()+(camera.getW()/2)-20-sprBackground.getWidth(),
                camera.getY()+(camera.getH()/2)-20-sprBackground.getHeight());
        sprBackground.draw(batch);

        // Render the score
        font.draw(batch, Integer.toString(nScore), camera.getX()+(camera.getW()/2)-20-sprBackground.getWidth()+40,
                camera.getY()+(camera.getH()/2)-20-sprBackground.getHeight()+60);

        batch.end();
    }

    public void addScore(int nAdd) { nScore += nAdd; }

    public void setScore(int nScore) { this.nScore = nScore; }
}
