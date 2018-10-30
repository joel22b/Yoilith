package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/* =========================== Bullet =============================
The bullet that flies across the map
Contains all the needed information for the bullet
================================================================ */

public class Bullet extends Sprite {

    Vector2 vel = new Vector2();
    Rectangle rect;
    int nDamage, nDist;
    float fSpeed;

    public Bullet(Texture txtBullet, Rectangle rect, Vector2 vel, int nDamage, int nSpray, float fSpeed, int nDist){
        super(txtBullet, (int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        this.rect = rect;
        this.vel.set(vel);
        this.nDamage = nDamage;
        setX(rect.getX());
        setY(rect.getY());
        setRegion(txtBullet);
        this.fSpeed = fSpeed;
        setRotation(this.vel.angle()-90+nSpray);
        this.nDist = nDist;
    }

    public boolean update(){
        setX(super.getX()+(vel.x*fSpeed));
        setY(super.getY()+(vel.y*fSpeed));

        nDist--;
        if(nDist <= 0){
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        draw(batch);
        batch.end();
    }

    public void dispose(){
        getTexture().dispose();
    }

    public Rectangle getRect() { return rect; }

    public void setX(float x){
        rect.setX(x);
        super.setX(x);
    }

    public void setY(float y){
        rect.setY(y);
        super.setY(y);
    }
}
