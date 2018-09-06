package com.icsgame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button extends Sprite{
    public int nX, nY, nW, nH, nTxt;
    public boolean bCanClick = true;
    Texture txtButton, txtButtonPressed, txtButtonGray;
    public Button(int _nX, int _nY, int _nW, int _nH, String _sButtonFile, String _sButtonPressedFile, String _sButtonGrayFile){
        super(new Texture(Gdx.files.internal(_sButtonFile)));

        // Importing Info
        txtButton = new Texture(_sButtonFile);
        txtButtonPressed = new Texture(_sButtonPressedFile);
        txtButtonGray = new Texture(_sButtonGrayFile);
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;

        // Setting Dimenions
        setPosition(nX, nY);
        setFlip(false, false);
        setSize(nW, nH);

        // Setting Texture Info
        setTexture(txtButton);
        nTxt = 0;
    }

    public boolean isMousedOver(){ // Checks if the mouse is over the button, not whether the mouse was clicked
        if(Gdx.input.getX() > nX && Gdx.input.getX() < nX + nW){
            if(Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() > nY && Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() < nY + nH){
                return true;
            }
        }
        return false;
    }

    public boolean justClicked(){ // Checks if the button was just clicked
        if(bCanClick){
            if(Gdx.input.justTouched()){
                if(isMousedOver()){
                    return true;
                }
            }
        }
        return false;
    }

    public void changeTexture(int _nTxt){ // Changes the Texture of the button
        if(nTxt != _nTxt){
            switch(_nTxt){
                case 0:
                    // Regular Texture
                    setTexture(txtButton);
                    nTxt = 0;
                    break;
                case 1:
                    // Pressed Button
                    setTexture(txtButtonPressed);
                    nTxt = 1;
                    break;
                case 2:
                    // Grayed out Button
                    setTexture(txtButtonGray);
                default:
                    break;
            }
        }
    }
}
