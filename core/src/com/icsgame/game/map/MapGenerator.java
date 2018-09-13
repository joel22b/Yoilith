package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class MapGenerator {

    MapMain map;
    Tile[][] tiles;

    public MapGenerator(MapMain _map){
        map = _map;
    }

    public Tile[][] generateMap(Texture[] txtTiles, int nX, int nY, int nW, int nH, int nTileSize){
        tiles = new Tile[nW][nH];

        // Follow the steps to make the map
        generateBourdary(nX, nY, nW, nH, nTileSize, txtTiles[0]);

        // Return the map
        return tiles;
    }

    private void generateBourdary(int nX, int nY, int nW, int nH, int nTileSize, Texture txtBoundary){
        for (int x = 0; x < nW; x++){
            if(x == 0 || x == nW-1){
                for(int y = 0; y < nH; y++){
                    tiles[x][y] = new Tile(map, 0, txtBoundary, nX+(nTileSize*x), nY+(nTileSize*y), nTileSize, nTileSize);
                }
            } else {
                tiles[x][0] = new Tile(map, 0, txtBoundary, nX+(nTileSize*x), nY+(nTileSize*0), nTileSize, nTileSize);
                tiles[x][nH-1] = new Tile(map, 0, txtBoundary, nX+(nTileSize*x), nY+(nTileSize*(nH-1)), nTileSize, nTileSize);
            }
        }
    }
}
