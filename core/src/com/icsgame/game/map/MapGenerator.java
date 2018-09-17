package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    MapMain map;
    Tile[][] tiles;
    Random ranGen = new Random();

    public MapGenerator(MapMain _map){
        map = _map;
    }

    public Tile[][] generateMap(Texture[] txtTiles, int nX, int nY, int nW, int nH, int nTileSize){
        tiles = new Tile[nW][nH];

        // Follow the steps to make the map
        generateBourdary(nX, nY, nW, nH, nTileSize, txtTiles[0]);
        generateEdgeWalls(nX, nY, nW, nH, nTileSize, txtTiles[2], 30);
        generateWallNodes(nX, nY, nW, nH, nTileSize, txtTiles[2]);
        fillInFloor(nX, nY, nW, nH, nTileSize, txtTiles[1]);

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

    private void generateEdgeWalls(int nX, int nY, int nW, int nH, int nTileSize, Texture txtWall, int nPercentChange){
        for (int i = 1; i < Math.ceil(((nW/3)+(nH/3))/2); i++) {
            for (int x = i; x < (nW - i); x++) {
                if(ranPercent(100-((i-1)*nPercentChange))) {
                    tiles[x][i] = new Tile(map, 2, txtWall, nX + (x * nTileSize), nY + (i * nTileSize), nTileSize);
                }
                if(ranPercent(100-((i-1)*nPercentChange))) {
                    tiles[x][nH - (i + 1)] = new Tile(map, 2, txtWall, nX + (x * nTileSize), nY + ((nH - (i + 1)) * nTileSize), nTileSize);
                }
                if (x == i || x == nW - (i+1)) {
                    for (int y = 1; y < nH - 1; y++) {
                        if(ranPercent(100-((i-1)*nPercentChange))) {
                            tiles[x][y] = new Tile(map, 2, txtWall, nX + (x * nTileSize), nY + (y * nTileSize), nTileSize);
                        }
                    }
                }
            }
        }
    }

    private void generateWallNodes(int nX, int nY, int nW, int nH, int nTileSize, Texture txtWall){
        int ranX, ranY, ranSize, nFailCount;
        int nSizeFromEdge = (int)Math.ceil(((nW/3)+(nH/3))/2);
        int nAreaInnerRect = (int)Math.pow(nSizeFromEdge, 2);
        int nNodeNum = (int)Math.ceil(nAreaInnerRect/8);
        int nNodeSize = (int)Math.ceil(nAreaInnerRect/4);
        for (int i = 0; i < nNodeNum; i++){
            do {
                ranX = ranGen.nextInt(nW - (nSizeFromEdge * 2)) + nSizeFromEdge;
                ranY = ranGen.nextInt(nH - (nSizeFromEdge * 2)) + nSizeFromEdge;
            } while (tiles[ranX][ranY] != null);
            tiles[ranX][ranY] = new Tile(map, 2, txtWall, nX+(ranX*nTileSize), nY+(ranY*nTileSize), nTileSize);
            if(ranPercent(50)) {
                ranSize = nNodeSize + (ranGen.nextInt((int) Math.ceil(nNodeSize / 4)));
            } else {
                ranSize = nNodeSize - (ranGen.nextInt((int) Math.ceil(nNodeSize / 4)));
            }
            nFailCount = 0;
            for (int j = 0; j < ranSize; j++){
                switch (ranGen.nextInt(4)){
                    case 0:
                        if(tiles[ranX+1][ranY] == null){
                            tiles[ranX+1][ranY] = new Tile(map, 2, txtWall, nX+((ranX+1)*nTileSize), nY+((ranY)*nTileSize), nTileSize);
                            ranX++;
                        } else {
                            if(nFailCount < 5) {
                                j--;
                                nFailCount++;
                            }
                        }
                        break;
                    case 1:
                        if(tiles[ranX][ranY+1] == null){
                            tiles[ranX][ranY+1] = new Tile(map, 2, txtWall, nX+((ranX)*nTileSize), nY+((ranY+1)*nTileSize), nTileSize);
                            ranY++;
                        } else {
                            if(nFailCount < 5) {
                                j--;
                                nFailCount++;
                            }
                        }
                        break;
                    case 2:
                        if(tiles[ranX-1][ranY] == null){
                            tiles[ranX-1][ranY] = new Tile(map, 2, txtWall, nX+((ranX-1)*nTileSize), nY+((ranY)*nTileSize), nTileSize);
                            ranX--;
                        } else {
                            if(nFailCount < 5) {
                                j--;
                                nFailCount++;
                            }
                        }
                        break;
                    case 3:
                        if(tiles[ranX][ranY-1] == null){
                            tiles[ranX][ranY-1] = new Tile(map, 2, txtWall, nX+((ranX)*nTileSize), nY+((ranY-1)*nTileSize), nTileSize);
                            ranY--;
                        } else {
                            if(nFailCount < 5) {
                                j--;
                                nFailCount++;
                            }
                        }
                        break;
                    default:
                        System.out.println("ERROR: Invalid number in 'generateWallNodes' in 'mapGenerator' class");
                        break;
                }
            }
        }
    }

    private void fillInFloor(int nX, int nY, int nW, int nH, int nTileSize, Texture txtFloor){
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                if (tiles[x][y] == null) {
                    tiles[x][y] = new Tile(map, 1, txtFloor, nX+(x*nTileSize), nY+(y*nTileSize), nTileSize);
                }
            }
        }
    }

    private void correctTextures(int nW, int nH, Texture[] txtTiles){
        for (int x = 1; x < nW-1; x++){
            for (int y = 1; y < nH-1; y++){

            }
        }
    }

    private boolean ranPercent(int nPercent){
        if(ranGen.nextInt(100) < nPercent) {
            return true;
        } else {
            return false;
        }
    }
}
