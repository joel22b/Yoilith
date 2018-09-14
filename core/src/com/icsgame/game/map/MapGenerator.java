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
        generateEdgeWalls(nX, nY, nW, nH, nTileSize, txtTiles[2]);
        generateWallNodes(nX, nY, nW, nH, nTileSize, txtTiles[2]);

        // Return the map
        return tiles;
    }

    private void generateBourdary(int nX, int nY, int nW, int nH, int nTileSize, Texture txtBoundary){
        for (int x = 0; x < nW; x++){
            if(x == 0 || x == nW-1){
                for(int y = 0; y < nH; y++){
                    tiles[x][y] = new Tile(map, 0, txtBoundary, nX+(nTileSize*x), nY+(nTileSize*y), nTileSize);
                }
            } else {
                tiles[x][0] = new Tile(map, 0, txtBoundary, nX+(nTileSize*x), nY+(nTileSize*0), nTileSize);
                tiles[x][nH-1] = new Tile(map, 0, txtBoundary, nX+(nTileSize*x), nY+(nTileSize*(nH-1)), nTileSize);
            }
        }
    }

    private void generateEdgeWalls(int nX, int nY, int nW, int nH, int nTileSize, Texture txtWall){
        for (int x = 1; x < (nW-1); x++){
            tiles[x][1] = new Tile(map, 2, txtWall, nX+(x*nTileSize), nY+(1*nTileSize), nTileSize);
            tiles[x][nH-2] = new Tile(map, 2, txtWall, nX+(x*nTileSize), nY+((nH-2)*nTileSize), nTileSize);
            if(x == 1 || x == nW-2){
                for (int y = 1; y < nH-1; y++){
                    tiles[x][y] = new Tile(map, 2, txtWall, nX+(x*nTileSize), nY+(y*nTileSize), nTileSize);
                }
            }
        }
    }

    private void generateWallNodes(int nX, int nY, int nW, int nH, int nTileSize, Texture txtWall){
        
    }
}
