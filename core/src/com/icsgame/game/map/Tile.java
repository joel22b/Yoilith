package com.icsgame.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tile {

    MapMain map;
    Texture txtTile;
    int nType, nHealth;
    Rectangle rect = new Rectangle();

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
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtTile, getX(), getY(), getW(), getH());
        batch.end();
    }

    public void updateType(int _nType, Texture[] txtTiles){
        nType = _nType;
        txtTile = txtTiles[nType];
    }

    public int getX(){ return (int)rect.x; }

    public int getY(){ return (int)rect.y; }

    public int getW(){ return (int)rect.width; }

    public int getH(){ return (int)rect.height; }

    public Rectangle getRect(){ return rect; }

    public  int getType() { return nType; }
}
