package com.icsgame.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.Main;
import com.icsgame.game.Player;
import com.icsgame.game.enemies.Basic;
import com.icsgame.game.enemies.Enemy;
import com.icsgame.game.ui.PlayerInfo;
import com.icsgame.game.utils.Camera;
import com.icsgame.game.utils.InputManager;
import com.icsgame.game.map.MapMain;
import com.icsgame.game.utils.RectCollision;
import com.icsgame.game.weapons.projectiles.Explosive;
import com.icsgame.game.weapons.projectiles.Projectile;

import java.util.ArrayList;

/* ======================== ScrGame ================================
Implements a Screen
This is the game
Runs the game
================================================================== */

public class ScrGame implements Screen {

    protected Main main;
    public SpriteBatch batch = new SpriteBatch();
    public int nX = 0, nY = 0, nW = 20, nH = 20, nTileSize = 100;

    // Tick Rate / Frames Info
    protected float tickAccumulator, tickRate;
    protected int TICK_RATE = 30;
    protected float TICK_PERIOD = 1 / TICK_RATE;

    // Game Assets
    protected MapMain map;
    protected Camera camera;
    protected InputManager input;
    protected RectCollision rectCollision;

    // Game UI
    protected PlayerInfo playerInfo;

    // Player
    protected Player player;

    // ArrayLists
    protected ArrayList<Projectile> projectiles;
    protected ArrayList<Enemy> enemies;

    // ====================== Hardcoded Variables =======================
    public int nNoSpawnPlayerRadius = 600;
    // ==================================================================

    public ScrGame(Main _main) {
        main = _main;

        rectCollision = new RectCollision();
        map = new MapMain(this, rectCollision);
        camera = new Camera(this, main.nWidth, main.nHeight);
        camera.setFollowBox(300, 200);
        input = new InputManager(this);
        playerInfo = new PlayerInfo(camera);
    }

    public void setupGame(){
        map.createMap(nX, nY, nW, nH, nTileSize, "Desert");

        // Player
        player = new Player(this, "players/1", map.getMapGen().findPlayerSpawnRect(nX, nY, nW, nH, nTileSize), new Vector2(0, 0));
        camera.setPosition(player.getPosition());
        playerInfo.setPlayer(player);

        projectiles = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    // Main loop of the game
    protected void doTick(){ // Runs the game at a regular tick rate
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

    protected void update(){
        //Player Update
        player.update();

        // Enemies Update
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).update()) {
                killEnemy(i);
            }
        }

        // Projectiles Update
        for (int i = 0; i < projectiles.size(); i++){
            if(projectiles.get(i).update()){
                if(projectiles.get(i).getClass() == Explosive.class) { // Checks if the Projectile is an Explosive
                    Explosive explosive = (Explosive)projectiles.get(i);
                    explode(explosive.getRect(), explosive.getDamage(), explosive.getRange());
                }

                killProjectile(i);
            }
        }

        // Camera Update
        camera.update(batch);
        camera.follow(player.getX(), player.getY(), (int)player.getW(), (int)player.getH());
    }

    protected void renderGame(){
        // Render Game Assets
        map.render(batch);

        // Render Enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(batch);
        }

        // Render Player
        player.render(batch);

        // Render Projectiles
        for (int i = 0; i < projectiles.size(); i++){
            projectiles.get(i).render(batch);
        }

        // Render UI
        playerInfo.render(batch);
    }

    protected void collisionDetection(){
        collisionDetectionMap();

        // Collision detection is not working properly 100% of the time here
        //collisionDetectionProjectiles();

        collisionDetectionEnemies();
    }

    private void collisionDetectionMap() {
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

                    /*/ For Projectiles
                    for (int i = 0; i < projectiles.size(); i++){
                        if (rectCollision.isColliding(projectiles.get(i).getRect(), map.getTiles()[x][y].getRect())){
                            if(projectiles.get(i).getClass() == Explosive.class) { // Checks if the Projectile is an Explosive
                                projectiles.get(i).reverseDirection();
                            } else {
                                killProjectile(i);
                            }
                        }
                    }*/
                }
            }
        }
    }

    private void collisionDetectionProjectiles() {
        // With Projectiles
        for (int i = 0; i < projectiles.size(); i++) {
            // With Player
            if (projectiles.get(i).getTeam() != 0) {
                //System.out.println("P: Team != 0");
                if (projectiles.get(i).getClass() != Explosive.class) { // Checks if the Projectile is an Explosive
                    //System.out.println("P: Not Bomb");
                    if (rectCollision.isColliding(projectiles.get(i).getRect(), player.getRect())) {
                        System.out.println("HIT PLAYER");
                        player.decreaseHealth(projectiles.get(i).getDamage());
                        killProjectile(i);
                        break;
                    }
                }
            }

            // With Enemies
            for (int e = 0; e < enemies.size(); e++) {
                if (projectiles.get(i).getTeam() != 1) {
                    //System.out.println("E: Team != 1");
                    if (projectiles.get(i).getClass() != Explosive.class) { // Checks if the Projectile is an Explosive
                        //System.out.println("E: Not Bomb");
                        if (rectCollision.isColliding(projectiles.get(i).getRect(), enemies.get(e).getRect())) {
                            System.out.println("HIT ENEMY");
                            enemies.get(e).decreaseHealth(projectiles.get(i).getDamage());
                            killProjectile(i);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void collisionDetectionEnemies() {
        // With Enemies
        for (int i = 0; i < enemies.size(); i++) {
            //With Player
            if(rectCollision.isColliding(enemies.get(i).getRect(), player.getRect())) {
                rectCollision.collisionResponseSimple(enemies.get(i).getRect(), player.getRect(), enemies.get(i).getVel(),
                        enemies.get(i).getSpeed());
            }
        }
    }

    public boolean collisionMap(Rectangle rect) {
        for (int x = 0; x < map.getTiles().length; x++) {
            for (int y = 0; y < map.getTiles().length; y++) {
                if (map.getTiles()[x][y].getType() != 1) {
                    if (rectCollision.isColliding(rect, map.getTiles()[x][y].getRect())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Enemy collisionEnemies(Rectangle rect) {
        for (int e = 0; e < enemies.size(); e++) {
            if (rectCollision.isColliding(rect, enemies.get(e).getRect())) {
                return enemies.get(e);
            }
        }
        return null;
    }

    public Player collisionPlayer(Rectangle rect) {
        if (rectCollision.isColliding(rect, player.getRect())) {
            return player;
        }
        return null;
    }

    protected void explode(Rectangle rect, int nDamage, int nRange){
        // Distance from bomb
        int nDist;
        // Center location of object
        Vector2 vObjectCenter = new Vector2();
        // Center of location of the bomb
        Vector2 vBombCenter = new Vector2();

        // Find Center of Bomb
        vBombCenter.set(rect.getX()+(rect.getWidth()/2), rect.getY()+(rect.getHeight()/2));

        // Check collision with map and apply nDamage
        for (int x = 0; x < map.getTiles().length; x++){
            for (int y = 0; y < map.getTiles().length; y++){
                if(map.getTiles()[x][y].getType() != 1 && map.getTiles()[x][y].getType() != 0) {
                    // Find Center of Object
                    vObjectCenter.set(map.getTiles()[x][y].getX()+(map.getTiles()[x][y].getW()/2),
                            map.getTiles()[x][y].getY()+(map.getTiles()[x][y].getH()/2));

                    // Find nDist
                    vObjectCenter.sub(vBombCenter);
                    vObjectCenter.setAngle(0);
                    nDist = (int)vObjectCenter.x;

                    if(nRange >= nDist){
                        // Is in explosion radius

                        // Find the nDamage level
                        if(nDist >= (nRange/3)*2) {
                            map.getTiles()[x][y].decreaseHealth(nDamage / 3);
                        } else if(nDist >= nRange/3) {
                            map.getTiles()[x][y].decreaseHealth((nDamage/3)*2);
                        } else {
                            map.getTiles()[x][y].decreaseHealth(nDamage);
                        }

                        // Check to see if the wall changed
                        map.getTiles()[x][y].checkHealth(map.getTxtTiles(), map.getTxtDamage());
                    }
                }
            }
        }
        // Update the Textures
        map.getMapGen().updateTexture(map.getTxtTiles());

        // Check collision with player and apply nDamage
        // Find Center of Object
        vObjectCenter.set(player.getX()+(player.getW()/2),
                player.getY()+(player.getH()/2));

        // Find nDist
        vObjectCenter.sub(vBombCenter);
        vObjectCenter.setAngle(0);
        nDist = (int)vObjectCenter.x;

        if(nRange >= nDist) {
            // Is in explosion radius

            // Find the nDamage level
            if (nDist >= (nRange / 3) * 2) {
                player.decreaseHealth(nDamage / 3);
            } else if (nDist >= nRange / 3) {
                player.decreaseHealth((nDamage / 3) * 2);
            } else {
                player.decreaseHealth(nDamage);
            }
        }

        // Check Enemies
        for (int i = 0; i < enemies.size(); i++) {
            // Find Center of Object
            vObjectCenter.set(enemies.get(i).getX()+(enemies.get(i).getRect().width/2),
                    enemies.get(i).getY()+(enemies.get(i).getRect().height/2));

            // Find nDist
            vObjectCenter.sub(vBombCenter);
            vObjectCenter.setAngle(0);
            nDist = (int)vObjectCenter.x;

            if(nRange >= nDist) {
                // Is in explosion radius

                // Find the nDamage level
                if (nDist >= (nRange / 3) * 2) {
                    enemies.get(i).decreaseHealth(nDamage / 3);
                } else if (nDist >= nRange / 3) {
                    enemies.get(i).decreaseHealth((nDamage / 3) * 2);
                } else {
                    enemies.get(i).decreaseHealth(nDamage);
                }
            }
        }
    }

    public void killGame(){
        map.kill();
        player.kill();
        player = null;
        projectiles = null;
        enemies = null;
    }

    protected void killProjectile(int index){
        projectiles.get(index).dispose();
        projectiles.remove(index);
    }

    protected void killEnemy(int index){
        enemies.get(index).dispose();
        enemies.remove(index);
    }

    @Override
    public void render(float delta) {
        // Tick counting system by David Neuman to regulate the frames

        tickAccumulator += delta;

        //Check if a tick's worth of time has passed since last tick
        if (tickAccumulator > TICK_PERIOD) {

            doTick();

            //Get remainder of time
            tickAccumulator -= TICK_PERIOD;

            //If two ticks worth of time or more has passed since last tick,
            //prevent accumulator from winding up
            if (tickAccumulator > TICK_PERIOD) {
                tickRate = 1 / tickAccumulator;
                tickAccumulator = TICK_PERIOD;
            } else {
                tickRate = TICK_RATE;
            }
        }
    }

    public void spawnEnemy(){

        enemies.add(new Basic(this, new Texture("themeDesert/tileBoundary.png"),
                80, 80, 2));
    }

    public Player getPlayer(){ return player; }

    public Camera getCamera() { return camera; }

    public ArrayList<Projectile> getProjectiles() { return projectiles; }

    public Main getMain() { return main; }

    public MapMain getMap() { return map; }

    public RectCollision getRectCollision() {
        return rectCollision;
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
