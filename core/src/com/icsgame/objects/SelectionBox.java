package com.icsgame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* ======================== SelectionBox ================================
Creates a selection box so the player can cycle through options and select one
txtImages are the images it will show
arsReturn is the string it will return
====================================================================== */

public class SelectionBox {

    int nX, nY, nW, nH, nIndex = 0;
    Texture txtOutline;
    Texture[] txtImages;
    String sTitle, txtButtonLeft, txtButtonRight;
    String[] arsReturn;
    Button btnLeft, btnRight;
    BitmapFont fontRed, fontBlack;

    public SelectionBox(int nX, int nY, int nW, int nH, Texture txtOutline, String txtButtonLeft, String txtButtonRight,
                        Texture[] txtImages, String sTitle, String[] arsReturn){
        this.nX = nX;
        this.nY = nY;
        this.nW = nW;
        this.nH = nH;
        this.txtOutline = txtOutline;
        this.txtButtonLeft = txtButtonLeft;
        this.txtButtonRight = txtButtonRight;
        this.txtImages = txtImages;
        this.sTitle = sTitle;
        this.arsReturn = arsReturn;

        // Create Buttons
        btnLeft = new Button(nX-70, nY+(nH/2)-50, 50, 50, txtButtonLeft, txtButtonLeft, txtButtonLeft);
        btnRight = new Button(nX+nW+20, nY+(nH/2)-50, 50, 50, txtButtonRight, txtButtonRight, txtButtonRight);

        // Fonts
        fontRed = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontRed.setColor(Color.RED);
        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);

    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtOutline, nX, nY, nW, nH);
        batch.draw(txtImages[nIndex], nX+50, nY+100, nW-100, nH-300);
        btnLeft.draw(batch);
        btnRight.draw(batch);
        fontRed.draw(batch, sTitle, nX+40, nY+nH-100);
        fontBlack.draw(batch, arsReturn[nIndex], nX+20, nY+nH-150);
        batch.end();
    }

    public void checkButtons(){
        if(btnLeft.justClicked()){
            if(nIndex <= 0){
                nIndex = txtImages.length-1;
            } else {
                nIndex--;
            }
        }
        if(btnRight.justClicked()){
            if(nIndex >= txtImages.length-1){
                nIndex = 0;
            } else {
                nIndex++;
            }
        }
    }

    public String getReturn() { return arsReturn[nIndex]; }

    public void setIndex(int nIndex) { this.nIndex = nIndex; }
}
