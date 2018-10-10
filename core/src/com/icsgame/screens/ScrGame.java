package com.icsgame.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.icsgame.Main;
import com.icsgame.game.Player;
import com.icsgame.game.ui.PlayerInfo;
import com.icsgame.game.utils.Camera;
import com.icsgame.game.utils.InputManager;
import com.icsgame.game.map.MapMain;
import com.icsgame.game.utils.RectCollision;
import com.icsgame.game.weapons.Bullet;

import java.util.ArrayList;

public class ScrGame implements Screen {

    Main main;
    public SpriteBatch batch = new SpriteBatch();
    public int nX = 0, nY = 0, nW = 20, nH = 20, nTileSize = 100;

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
    ArrayList<Bullet> bullets = new ArrayList<>();

    public ScrGame(Main _main) {
        main = _main;
        createGameAssets();
    }

    public void setupGame(){
        map.createMap(nX, nY, nW, nH, nTileSize, "Desert");

        // Player
        player = new Player(this, new Texture("themeDesert/tileBoundary.png"), map.getMapGen().findPlayerSpawnRect(nX, nY, nW, nH, nTileSize), new Vector2(0, 0));
        camera.setPosition(player.getPosition());
        playerInfo.setPlayer(player);
    }

    private void createGameAssets(){
        rectCollision = new RectCollision();
        map = new MapMain(this, rectCollision);
        camera = new Camera(this, main.nWidth, main.nHeight);
        camera.setFollowBox(300, 200);
        input = new InputManager(this);
        playerInfo = new PlayerInfo(camera);
    }

    public void update(){
        input.handleInput();

        //Player Update
        player.update();

        // Bullets Update
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).update();
        }

        // Collision Detection
        collisionDetection();

        // Camera Update
        camera.update(batch);
        camera.follow(player.getX(), player.getY(), (int)player.getW(), (int)player.getH());
    }

    @Override
    public void render(float delta){
        update();

        // Render Game Assets
        map.render(batch);
        player.render(batch);

        // Render Bullets
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).render(batch);
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
                            bullets.get(i).dispose();
                            bullets.remove(i);
                        }
                    }
                }
            }
        }
    }

    public Player getPlayer(){ return player; }

    public Camera getCamera() { return camera; }

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
