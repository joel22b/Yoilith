package com.icsgame.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.game.utils.Camera;

import java.math.BigDecimal;

public class ScoreUI {

    Camera camera;

    int nScore, nCombo, nComboCount;
    float fComboScale;
    boolean bComboSizeChangeUp, bComboSizeChangeDown;

    int nComboTimeLimit, nComboTimeCount;

    BitmapFont font, fontBig, fontBigBack;

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
        nCombo = 1;
        nComboCount = 0;
        fComboScale = 1;
        bComboSizeChangeUp = false;
        bComboSizeChangeDown = false;

        nComboTimeLimit = 90;
        nComboTimeCount = 0;

        font = new BitmapFont(Gdx.files.internal("fontScore.fnt"));
        font.setColor(Color.WHITE);

        fontBig = new BitmapFont(Gdx.files.internal("fontCombo.fnt"));
        fontBig.setColor(Color.WHITE);

        fontBigBack = new BitmapFont(Gdx.files.internal("fontCombo.fnt"));
        fontBigBack.setColor(Color.BLACK);
    }

    public void render(SpriteBatch batch) {
        // Count down until combo runs out
        if (nCombo > 1) {
            if (nComboTimeCount >= nComboTimeLimit) {
                resetCombo();
            } else {
                nComboTimeCount++;
            }
        }

        batch.begin();

        // Render Black Score Background
        sprBackground.setPosition(camera.getX()+(camera.getW()/2)-20-sprBackground.getWidth(),
                camera.getY()+(camera.getH()/2)-20-sprBackground.getHeight());
        sprBackground.draw(batch);

        // Render the score
        font.draw(batch, Integer.toString(nScore), camera.getX()+(camera.getW()/2)-20-sprBackground.getWidth()+40,
                camera.getY()+(camera.getH()/2)-20-sprBackground.getHeight()+60);

        // Render Combo
        // Calculate The Scale
        if (bComboSizeChangeUp) {
            if (nComboCount >= 30) {
                bComboSizeChangeUp = false;
                nComboCount = 0;
                fComboScale = 1;
                fontBig.getData().setScale(fComboScale);
                fontBigBack.getData().setScale(fComboScale);
            } else {
                fComboScale = (float)((-0.5f)*Math.pow((nComboCount-15f)/15f , 2)+1.5f);
                fontBig.getData().setScale(fComboScale);
                fontBigBack.getData().setScale(fComboScale);
                nComboCount++;
            }
        }
        if (bComboSizeChangeDown) {
            if (nComboCount >= (15-1)) {
                bComboSizeChangeDown = false;
                nComboCount = 0;
                fComboScale = 1;
                fontBig.getData().setScale(fComboScale);
                fontBigBack.getData().setScale(fComboScale);
                nCombo = 1;
            } else {
                fComboScale = (float)((-1)*Math.pow(nComboCount/15f , 2)+1f);
                fontBig.getData().setScale(fComboScale);
                fontBigBack.getData().setScale(fComboScale);
                nComboCount++;
            }
        }

        if (nCombo != 1) {
            String sCombo = "x" + nCombo;

            fontBigBack.draw(batch, sCombo, camera.getX() + (camera.getW() / 2) - 20 - 80 - (20 * fComboScale) + 5,
                    camera.getY() + (camera.getH() / 2) - 20 - sprBackground.getHeight() + 80 - (20 * fComboScale) - 5);
            fontBig.draw(batch, sCombo, camera.getX() + (camera.getW() / 2) - 20 - 80 - (20 * fComboScale),
                    camera.getY() + (camera.getH() / 2) - 20 - sprBackground.getHeight() + 80 - (20 * fComboScale));
        }

        batch.end();
    }

    public void addScore(int nAdd) { nScore += (nAdd*nCombo); }

    public void setScore(int nScore) { this.nScore = nScore; }

    public void addCombo(int nAdd) {
        nCombo += nAdd;
        nComboTimeCount = 0;
        startComboSize();
    }

    public void resetCombo() {
        bComboSizeChangeDown = true;
        nComboTimeCount = 0;
    }

    public void startComboSize() { bComboSizeChangeUp = true; }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
