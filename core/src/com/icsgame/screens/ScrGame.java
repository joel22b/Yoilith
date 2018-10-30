package com.icsgame.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.Main;
import com.icsgame.game.Player;
import com.icsgame.game.enemies.Enemy;
import com.icsgame.game.ui.PlayerInfo;
import com.icsgame.game.utils.Camera;
import com.icsgame.game.utils.InputManager;
import com.icsgame.game.map.MapMain;
import com.icsgame.game.utils.RectCollision;
import com.icsgame.game.weapons.Bullet;
import com.icsgame.game.weapons.Explosive;

import java.util.ArrayList;

/* ======================== ScrGame ================================
Implements a Screen
This is the game
Runs the game
================================================================== */

public class ScrGame implements Screen {

    Main main;
    public SpriteBatch batch = new SpriteBatch();
    public int nX = 0, nY = 0, nW = 20, nH = 20, nTileSize = 100;

    // Tick Rate / Frames Info
    float tickAccumulator, tickRate;
    int TICK_RATE = 30;
    float TICK_PERIOD = 1 / TICK_RATE;

    // Game Assets
    MapMain map;
    Camera camera;
    InputManager input;
    RectCollision rectCollision;

    // Game UI
    PlayerInfo playerInfo;

    // Player
    Player player;

    // Bullets
    ArrayList<Bullet> bullets;
    ArrayList<Explosive> explosives;
    ArrayList<Enemy> enemies;

    public ScrGame(Main _main) {
        main = _main;
        createGameAssets();
    }

    public void setupGame(){
        map.createMap(nX, nY, nW, nH, nTileSize, "Desert");

        // Player
        player = new Player(this, "players/1", map.getMapGen().findPlayerSpawnRect(nX, nY, nW, nH, nTileSize), new Vector2(0, 0));
        camera.setPosition(player.getPosition());
        playerInfo.setPlayer(player);

        bullets = new ArrayList<>();
        explosives = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    private void createGameAssets(){
        rectCollision = new RectCollision();
        map = new MapMain(this, rectCollision);
        camera = new Camera(this, main.nWidth, main.nHeight);
        camera.setFollowBox(300, 200);
        input = new InputManager(this);
        playerInfo = new PlayerInfo(camera);
    }

    private void doTick(){
        if(!input.handleInput() && player.getHealth() > 0) {
            collisionDetection();
            update();
            renderGame();
        } else {
            main.changeScreen(0);
            killGame();
        }
    }

    private void update(){
        //Player Update
        player.update();

        // Enemies Update
        for (int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).update()){
                // Kill
            }
        }

        // Bullets Update
        for (int i = 0; i < bullets.size(); i++){
            if(bullets.get(i).update()){
                killBullet(i);
            }
        }

        // Explosives Update
        for (int i = 0; i < explosives.size(); i++){
            if(explosives.get(i).update()){
                explode(explosives.get(i).getRect(), explosives.get(i).getDamage(), explosives.get(i).getRange());
                killExplosive(i);
            }
        }

        // Camera Update
        camera.update(batch);
        camera.follow(player.getX(), player.getY(), (int)player.getW(), (int)player.getH());
    }

    private  void renderGame(){
        // Render Game Assets
        map.render(batch);

        // Render Enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(batch);
        }

        // Render Player
        player.render(batch);

        // Render Bullets
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).render(batch);
        }

        // Render Explosives
        for (int i = 0; i < explosives.size(); i++){
            explosives.get(i).render(batch);
        }

        // Render UI
        playerInfo.render(batch);
    }

    private void collisionDetection(){
        // With Map
        for (int x = 0; x < map.getTiles().length; x++){
            for (int y = 0; y < map.getTiles().length; y++){
                if(map.getTiles()[x][y].getType() != 1) {
                    // For Player
                    if (rectCollision.isColliding(player.getRect(), map.getTiles()[x][y].getRect())) {
                        rectCollision.collisionResponseSimple(player.getRect(), map.getTiles()[x][y].getRect(), player.getVel());
                    }

                    // For Bullets
                    for (int i = 0; i < bullets.size(); i++){
                        if (rectCollision.isColliding(bullets.get(i).getRect(), map.getTiles()[x][y].getRect())){
                            killBullet(i);
                        }
                    }
                }
            }
        }
    }

    private void explode(Rectangle rect, int nDamage, int nRange){
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
    }

    public void killGame(){
        map.kill();
        player.kill();
        player = null;
        bullets = null;
    }

    private void killBullet(int index){
        bullets.get(index).dispose();
        bullets.remove(index);
    }

    private void killExplosive(int index){
        explosives.get(index).dispose();
        explosives.remove(index);
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
        enemies.add(new Enemy(this, new Texture("themeDesert/tileBoundary.png"), 80, 80, 2));
    }

    public Player getPlayer(){ return player; }

    public Camera getCamera() { return camera; }

    public ArrayList<Bullet> getBullets() { return bullets; }

    public ArrayList<Explosive> getExplosives() { return explosives; }

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
