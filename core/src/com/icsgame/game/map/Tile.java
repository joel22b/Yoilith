package com.icsgame.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

/* ======================== Tile ================================
The Tiles on the map
The map is map up of these
================================================================== */

public class Tile {

    MapMain map;
    Texture txtTile, txtDamage = null;
    int nType, nHealth, healthMax;
    Rectangle rect = new Rectangle();
    Polygon polygon;

    /* =========== Type Meaning =================
    0 = boundary wall
    1 = floor
    2 = wall no edge
    3 = wall top edge
    4 = wall right edge
    5 = wall bottom edge
    6 = wall left edge
    7 = wall top left edge
    8 = wall top right edge
    9 = wall bottom right edge
    10 = wall bottom left  edge
    11 = wall top cap
    12 = wall bottom cap
    13 = wall left cap
    14 = wall right cap
    15 = wall vertical tunnel
    16 = wall horizontal tunnel
    ========================================== */

    public Tile(MapMain _map, int _nType, Texture _txtTile, int _nX, int _nY, int _nSize, int nHealth){
        map = _map;
        nType = _nType;
        txtTile = _txtTile;
        rect.x = _nX;
        rect.y = _nY;
        rect.width = _nSize;
        rect.height = _nSize;
        this.nHealth = nHealth;
        healthMax = nHealth;

        polygon = new Polygon(new float[]{getX(), getY(), getX()+getW(), getY(), getX()+getW(), getY()+getH(), getX(), getY()+getH()});
        polygon.setPosition(getX(), getY());
        polygon.setOrigin(getX()+(getW()/2), getY()+(getH()/2));
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtTile, getX(), getY(), getW(), getH());

        if(txtDamage != null) {
            batch.draw(txtDamage, getX(), getY(), getW(), getH());
        }
        batch.end();
    }

    public void updateType(int _nType, Texture[] txtTiles){
        nType = _nType;
        txtTile = txtTiles[nType];
    }

    public void checkHealth(Texture[] txts, Texture[] txtDamage) {
        if(nHealth <= 0) {
            nType = 1;
            txtTile = txts[1];
            this.txtDamage = null;
        } else {
            for (int i = 0; i < txtDamage.length; i++) {
                if(nHealth >= (healthMax/txtDamage.length)*(txtDamage.length-i-1)) {
                    this.txtDamage = txtDamage[i];
                    break;
                }
            }
        }
    }

    public int getX(){ return (int)rect.x; }

    public int getY(){ return (int)rect.y; }

    public int getW(){ return (int)rect.width; }

    public int getH(){ return (int)rect.height; }

    public Rectangle getRect(){ return rect; }

    public Polygon getPolygon() { return polygon; }

    public  int getType() { return nType; }

    public void decreaseHealth(int decrease) { nHealth -= decrease; }

    public void setTexture(Texture txt) {
        txtTile = txt;
    }
}
