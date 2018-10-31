package com.icsgame.game.weapons.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/* =========================== Bullet =============================
Extends Projectile
The bullet that flies across the map
Contains all the needed information for the bullet
================================================================ */

public class Bullet extends Projectile {

    int nDist;

    public Bullet(Texture txt, Rectangle rect, Vector2 vVel, int nDamage, float fSpeed, int nDist){
        super(txt, rect, vVel, nDamage, fSpeed);

        this.nDist = nDist;

        setX(rect.getX());
        setY(rect.getY());
    }

    @Override
    protected boolean shouldKill(){
        nDist--;
        if(nDist <= 0){
            return true;
        }
        return false;
    }
}
