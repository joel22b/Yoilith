package com.icsgame.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {

    MapMain map;
    Texture txtTile;
    int nType, nX, nY, nW, nH;

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

    public Tile(MapMain _map, int _nType, Texture _txtTile, int _nX, int _nY, int _nSize){
        map = _map;
        nType = _nType;
        txtTile = _txtTile;
        nX = _nX;
        nY = _nY;
        nW = _nSize;
        nH = _nSize;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtTile, nX, nY, nW, nH);
        batch.end();
    }

    public boolean isHit(int _nX, int _nY, int _nW, int _nH){
        if(_nX > nX && _nX < nX + nW){
            if(_nY > nY && _nY < nY + nH){
                if(_nX+_nW > nX && _nX+_nW < nX + nW) {
                    if (_nY+_nH > nY && _nY+_nH < nY + nH) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void updateType(int _nType, Texture[] txtTiles){
        nType = _nType;
        txtTile = txtTiles[nType];
    }
}
