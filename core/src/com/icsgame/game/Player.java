package com.icsgame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.screens.ScrGame;

public class Player {

    ScrGame game;

    // Player Info
    Texture txtPlayer;
    Rectangle rect;
    Vector2 vel;

    public Player(ScrGame game, Texture txtPlayer, Rectangle rect, Vector2 vel){
        this.game = game;
        this.txtPlayer = txtPlayer;
        this.rect = rect;
        this.vel = vel;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtPlayer, getX(), getY(), getW(), getH());
        batch.end();
    }

    public void update(){
        // Slowing Down
        vel.set(vel.x*0.9f, vel.y*0.9f);

        // Update Position
        setX(getX()+vel.x);
        setY(getY()+vel.y);
    }

    public float getX(){ return rect.x; }

    public float getY(){ return rect.y; }

    public float getW(){ return rect.width; }

    public float getH(){ return rect.height; }

    public void setX(float x){ rect.setX(x); }

    public void setY(float y){ rect.setY(y); }

    public void setW(float w){ rect.setWidth(w); }

    public void setH(float h){ rect.setHeight(h); }

    public void addVel(Vector2 velAdd){
        vel.add(velAdd);
    }

    public void addVel(float vX, float vY){
        vel.add(vX, vY);
    }

    public Rectangle getRect() { return rect; }

    public Vector2 getVel() { return vel; }

    public Vector2 getPosition() { return new Vector2(rect.x, rect.y); }
}
