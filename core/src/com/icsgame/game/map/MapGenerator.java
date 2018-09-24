package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    MapMain map;
    Random ranGen = new Random();
    int[][][] arbTileTemplate;

    // Map Things
    Tile[][] tiles;
    ArrayList<Item> Items = new ArrayList<>();

    public MapGenerator(MapMain _map){
        map = _map;
        createTileTemplate();
    }

    public void generateMap(Texture[] txtTiles, Texture[] txtDecorations, int nX, int nY, int nW, int nH, int nTileSize){
        tiles = new Tile[nW][nH];

        // Follow the steps to make the map
        generateBourdary(nX, nY, nW, nH, nTileSize, txtTiles[0]);
        generateEdgeWalls(nX, nY, nW, nH, nTileSize, txtTiles[2], 30);
        generateWallNodes(nX, nY, nW, nH, nTileSize, txtTiles[2]);
        fillInFloor(nX, nY, nW, nH, nTileSize, txtTiles[1]);
        correctTextures(nW, nH, txtTiles);
        createItems((int)Math.ceil(Math.pow(Math.ceil(((nW/3)+(nH/3))/2), 2)/8), nX, nY, nW, nH, nTileSize, txtDecorations);
    }

    public Tile[][] getTiles(){
        return tiles;
    }

    public ArrayList<Item> getDecorations(){
        return Items;
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

    private void correctTextures(int nW, int nH, Texture[] txtTiles){ // True means there's a wall
        int[][] arnTiles, arnUpdatedTiles = new int[nW][nH];
        for (int x = 1; x < nW-1; x++){
            for (int y = 1; y < nH-1; y++){
                if(tiles[x][y].nType == 2){
                    arnTiles = new int[3][3];
                    arnTiles[1][1] = 1;
                    for (int bX = 0; bX < 3; bX++){
                        for (int bY = 0; bY < 3; bY++){
                            if(tiles[x+(bX-1)][y+(bY-1)].nType == 2){
                                arnTiles[bX][bY] = 1;
                            } else {
                                arnTiles[bX][bY] = 0;
                            }
                        }
                    }
                    updateTexture(arnTiles, arnUpdatedTiles, x, y);
                } else {
                    arnUpdatedTiles[x][y] = tiles[x][y].nType;
                }
            }
        }
        for (int x = 1; x < nW-1; x++) {
            for (int y = 1; y < nH - 1; y++) {
                tiles[x][y].updateType(arnUpdatedTiles[x][y], txtTiles);
            }
        }
    }

    private void updateTexture(int[][] arnTiles, int[][] arnUpdatedTiles, int x, int y){
        Boolean bTemplate, bFoundType = false;
        for (int i = 0; i < arbTileTemplate.length; i++){
            bTemplate = true;
            if(!bFoundType) {
                for (int bX = 0; bX < 3; bX++) {
                    for (int bY = 0; bY < 3; bY++) {
                        if (bTemplate) {
                            if (arbTileTemplate[i][bX][bY] != arnTiles[bX][bY] && arbTileTemplate[i][bX][bY] != 2) {
                                bTemplate = false;
                            }
                        }
                    }
                }
                if (bTemplate) {
                    arnUpdatedTiles[x][y] = i + 2;
                    bFoundType = true;
                }
            }
        }
        if(!bFoundType){
            arnUpdatedTiles[x][y] = 2;
        }
    }

    private void createItems(int nNumODecoration, int nX, int nY, int nW, int nH, int nTileSize, Texture[] txtDecorations){
        int x, y, w, h;
        for (int i = 0; i < nNumODecoration; i++){
            do{
                x = ranGen.nextInt((nW*nTileSize)-(nTileSize*2));
                y = ranGen.nextInt((nH*nTileSize)-(nTileSize*2));
                w = 50;
                h = 50;
            } while (!canPlace(nX+x+nTileSize, nY+y+nTileSize, w, h));
            Items.add(new Item(map, txtDecorations[ranGen.nextInt(2)], new Vector2(nX+x+nTileSize, nY+y+nTileSize), w, h));
        }
    }

    private boolean ranPercent(int nPercent){
        if(ranGen.nextInt(100) < nPercent) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canPlace(int nX, int nY, int nW, int nH){
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles.length; y++){
                if(tiles[x][y].isHit(nX, nY, nW, nH)){
                    return false;
                }
            }
        }
        return true;
    }

    private void createTileTemplate(){
        arbTileTemplate = new int[16][3][3]; // 0 = floor, 1 = wall, 2 = doesn't matter

        // no edge
        arbTileTemplate[0][0][0] = 2;
        arbTileTemplate[0][0][1] = 1;
        arbTileTemplate[0][0][2] = 2;
        arbTileTemplate[0][1][0] = 1;
        arbTileTemplate[0][1][1] = 1;
        arbTileTemplate[0][1][2] = 1;
        arbTileTemplate[0][2][0] = 2;
        arbTileTemplate[0][2][1] = 1;
        arbTileTemplate[0][2][2] = 2;

        // top edge
        arbTileTemplate[1][0][0] = 2;
        arbTileTemplate[1][0][1] = 1;
        arbTileTemplate[1][0][2] = 2;
        arbTileTemplate[1][1][0] = 1;
        arbTileTemplate[1][1][1] = 1;
        arbTileTemplate[1][1][2] = 0;
        arbTileTemplate[1][2][0] = 2;
        arbTileTemplate[1][2][1] = 1;
        arbTileTemplate[1][2][2] = 2;

        // right edge
        arbTileTemplate[2][0][0] = 2;
        arbTileTemplate[2][0][1] = 1;
        arbTileTemplate[2][0][2] = 2;
        arbTileTemplate[2][1][0] = 1;
        arbTileTemplate[2][1][1] = 1;
        arbTileTemplate[2][1][2] = 1;
        arbTileTemplate[2][2][0] = 2;
        arbTileTemplate[2][2][1] = 0;
        arbTileTemplate[2][2][2] = 2;

        // bottom edge
        arbTileTemplate[3][0][0] = 2;
        arbTileTemplate[3][0][1] = 1;
        arbTileTemplate[3][0][2] = 2;
        arbTileTemplate[3][1][0] = 0;
        arbTileTemplate[3][1][1] = 1;
        arbTileTemplate[3][1][2] = 1;
        arbTileTemplate[3][2][0] = 2;
        arbTileTemplate[3][2][1] = 1;
        arbTileTemplate[3][2][2] = 2;

        // left edge
        arbTileTemplate[4][0][0] = 2;
        arbTileTemplate[4][0][1] = 0;
        arbTileTemplate[4][0][2] = 2;
        arbTileTemplate[4][1][0] = 1;
        arbTileTemplate[4][1][1] = 1;
        arbTileTemplate[4][1][2] = 1;
        arbTileTemplate[4][2][0] = 2;
        arbTileTemplate[4][2][1] = 1;
        arbTileTemplate[4][2][2] = 2;

        // top left edge
        arbTileTemplate[5][0][0] = 2;
        arbTileTemplate[5][0][1] = 0;
        arbTileTemplate[5][0][2] = 2;
        arbTileTemplate[5][1][0] = 1;
        arbTileTemplate[5][1][1] = 1;
        arbTileTemplate[5][1][2] = 0;
        arbTileTemplate[5][2][0] = 2;
        arbTileTemplate[5][2][1] = 1;
        arbTileTemplate[5][2][2] = 2;

        // top right edge
        arbTileTemplate[6][0][0] = 2;
        arbTileTemplate[6][0][1] = 1;
        arbTileTemplate[6][0][2] = 2;
        arbTileTemplate[6][1][0] = 1;
        arbTileTemplate[6][1][1] = 1;
        arbTileTemplate[6][1][2] = 0;
        arbTileTemplate[6][2][0] = 2;
        arbTileTemplate[6][2][1] = 0;
        arbTileTemplate[6][2][2] = 2;

        // bottom right edge
        arbTileTemplate[7][0][0] = 2;
        arbTileTemplate[7][0][1] = 1;
        arbTileTemplate[7][0][2] = 2;
        arbTileTemplate[7][1][0] = 0;
        arbTileTemplate[7][1][1] = 1;
        arbTileTemplate[7][1][2] = 1;
        arbTileTemplate[7][2][0] = 2;
        arbTileTemplate[7][2][1] = 0;
        arbTileTemplate[7][2][2] = 2;

        // bottom left edge
        arbTileTemplate[8][0][0] = 2;
        arbTileTemplate[8][0][1] = 0;
        arbTileTemplate[8][0][2] = 2;
        arbTileTemplate[8][1][0] = 0;
        arbTileTemplate[8][1][1] = 1;
        arbTileTemplate[8][1][2] = 1;
        arbTileTemplate[8][2][0] = 2;
        arbTileTemplate[8][2][1] = 1;
        arbTileTemplate[8][2][2] = 2;

        //  top cap edge
        arbTileTemplate[9][0][0] = 2;
        arbTileTemplate[9][0][1] = 0;
        arbTileTemplate[9][0][2] = 2;
        arbTileTemplate[9][1][0] = 1;
        arbTileTemplate[9][1][1] = 1;
        arbTileTemplate[9][1][2] = 0;
        arbTileTemplate[9][2][0] = 2;
        arbTileTemplate[9][2][1] = 0;
        arbTileTemplate[9][2][2] = 2;

        // bottom cap edge
        arbTileTemplate[10][0][0] = 2;
        arbTileTemplate[10][0][1] = 0;
        arbTileTemplate[10][0][2] = 2;
        arbTileTemplate[10][1][0] = 0;
        arbTileTemplate[10][1][1] = 1;
        arbTileTemplate[10][1][2] = 1;
        arbTileTemplate[10][2][0] = 2;
        arbTileTemplate[10][2][1] = 0;
        arbTileTemplate[10][2][2] = 2;

        // left cap edge
        arbTileTemplate[11][0][0] = 2;
        arbTileTemplate[11][0][1] = 0;
        arbTileTemplate[11][0][2] = 2;
        arbTileTemplate[11][1][0] = 0;
        arbTileTemplate[11][1][1] = 1;
        arbTileTemplate[11][1][2] = 0;
        arbTileTemplate[11][2][0] = 2;
        arbTileTemplate[11][2][1] = 1;
        arbTileTemplate[11][2][2] = 2;

        // right cap edge
        arbTileTemplate[12][0][0] = 2;
        arbTileTemplate[12][0][1] = 1;
        arbTileTemplate[12][0][2] = 2;
        arbTileTemplate[12][1][0] = 0;
        arbTileTemplate[12][1][1] = 1;
        arbTileTemplate[12][1][2] = 0;
        arbTileTemplate[12][2][0] = 2;
        arbTileTemplate[12][2][1] = 0;
        arbTileTemplate[12][2][2] = 2;

        // vertical tunnel edge
        arbTileTemplate[13][0][0] = 2;
        arbTileTemplate[13][0][1] = 0;
        arbTileTemplate[13][0][2] = 2;
        arbTileTemplate[13][1][0] = 1;
        arbTileTemplate[13][1][1] = 1;
        arbTileTemplate[13][1][2] = 1;
        arbTileTemplate[13][2][0] = 2;
        arbTileTemplate[13][2][1] = 0;
        arbTileTemplate[13][2][2] = 2;

        // horizontal tunnel edge
        arbTileTemplate[14][0][0] = 2;
        arbTileTemplate[14][0][1] = 1;
        arbTileTemplate[14][0][2] = 2;
        arbTileTemplate[14][1][0] = 0;
        arbTileTemplate[14][1][1] = 1;
        arbTileTemplate[14][1][2] = 0;
        arbTileTemplate[14][2][0] = 2;
        arbTileTemplate[14][2][1] = 1;
        arbTileTemplate[14][2][2] = 2;

        // wall alone
        arbTileTemplate[15][0][0] = 2;
        arbTileTemplate[15][0][1] = 0;
        arbTileTemplate[15][0][2] = 2;
        arbTileTemplate[15][1][0] = 0;
        arbTileTemplate[15][1][1] = 1;
        arbTileTemplate[15][1][2] = 0;
        arbTileTemplate[15][2][0] = 2;
        arbTileTemplate[15][2][1] = 0;
        arbTileTemplate[15][2][2] = 2;
    }
}
