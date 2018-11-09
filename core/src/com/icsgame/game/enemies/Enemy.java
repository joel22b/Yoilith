package com.icsgame.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.*;
import com.icsgame.game.map.Tile;
import com.icsgame.game.weapons.Weapon;
import com.icsgame.objects.Bar;
import com.icsgame.screens.ScrGame;

import java.util.Random;

/* ======================== Enemy ================================
Basic generic enemy class
================================================================== */

public abstract class Enemy {

    public boolean bCan = true;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    Vector2 p0 = new Vector2();
    Vector2 p1 = new Vector2();
    Vector2 p2 = new Vector2();
    Polygon polygon;

    protected ScrGame game;

    protected int nHealth;
    protected int nHealthMax;

    protected Weapon weapon;

    protected float fAimAngle;
    protected Vector2 vAimAngle;

    protected Rectangle rect;
    protected Texture txt;
    protected Vector2 vVel;
    protected float fSpeed;
    protected Bar bar;

    protected Random ranGen = new Random();

    public Enemy(ScrGame game, Texture txt, int w, int h, float fSpeed, int nHealth, int nHealthMax) {
        this.game = game;
        this.txt = txt;
        this.rect = new Rectangle(0, 0, w, h);
        this.fSpeed = fSpeed;
        this.nHealth = nHealth;
        this.nHealthMax = nHealthMax;

        fAimAngle = 0;

        vVel = new Vector2();
        bar = new Bar("extra/red.png", "extra/green.png", getX(), getY()+rect.getHeight()+5, 20,
                nHealth, nHealthMax, w/nHealthMax, false);

        vAimAngle = new Vector2();

        spawnController();
    }

    public Enemy(ScrGame game, Texture txt, Rectangle rect, float fSpeed) {
        this.game = game;
        this.txt = txt;
        this.rect.set(rect);
        this.fSpeed = fSpeed;

        vVel = new Vector2();

        vAimAngle = new Vector2();

        spawnController();
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(txt, rect.x, rect.y, rect.width, rect.height);

        // Health Bar
        bar.update(getX(), getY()+rect.getHeight()+5, nHealth, nHealthMax);
        bar.render(batch);

        shapeRenderer.setProjectionMatrix(game.getCamera().getProjectionMatrix());
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.begin();
        //shapeRenderer.line(p0, p1);
        //shapeRenderer.line(p0, p2);

        if(polygon != null) {
            shapeRenderer.line(new Vector2(polygon.getVertices()[0], polygon.getVertices()[1]),
                    new Vector2(polygon.getVertices()[2], polygon.getVertices()[3]));
            shapeRenderer.line(new Vector2(polygon.getVertices()[0], polygon.getVertices()[1]),
                    new Vector2(polygon.getVertices()[4], polygon.getVertices()[5]));
            shapeRenderer.line(new Vector2(polygon.getVertices()[2], polygon.getVertices()[3]),
                    new Vector2(polygon.getVertices()[4], polygon.getVertices()[5]));
        }

        shapeRenderer.end();
        batch.end();
    }

    public boolean update() {
        if(bCan) {
            aiController();

            // Update position
            setX(getX() + (vVel.x * fSpeed));
            setY(getY() + (vVel.y * fSpeed));

            bCan = false;
        }

        return false;
    }

    protected void aiController() {
        pathfinding();
        attack();
    }

    protected void attack() {
        vAimAngle.set(game.getPlayer().getX()-getX(), game.getPlayer().getY()-getY());
        fAimAngle = vAimAngle.angle();

        if(hasLineOfSight()) {
            weapon.fire();
            System.out.println("FIRE!!!");
            System.out.println("------------------------------------------------");
        }
    }

    protected boolean hasLineOfSight() {
        Vector2 vTileEnemy = new Vector2();
        Vector2 vTilePlayer = new Vector2();

        System.out.println("===================================================");

        // Get Tile location of player and enemy
        vTileEnemy.set(game.getMap().getTileIndex((int)(getX()+(rect.getWidth()/2)), (int)(getY()+(rect.getHeight()/2))));
        vTilePlayer.set(game.getMap().getTileIndex((int)(game.getPlayer().getX()+(game.getPlayer().getW()/2)),
                (int)(game.getPlayer().getY()+(game.getPlayer().getH()/2))));

        System.out.println("tile enemy x:"+vTileEnemy.x+" y:"+vTileEnemy.y);
        System.out.println("tile player x:"+vTilePlayer.x+" y:"+vTilePlayer.y);

        // Make a line Segment of the line of sight
        Vector2 vPlayer1 = new Vector2();
        vPlayer1.set(game.getPlayer().getCenterPosition());
        Vector2 vPlayer2 = new Vector2();
        vPlayer2.set(game.getPlayer().getHeadX(), game.getPlayer().getHeadY());
        Vector2 vEnemy = new Vector2();
        vEnemy.set(getX()+(rect.width), getY()+(rect.height));

        System.out.println("player2 x:"+vPlayer2.x+" y:"+vPlayer2.y);
        System.out.println("enemy x:"+vEnemy.x+" y:"+vEnemy.y);
        System.out.println("player1 x:"+vPlayer1.x+" y:"+vPlayer1.y);

        p0.set(vPlayer1);
        p1.set(vPlayer2);
        p2.set(vEnemy);
        polygon = new Polygon(new float[]{vPlayer2.x, vPlayer2.y, vPlayer1.x, vPlayer1.y, vEnemy.x, vEnemy.y});

        // Check if the ray collides with any walls
        for (int x = 0; x < game.getMap().getTiles().length; x++) {
            for (int y = 0; y < game.getMap().getTiles()[x].length; y++) {
                if (game.getMap().getTiles()[x][y].getType() != 1) {
                    Tile tile = game.getMap().getTiles()[x][y];
                    //System.out.println("Is Wall! Tile ["+x+"]["+y+"] Type: "+tile.getType());
                    if (Intersector.overlapConvexPolygons(polygon, tile.getPolygon())) {
                        System.out.println("Intersector! Tile ["+x+"]["+y+"] Type: "+tile.getType());
                        System.out.print("Polygon Vertices: ");
                        for (int i = 0; i < 8; i++) {
                            if(i!=0) {
                                System.out.print(", ");
                            }
                            System.out.print(tile.getPolygon().getVertices()[i]);
                        }
                        System.out.println();
                        tile.setTexture(new Texture("extra/red.png"));


                        System.out.println("------------------------------------------------");
                        //polygon = tile.getPolygon();
                        //return false;
                    }
                }
            }
        }

        return true;
    }

    protected void pathfinding() {
        vVel.set(game.getPlayer().getX()-getX(), game.getPlayer().getY()-getY());
        vVel.nor();
        //vVel.set(vVel.x*(ranGen.nextFloat()*2), vVel.y*(ranGen.nextFloat()*2));
        //vVel.nor();
    }

    protected void spawnController() {
        do {
            setX(ranGen.nextInt(game.getMap().getMapW()));
            setY(ranGen.nextInt(game.getMap().getMapH()));
        } while (!canSpawn(game.getMap().getTiles()));
    }

    protected boolean canSpawn(Tile[][] tiles) {
        // Check if the enemy is close to the player
        Circle playerNoSpawnArea = new Circle();
        playerNoSpawnArea.set(game.getPlayer().getCenterPosition(), game.nNoSpawnPlayerRadius);

        if(Intersector.overlaps(playerNoSpawnArea, rect)) {
            return false;
        }

        // Check if it is colliding with the map
        for (int x = 0; x < tiles.length; x++){
            for (int y = 0; y < tiles.length; y++){
                if(game.getRectCollision().isColliding(tiles[x][y].getRect(), rect) && tiles[x][y].getType() != 1){
                    return false;
                }
            }
        }
        return true;
    }


    public void setX(float x) {
        rect.setX(x);
    }

    public void setY(float y) {
        rect.setY(y);
    }

    public float getX() { return rect.getX(); }

    public float getY() { return rect.getY(); }

    public Rectangle getRect() { return rect; }

    public Vector2 getVel() { return vVel; }

    public float getSpeed() { return fSpeed; }
}
