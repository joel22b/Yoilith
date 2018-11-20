package com.icsgame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* ==================== Bar ======================
Draws a bar to display information
================================================ */

public class Bar {

    float x, y, fScale;
    int h, nValue, nMax;
    boolean bFont;
    Texture txtBlack, txtBack, txtFront;
    BitmapFont fontBlack;

    public Bar(Texture txtBack, Texture txtFront, float x, float y, int h, int nValue, int nMax, float fScale, boolean bFont) {
        this.txtBlack = new Texture("extra/black.png");
        this.txtBack = txtBack;
        this.txtFront = txtFront;
        this.x = x;
        this.y = y;
        this.h = h;
        this.nValue = nValue;
        this.nMax = nMax;
        this.fScale = fScale;
        this.bFont = bFont;

        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);
    }

    public Bar(String sBack, String sFront, float x, float y, int h, int nValue, int nMax, float fScale, boolean bFont) {
        this.txtBlack = new Texture("extra/black.png");
        this.txtBack = new Texture(sBack);
        this.txtFront = new Texture(sFront);
        this.x = x;
        this.y = y;
        this.h = h;
        this.nValue = nValue;
        this.nMax = nMax;
        this.fScale = fScale;
        this.bFont = bFont;

        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);
    }

    public void render(SpriteBatch batch) {
        // Background
        batch.draw(txtBlack, x, y, nMax*fScale, h);

        // Bar
        batch.draw(txtBack, x+2, y+2, (nMax*fScale)-4, h-4);
        batch.draw(txtFront, x+2, y+2, (nValue*fScale)-4, h-4);

        // Text
        if(bFont) {
            fontBlack.draw(batch, String.valueOf(nValue), x + ((nMax * fScale) / 2 - 23), y + h - 10);
        }
    }

    public void update(float x, float y, int nValue, int nMax) {
        this.x = x;
        this.y = y;
        this.nValue = nValue;
        this.nMax = nMax;
    }
}
