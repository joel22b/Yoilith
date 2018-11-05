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

    float x, y;
    int h, nValue, nMax, nScale;
    boolean bFont;
    Texture txtBlack, txtBack, txtFront;
    BitmapFont fontBlack;

    public Bar(Texture txtBack, Texture txtFront, float x, float y, int h, int nValue, int nMax, int nScale, boolean bFont) {
        this.txtBlack = new Texture("extra/black.png");
        this.txtBack = txtBack;
        this.txtFront = txtFront;
        this.x = x;
        this.y = y;
        this.h = h;
        this.nValue = nValue;
        this.nMax = nMax;
        this.nScale = nScale;
        this.bFont = bFont;

        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);
    }

    public Bar(String sBack, String sFront, float x, float y, int h, int nValue, int nMax, int nScale, boolean bFont) {
        this.txtBlack = new Texture("extra/black.png");
        this.txtBack = new Texture(sBack);
        this.txtFront = new Texture(sFront);
        this.x = x;
        this.y = y;
        this.h = h;
        this.nValue = nValue;
        this.nMax = nMax;
        this.nScale = nScale;
        this.bFont = bFont;

        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);
    }

    public void render(SpriteBatch batch) {
        // Background
        batch.draw(txtBlack, x, y, nMax*nScale, h);

        // Bar
        batch.draw(txtBack, x+2, y+2, (nMax*nScale)-4, h-4);
        batch.draw(txtFront, x+2, y+2, (nValue*nScale)-4, h-4);

        // Text
        if(bFont) {
            fontBlack.draw(batch, String.valueOf(nValue), x + ((nMax * nScale) / 2 - 23), y + h - 10);
        }
    }

    public void update(float x, float y, int nValue, int nMax) {
        this.x = x;
        this.y = y;
        this.nValue = nValue;
        this.nMax = nMax;
    }
}
