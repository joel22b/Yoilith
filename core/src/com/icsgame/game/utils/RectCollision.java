package com.icsgame.game.utils;

import com.badlogic.gdx.math.Rectangle;

public class RectCollision {

    public RectCollision(){}

    public boolean isColliding(Rectangle rectA, Rectangle rectB){
        return rectA.overlaps(rectB);
    }
}
