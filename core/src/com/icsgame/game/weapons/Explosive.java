package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Explosive extends Sprite {

    Texture txtExplosive;
    Rectangle rect;
    Vector2 vel;
    float fSpeed;
    int nTime, nRange, nDamage;

    public Explosive(Texture txtExplosive, Rectangle rect, Vector2 vel, float fSpeed, int nDamage, int nRange, int nTime){
        super(txtExplosive, (int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        this.txtExplosive = txtExplosive;
        this.rect = rect;
        this.vel = vel;
        this.fSpeed = fSpeed;
        this.nDamage = nDamage;
        this.nRange = nRange;
        this.nTime = nTime;
        setRotation(0);
        setRegion(txtExplosive);
        setX(rect.getX());
        setY(rect.getY());
    }

    public boolean update(){
        // Move
        setX(super.getX()+(vel.x*fSpeed));
        setY(super.getY()+(vel.y*fSpeed));

        // Slow Down
        fSpeed -= fSpeed*0.1f;

        if(nTime <= 0){
            return true;
        }
        nTime--;
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

    public int getDamage() { return nDamage; }

    public int getRange() { return nRange; }

    public void setX(float x){
        rect.setX(x);
        super.setX(x);
    }

    public void setY(float y){
        rect.setY(y);
        super.setY(y);
    }
}
