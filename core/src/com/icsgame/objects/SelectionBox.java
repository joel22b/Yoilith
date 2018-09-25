package com.icsgame.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SelectionBox {

    int nX, nY, nW, nH, nIndex = 0;
    Texture txtOutline;
    Texture[] txtImages;
    String sTitle, txtButtonLeft, txtButtonRight;
    String[] arsReturn;
    Button btnLeft, btnRight;

    public SelectionBox(int nX, int nY, int nW, int nH, Texture txtOutline, String txtButtonLeft, String txtButtonRight, Texture[] txtImages, String sTitle, String[] arsReturn){
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
        btnLeft = new Button(nX-150, nY+(nH/2)-50, 100, 100, txtButtonLeft, txtButtonLeft, txtButtonLeft);
        btnRight = new Button(nX+50, nY+(nH/2)-50, 100, 100, txtButtonRight, txtButtonRight, txtButtonRight);
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtOutline, nX, nY, nW, nH);
        batch.draw(txtImages[nIndex], nX+50, nY+100, nW-100, nH-300);
        batch.end();

        btnLeft.draw(batch);
        btnRight.draw(batch);
    }

    public void checkButtons(){

    }
}
