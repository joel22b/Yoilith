package com.icsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.icsgame.Main;
import com.icsgame.game.Player;
import com.icsgame.game.enemies.Basic;
import com.icsgame.game.enemies.Enemy;
import com.icsgame.game.map.MapMain;
import com.icsgame.game.map.Tile;
import com.icsgame.game.ui.PlayerInfo;
import com.icsgame.game.utils.Camera;
import com.icsgame.game.utils.InputManager;
import com.icsgame.game.utils.RectCollision;
import com.icsgame.game.weapons.projectiles.Explosive;
import com.icsgame.game.weapons.projectiles.Projectile;
import com.icsgame.objects.TestPolygon;

import java.util.ArrayList;

public class ScrLineOfSight extends ScrGame {

    public SpriteBatch batch = new SpriteBatch();
    public int nX = 0, nY = 0, nW = 3, nH = 3, nTileSize = 100;

    public ShapeRenderer shapeRenderer = new ShapeRenderer();

    ArrayList<TestPolygon> testPolygons = new ArrayList<>();

    public ScrLineOfSight(Main _main) {
        super(_main);
        main = _main;

        rectCollision = new RectCollision();
        map = new MapMain(this, rectCollision);
        camera = new Camera(this, main.nWidth, main.nHeight);
        camera.setFollowBox(300, 200);
        input = new InputManager(this);
        playerInfo = new PlayerInfo(camera);

        nNoSpawnPlayerRadius = 50;
    }

    @Override
    public void setupGame(){
        map.createMap(nX, nY, nW, nH, nTileSize, "Desert");

        map.getTiles()[0][0] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 0, 0, nTileSize, 50);
        map.getTiles()[0][1] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 0, 100, nTileSize, 50);
        map.getTiles()[0][2] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 0, 200, nTileSize, 50);

        map.getTiles()[1][0] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 100, 0, nTileSize, 50);
        map.getTiles()[1][1] = new Tile(map, 2, new Texture("themeDesert/tileWallAlone.png"), 100, 100, nTileSize, 50);
        map.getTiles()[1][2] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 100, 200, nTileSize, 50);

        map.getTiles()[2][0] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 200, 0, nTileSize, 50);
        map.getTiles()[2][1] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 200, 100, nTileSize, 50);
        map.getTiles()[2][2] = new Tile(map, 1, new Texture("themeDesert/tileFloor.png"), 200, 200, nTileSize, 50);

        // Player
        player = new Player(this, "players/1", map.getMapGen().findPlayerSpawnRect(nX, nY, nW, nH, nTileSize), new Vector2(0, 0));
        camera.setPosition(player.getPosition());
        playerInfo.setPlayer(player);

        projectiles = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    @Override
    protected void doTick() {
        if(!input.handleInput() && player.getHealth() > 0) { // Kill game condition
            // Test collision
            collisionDetection();

            // Update
            update();

            // Render
            renderGame();
        } else {
            // Kill game
            main.changeScreen(0);
            killGame();
        }
    }

    @Override
    protected void update(){
        shapeRenderer.setProjectionMatrix(getCamera().getProjectionMatrix());
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setColor(0, 0, 1, 1);

        if (Gdx.input.justTouched()) {
            if (testPolygons.size() < 1) {
                testPolygons.add(new TestPolygon(this));
            } else if (!testPolygons.get(testPolygons.size()-1).addPoint(new Vector2(
                    camera.unProject(new Vector3(Gdx.input.getX(), 0, 0)).x,
                    camera.unProject(new Vector3(0, Gdx.input.getY(), 0)).y
            ))) {
                testPolygons.add(new TestPolygon(this));
            }
        }

        // Camera Update
        camera.update(batch);
        camera.follow(player.getX(), player.getY(), (int)player.getW(), (int)player.getH());
    }

    @Override
    protected void renderGame(){
        // Render Game Assets
        map.render(batch);

        for (int i = 0; i < testPolygons.size(); i++) {
            testPolygons.get(i).render(shapeRenderer);
        }
    }

    @Override
    protected void collisionDetection(){
        // With Map
        for (int x = 0; x < map.getTiles().length; x++){
            for (int y = 0; y < map.getTiles().length; y++){
                if(map.getTiles()[x][y].getType() != 1) {
                    // For Player
                    if (rectCollision.isColliding(player.getRect(), map.getTiles()[x][y].getRect())) {
                        rectCollision.collisionResponseSimple(player.getRect(), map.getTiles()[x][y].getRect(),
                                player.getVel());
                    }

                    // For Enemies
                    for (int i = 0; i < enemies.size(); i++) {
                        if(rectCollision.isColliding(enemies.get(i).getRect(), map.getTiles()[x][y].getRect())) {
                            rectCollision.collisionResponseSimple(enemies.get(i).getRect(), map.getTiles()[x][y].getRect(),
                                    enemies.get(i).getVel(), enemies.get(i).getSpeed());
                        }
                    }

                    // For Projectiles
                    for (int i = 0; i < projectiles.size(); i++){
                        if (rectCollision.isColliding(projectiles.get(i).getRect(), map.getTiles()[x][y].getRect())){
                            if(projectiles.get(i).getClass() == Explosive.class) { // Checks if the Projectile is an Explosive
                                projectiles.get(i).reverseDirection();
                            } else {
                                killProjectile(i);
                            }
                        }
                    }
                }
            }
        }

        // With Enemies
        for (int i = 0; i < enemies.size(); i++) {
            //With Player
            if(rectCollision.isColliding(enemies.get(i).getRect(), player.getRect())) {
                rectCollision.collisionResponseSimple(enemies.get(i).getRect(), player.getRect(), enemies.get(i).getVel(),
                        enemies.get(i).getSpeed());
            }
        }
    }

    @Override
    public void spawnEnemy(){

        enemies.add(new Basic(this, new Texture("themeDesert/tileBoundary.png"),
                80, 80, 2, 0.5f));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
