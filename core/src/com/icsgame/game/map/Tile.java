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
    ========================================== */

    public Tile(MapMain _map, int _nType, Texture _txtTile, int _nX, int _nY, int _nW, int _nH){
        map = _map;
        nType = _nType;
        txtTile = _txtTile;
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txtTile, nX, nY, nW, nH);
        batch.end();
    }

    public void hitDetection(int _nX, int _nY, int _nW, int _nH){
        switch (nType){
            case 0:
                hitDetectionUp(_nX, _nY);
                hitDetectionDown(_nX, _nY, _nH);
                hitDetectionLeft(_nX, _nY, _nW);
                hitDetectionRight(_nX, _nY);
                break;
            case 3:
                hitDetectionUp(_nX, _nY);
                break;
            case 4:
                hitDetectionRight(_nX, _nY);
                break;
            case 5:
                hitDetectionDown(_nX, _nY, _nH);
                break;
            case 6:
                hitDetectionLeft(_nX, _nY, _nW);
                break;
            case 7:
                hitDetectionUp(_nX, _nY);
                hitDetectionLeft(_nX, _nY, _nW);
                break;
            case 8:
                hitDetectionUp(_nX, _nY);
                hitDetectionRight(_nX, _nY);
                break;
            case 9:
                hitDetectionDown(_nX, _nY, _nH);
                hitDetectionRight(_nX, _nY);
                break;
            case 10:
                hitDetectionDown(_nX, _nY, _nH);
                hitDetectionLeft(_nX, _nY, _nW);
                break;
            default:
                break;
        }
    }

    private boolean hitDetectionUp(int _nX, int _nY){
        if(_nY < nY+nH){
            if(_nX > nX && _nX < nX + nW) {
                return true;
            }
        }
        return false;
    }

    private boolean hitDetectionDown(int _nX, int _nY, int _nH){
        if(_nY+_nH > nY){
            if(_nX > nX && _nX < nX + nW) {
                return true;
            }
        }
        return false;
    }

    private boolean hitDetectionLeft(int _nX, int _nY, int _nW){
        if(_nX+_nW > nX){
            if(_nY > nY && _nY < nY + nH) {
                return true;
            }
        }
        return false;
    }

    private boolean hitDetectionRight(int _nX, int _nY){
        if(_nX < nX+nW){
            if(_nY > nY && _nY < nY + nH) {
                return true;
            }
        }
        return false;
    }
}
