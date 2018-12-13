package com.icsgame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/* ======================== Button ================================
Extends a Sprite
Changes texture based on if you can click and if the mouse is over it

To Use:
Create a Button (give everything to the constructor)
Call the update function every frame
Check if justClicked(); returns true (true means the button is clicked)
================================================================== */

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

    public boolean isMousedOver(OrthographicCamera camera){ // Checks if the mouse is over the button, not whether the mouse was clicked with camera support
        Vector3 vMouse = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(rect.contains(vMouse.x, vMouse.y)){
            return true;
        }
        return false;
    }

    public boolean justClicked(OrthographicCamera camera){ // Checks if the button was just clicked with camera support
        if(bCanClick){
            if(Gdx.input.justTouched()){
                if(isMousedOver(camera)){
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

    @Override
    public float getX() { return rect.getX(); }

    @Override
    public float getY() { return rect.getY(); }

    public float getW() { return rect.getWidth(); }

    public float getH() { return rect.getHeight(); }

    @Override
    public void setX(float x) {
        rect.setX(x);
        super.setX(x);
    }

    @Override
    public void setY(float y) {
        rect.setY(y);
        super.setY(y);
    }

    public boolean getCanClick() { return bCanClick; }

    public void setCanClick(boolean canClick) { bCanClick = canClick; }
}
