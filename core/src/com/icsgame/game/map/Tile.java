package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {

    MapMain map;
    SpriteBatch batch = new SpriteBatch();
    Texture txtTile;
    int nX, nY, nW, nH;

    public Tile(MapMain _map, Texture _txtTile, int _nX, int _nY, int _nW, int _nH){
        map = _map;
        txtTile = _txtTile;
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
    }

    public void render(){
        batch.begin();
        batch.draw(txtTile, nX, nY, nW, nH);
        batch.end();
    }
}
