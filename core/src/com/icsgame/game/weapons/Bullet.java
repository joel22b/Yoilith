package com.icsgame.game.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite {

    Vector2 vel;
    Rectangle rect;
    float fDamage;

    public Bullet(Texture txtBullet, Rectangle rect, Vector2 vel, float fDamage){
        super(txtBullet, (int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        this.rect = rect;
        this.vel = vel;
        this.fDamage = fDamage;
    }

    public void update(){
        setX(super.getX()+vel.x);
        setY(super.getY()+vel.y);
    }

    public void render(SpriteBatch batch){
        batch.begin();
        super.draw(batch);
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
