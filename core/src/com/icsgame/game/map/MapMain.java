package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.icsgame.game.GameMain;

public class MapMain {

    GameMain game;
    int nX, nY, nW, nH, nWTile, nHTile;

    // Map Assets
    Tile[][] tiles;

    public MapMain(GameMain _game){
        game = _game;
    }

    public void createMap(int _nX, int _nY, int _nW, int _nH, int _nWTile, int _nHTile){
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
        nWTile = _nWTile;
        nHTile = _nHTile;

        tiles = new Tile[nW][nH];
        genMap();
    }

    public void render(){
        // Render Tiles
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[x].length; y++){
                tiles[x][y].render();
            }
        }
    }

    private void genMap(){
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new Tile(this, new Texture("Blank.png"), nX+(nWTile*x), nY+(nHTile*y), nWTile, nHTile);
            }
        }
    }
}
