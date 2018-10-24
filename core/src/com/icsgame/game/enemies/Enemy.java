package com.icsgame.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy {

    protected Rectangle rect;
    protected Texture txt;
    protected Vector2 vel;

    public Enemy(Texture txt, int w, int h) {
        this.txt = txt;
        rect = new Rectangle(0, 0, w, h);

        spawnController();
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(txt, rect.x, rect.y, rect.width, rect.height);
        batch.end();
    }

    public boolean update() {
        aiController();

        // Update position
        setX(getX()+vel.x);
        setY(getY()+vel.y);

        return false;
    }

    protected void aiController() {

    }

    protected void spawnController() {

    }

    public void setX(float x) {
        rect.setX(x);
    }

    public void setY(float y) {
        rect.setY(y);
    }

    public float getX() { return rect.getX(); }

    public float getY() { return rect.getY(); }

    public Rectangle getRect() { return rect; }
}
