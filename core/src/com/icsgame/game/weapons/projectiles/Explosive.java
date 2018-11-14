package com.icsgame.game.weapons.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/* =========================== Explosive =============================
Extends Projectile
The explosives that go on the map
Contains all information needed for the explosive
update(); returns true when nTime = 0
=================================================================== */

public class Explosive extends Projectile {

    int nTime, nRange;

    public Explosive(Texture txt, Rectangle rect, Vector2 vVel, float fSpeed, int nDamage, int nRange, int nTime, int nTeam){
        super(txt, rect, vVel, nDamage, fSpeed, nTeam);

        this.nRange = nRange;
        this.nTime = nTime;

        setRotation(0);
        setX(rect.getX());
        setY(rect.getY());
    }

    @Override
    public boolean update(){
        fSpeed -= fSpeed*0.1f;

        return super.update();
    }

    @Override
    protected boolean shouldKill() {
        if(nTime <= 0){
            return true;
        }
        nTime--;
        return false;
    }

    public int getDamage() { return nDamage; }

    public int getRange() { return nRange; }
}
