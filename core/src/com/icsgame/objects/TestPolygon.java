package com.icsgame.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icsgame.game.map.Tile;
import com.icsgame.screens.ScrGame;

public class TestPolygon {

    ScrGame game;
    Vector3[] v;
    Polygon polygon;
    boolean bComplete, bTestDone = false;

    public TestPolygon(ScrGame game) {
        this.game = game;
        bComplete = false;
        polygon = new Polygon();
        v = new Vector3[3];
    }

    public boolean addPoint(Vector2 point) {
        for (int i = 0; i < 3; i++) {
            if (v[i] == null) {
                v[i] = new Vector3();
                v[i].set(point, 0);
                setPoints();
                return true;
            }
        }
        return false;
    }

    private void setPoints() {
        float[] points = new float[6];
        for (int i = 0; i < 3; i++) {
            if (v[i] != null) {
                points[(i*2)] = v[i].x;
                points[(i*2)+1] = v[i].y;
                bComplete = true;
            } else {
                points[(i*2)] = 0;
                points[(i*2)+1] = 0;
                bComplete = false;
                break;
            }
        }
        polygon.setVertices(points);
    }

    public void render(ShapeRenderer shapeRenderer) {
        if(bComplete == true) {
            shapeRenderer.begin();
            shapeRenderer.line(v[0], v[1]);
            shapeRenderer.line(v[1], v[2]);
            shapeRenderer.line(v[0], v[2]);
            shapeRenderer.end();
            if (!bTestDone) {
                System.out.println("hasLineOfSight = "+hasLineOfSight());
                bTestDone = true;
            }
        }
    }

    protected boolean hasLineOfSight() {
        System.out.println("===================================================");

        // Check if the ray collides with any walls
        for (int x = 0; x < game.getMap().getTiles().length; x++) {
            for (int y = 0; y < game.getMap().getTiles()[x].length; y++) {
                if (game.getMap().getTiles()[x][y].getType() != 1) {
                    Tile tile = game.getMap().getTiles()[x][y];
                    //System.out.println("Is Wall! Tile ["+x+"]["+y+"] Type: "+tile.getType());
                    if (Intersector.overlapConvexPolygons(polygon, tile.getPolygon())) {
                        System.out.print("Tile Polygon Vertices: ");
                        for (int i = 0; i < 8; i++) {
                            if (i != 0) {
                                System.out.print(", ");
                            }
                            System.out.print(tile.getPolygon().getVertices()[i]);
                        }
                        System.out.println();
                        System.out.print("Line Polygon Vertices: ");
                        for (int i = 0; i < 6; i++) {
                            if (i != 0) {
                                System.out.print(", ");
                            }
                            System.out.print(polygon.getVertices()[i]);
                        }
                        System.out.println();

                        return false;
                    }
                }
            }
        }
        System.out.println();
        return true;
    }
}
