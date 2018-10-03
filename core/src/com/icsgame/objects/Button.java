package com.icsgame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button extends Sprite{

    int nTxt;
    boolean bCanClick = true;
    Texture txtButton, txtButtonPressed, txtButtonGray;
    Rectangle rect;

    public Button(int nX, int nY, int nW, int nH, String _sButtonFile, String _sButtonPressedFile, String _sButtonGrayFile){
        super(new Texture(Gdx.files.internal(_sButtonFile)));

        // Importing Info
        txtButton = new Texture(_sButtonFile);
        txtButtonPressed = new Texture(_sButtonPressedFile);
        txtButtonGray = new Texture(_sButtonGrayFile);
        rect = new Rectangle(nX, nY, nW, nH);

        // Setting Dimenions
        setPosition(nX, nY);
        setFlip(false, false);
        setSize(nW, nH);

        // Setting Texture Info
        setTexture(txtButton);
        nTxt = 0;
    }

    public void update(SpriteBatch batch){ // Will update the buttons image and draw the button
        // Changes texture
        if(!bCanClick){ // If the button can't be clicked
            changeTexture(2);
        } else if(isMousedOver()){ // If the mouse is over the button
            changeTexture(1);
        } else { // Default button image
            changeTexture(0);
        }

        // Render the button
        batch.begin();
        this.draw(batch);
        batch.end();
    }

    public boolean isMousedOver(){ // Checks if the mouse is over the button, not whether the mouse was clicked
        if(rect.contains(Gdx.input.getX(), Gdx.input.getY()*(-1)+Gdx.graphics.getHeight())){
            return true;
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

    private void changeTexture(int _nTxt){ // Changes the Texture of the button
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

    public float getX() { return rect.getX(); }

    public float getY() { return rect.getY(); }

    public boolean getCanClick() { return bCanClick; }

    public void setCanClick(boolean canClick) { bCanClick = canClick; }
}
