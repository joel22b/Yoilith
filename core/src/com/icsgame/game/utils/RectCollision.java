package com.icsgame.game.utils;

import com.badlogic.gdx.math.Rectangle;

public class RectCollision {

    public RectCollision(){}

    public boolean isColliding(Rectangle rectA, Rectangle rectB){
        return rectA.overlaps(rectB);
    }

    public boolean collisionResponse(Rectangle rectA, Rectangle rectB) {
        if(isColliding(rectA, rectB)){
            float nXOverlap, nYOverlap;
            if(rectA.x > rectB.x){ // Do X
                nXOverlap = (rectB.x+rectB.getWidth())-rectA.x;
            } else {
                nXOverlap = rectB.x-(rectA.x+rectA.getWidth());
            }
            if(rectA.y > rectB.y){ // Do Y
                nYOverlap = (rectB.y+rectB.getHeight())-rectA.y;
            } else {
                nYOverlap = rectB.y-(rectA.y+rectA.getHeight());
            }
            rectA.setX(rectA.getX()+nXOverlap);
            rectA.setY(rectA.getY()+nYOverlap);
            System.out.println("nXOffset="+nXOverlap+" nYOffset="+nYOverlap);
            return true;
        } else {
            return false;
        }
    }
}
