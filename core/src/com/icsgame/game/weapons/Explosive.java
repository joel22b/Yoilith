package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Explosive {

    Texture txtExplosive;
    Rectangle rect;
    Vector2 vel;
    float fSpeed, fDamage, fRange;
    int nTime;

    public Explosive(Texture txtExplosive, Rectangle rect, Vector2 vel, float fSpeed, float fDamage, float fRange, int nTime){
        this.txtExplosive = txtExplosive;
        this.rect = rect;
        this.vel = vel;
        this.fSpeed = fSpeed;
        this.fDamage = fDamage;
        this.fRange = fRange;
        this.nTime = nTime;
    }

    public boolean update(){
        // Move
        rect.setPosition(rect.getX()+(vel.x*fSpeed), rect.getY()+(vel.y*fSpeed));

        // Slow Down
        vel.set(vel.x*0.1f, vel.y*0.1f);

        if(nTime <= 0){
            return true;
        }
        nTime--;
        return false;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtExplosive, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        batch.end();
    }
}
