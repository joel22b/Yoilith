package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.game.Camera;
import com.icsgame.game.GameMain;

import java.util.Random;

public class MapMain {

    GameMain game;
    int nX, nY, nW, nH, nWTile, nHTile;

    // Map Assets
    Tile[][] tiles;
    Random ranGen;

    public MapMain(GameMain _game){
        game = _game;
        ranGen = new Random();
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

    public void render(SpriteBatch batch){
        // Render Tiles
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles[x].length; y++){
                tiles[x][y].render(batch);
            }
        }
    }

    private void genMap(){
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                if(ranGen.nextInt(2) == 1){
                    tiles[x][y] = new Tile(this, new Texture("themeDesert/tileWallCenter.png"), nX+(nWTile*x), nY+(nHTile*y), nWTile, nHTile);
                } else {
                    tiles[x][y] = new Tile(this, new Texture("themeDesert/tileFloor.png"), nX+(nWTile*x), nY+(nHTile*y), nWTile, nHTile);

                }
            }
        }
    }
}
