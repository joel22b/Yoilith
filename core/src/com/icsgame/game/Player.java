package com.icsgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icsgame.game.weapons.Gun;
import com.icsgame.screens.ScrGame;

public class Player {

    ScrGame game;

    // Player Info
    Gun gun;
    int nHealthMax = 50, nHealth = 30;
    Texture txtPlayer;
    Sprite sprPlayerTop;
    Rectangle rect;
    Vector2 vel, angleHead = new Vector2();
    Vector3 posMouse3D;

    public Player(ScrGame game, Texture txtPlayer, Rectangle rect, Vector2 vel){
        this.game = game;
        this.txtPlayer = txtPlayer;
        this.rect = rect;
        this.vel = vel;

        // Create Sprites
        sprPlayerTop = new Sprite(txtPlayer, (int)(getX()+(getW()/2)), (int)(getY()+((getH()/4)*3)), (int)(getW()), (int)(getH()));
        gun = new Gun(game, this);
        gun.loadType(game.getMain().scrSetup.getSbGunReturn());
    }

    public void render(SpriteBatch batch){
        batch.begin();

        // Player Bottom
        batch.draw(txtPlayer, getX(), getY(), getW(), getH());

        // Player Top
        sprPlayerTop.draw(batch);

        batch.end();
    }

    public void update(){
        // Update Gun
        gun.update();

        // Slowing Down
        vel.set(vel.x*0.9f, vel.y*0.9f);

        // Update Position
        setX(getX()+vel.x);
        setY(getY()+vel.y);

        // Rotate Top
        posMouse3D = game.getCamera().unProject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        angleHead.set(posMouse3D.x-(sprPlayerTop.getX()+(sprPlayerTop.getWidth()/2)), posMouse3D.y-(sprPlayerTop.getY()+(sprPlayerTop.getHeight()/2)));
        sprPlayerTop.setRotation(angleHead.angle());
    }

    public void setupHealth(int nHealth, int nHealthMax){
        this.nHealth = nHealth;
        this.nHealthMax = nHealthMax;
    }

    public void kill(){
        gun = null;
        sprPlayerTop = null;
        rect = null;
        vel = null;
        angleHead = null;
    }

    public float getX(){ return rect.x; }

    public float getY(){ return rect.y; }

    public float getW(){ return rect.width; }

    public float getH(){ return rect.height; }

    public void setX(float x){
        rect.setX(x);
        sprPlayerTop.setCenterX(x+(getW()/2));
    }

    public void setY(float y){
        rect.setY(y);
        sprPlayerTop.setCenterY(y+((getH()/4)*3));
    }

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

    public int getHealth() { return nHealth; }

    public int getHealthMax() { return nHealthMax; }

    public Gun getGun() { return gun; }

    public Vector2 getAngleHead() { return angleHead; }

    public float getHeadX() { return sprPlayerTop.getX(); }

    public float getHeadY() { return sprPlayerTop.getY(); }

    public float getHeadSize() { return sprPlayerTop.getWidth(); }
}
