package com.icsgame.objects;

import com.badlogic.gdx.graphics.Texture;

public class SelectionBox {

    int nX, nY, nW, nH;
    Texture txtOutline, txtButtonLeft, txtButtonRight;
    Texture[] txtImages;
    String sTitle;

    public SelectionBox(int nX, int nY, int nW, int nH, Texture txtOutline, Texture txtButtonLeft, Texture txtButtonRight, Texture[] txtImages, String sTitle){
        this.nX = nX;
        this.nY = nY;
        this.nW = nW;
        this.nH = nH;
        this.txtOutline = txtOutline;
        this.txtButtonLeft = txtButtonLeft;
        this.txtButtonRight = txtButtonRight;
        this.txtImages = txtImages;
        this.sTitle = sTitle;
    }
}
