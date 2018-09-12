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
    Texture[] txtTiles;

    public MapMain(GameMain _game){
        game = _game;
        ranGen = new Random();
    }

    public void createMap(int _nX, int _nY, int _nW, int _nH, int _nWTile, int _nHTile, String sTheme){
        nX = _nX;
        nY = _nY;
        nW = _nW;
        nH = _nH;
        nWTile = _nWTile;
        nHTile = _nHTile;

        loadTileTextures(sTheme);
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
                    tiles[x][y] = new Tile(this, 2, txtTiles[2], nX+(nWTile*x), nY+(nHTile*y), nWTile, nHTile);
                } else {
                    tiles[x][y] = new Tile(this, 1, txtTiles[1], nX+(nWTile*x), nY+(nHTile*y), nWTile, nHTile);

                }
            }
        }
    }

    private void loadTileTextures(String sTheme){
        txtTiles = new Texture[11];

        // Load Textures
        txtTiles[0] = new Texture("theme"+sTheme+"/tileBoundary.png");
        txtTiles[1] = new Texture("theme"+sTheme+"/tileFloor.png");
        txtTiles[2] = new Texture("theme"+sTheme+"/tileWallCenter.png");
        txtTiles[3] = new Texture("theme"+sTheme+"/tileWallTop.png");
        txtTiles[4] = new Texture("theme"+sTheme+"/tileWallRight.png");
        txtTiles[5] = new Texture("theme"+sTheme+"/tileWallBottom.png");
        txtTiles[6] = new Texture("theme"+sTheme+"/tileWallLeft.png");
        txtTiles[7] = new Texture("theme"+sTheme+"/tileWallTopLeft.png");
        txtTiles[8] = new Texture("theme"+sTheme+"/tileWallTopRight.png");
        txtTiles[9] = new Texture("theme"+sTheme+"/tileWallBottomRight.png");
        txtTiles[10] = new Texture("theme"+sTheme+"/tileWallBottomLeft.png");
    }
}
