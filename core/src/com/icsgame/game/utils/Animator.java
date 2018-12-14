package com.icsgame.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Animator {

    Animation[] animation;
    float fElapsedTime;

    Vector2 vPos; // Position
    Vector2 vSize; // Size

    public Animator(Texture txt, int nAnimations, int nFrames) {
        animation = new Animation[nAnimations];
        vPos = new Vector2();
        vSize = new Vector2();

        // Load animations
        TextureRegion[][] artxtRegion = TextureRegion.split(txt, txt.getWidth() / nFrames,
                txt.getHeight() / nAnimations);

        TextureRegion[] txtRegionTemp;
        for (int i = 0; i < nAnimations; i++) {
            txtRegionTemp = new TextureRegion[nFrames];
            for (int j = 0; j < nFrames; j++) {
                txtRegionTemp[j] = artxtRegion[i][j];
            }
            animation[i] = new Animation(1f / 3f, txtRegionTemp);
        }
    }

    public void setPosition(float fX, float fY) {
        vPos.set(fX, fY);
    }

    public void setSize(float fW, float fH) {
        vSize.set(fW, fH);
    }

    public void draw(SpriteBatch batch, int nAnimation, boolean bAnimate) {
        if (bAnimate) {
            // Get the time passed
            fElapsedTime += Gdx.graphics.getDeltaTime();
        }

        // Draw the correct animation
        batch.begin();
        batch.draw((TextureRegion) animation[nAnimation].getKeyFrame(fElapsedTime, true), vPos.x, vPos.y,
                vSize.x, vSize.y);
        batch.end();
    }
}
