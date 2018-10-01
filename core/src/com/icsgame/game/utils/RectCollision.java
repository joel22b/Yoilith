package com.icsgame.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectCollision {

    public RectCollision(){}

    public boolean isColliding(Rectangle rectA, Rectangle rectB){
        return rectA.overlaps(rectB);
    }

    public boolean collisionResponseSimple(Rectangle rectA, Rectangle rectB, Vector2 velA) { // A Simple collision detection method for now (has 1 static object)
        Rectangle rectTemp = new Rectangle();
        rectTemp.set(rectA);

        // Checks X
        rectTemp.setX(rectTemp.getX()-velA.x);
        if(!isColliding(rectTemp, rectB)){
            rectA.setX(rectA.getX()-velA.x);
            return true;
        }
        rectTemp.set(rectA);

        // Checks Y
        rectTemp.setY(rectTemp.getY()-velA.y);
        if(!isColliding(rectTemp, rectB)){
            rectA.setY(rectA.getY()-velA.y);
            return true;
        }
        rectTemp.set(rectA);

        // Checks Both
        rectTemp.setX(rectTemp.getX()-velA.x);
        rectTemp.setY(rectTemp.getY()-velA.y);
        if(!isColliding(rectTemp, rectB)){
            rectA.setX(rectA.getX()-velA.x);
            rectA.setY(rectA.getY()-velA.y);
            return true;
        }

        return false;
    }

    public boolean collisionResponse(Rectangle rectA, Rectangle rectB, Vector2 velA) { // Collision between static object and moving object
        Vector2 tempVel = new Vector2();
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
            tempVel.set(velA).nor();
            rectA.setX(rectA.getX()+(nXOverlap*tempVel.x));
            rectA.setY(rectA.getY()+(nYOverlap*tempVel.y));
            return true;
        } else {
            return false;
        }
    }
}
