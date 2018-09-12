package com.icsgame.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
import com.icsgame.game.map.MapMain;

public class GameMain implements Screen {

    Main main;
    public SpriteBatch batch = new SpriteBatch();
    public int nX = 0, nY = 0, nW = 20, nH = 10, nTileSize = 100;
    Sprite sprBox = new Sprite(new Texture("themeDesert/tileBoundary.png"), 300, 300 ,100, 100);

    // Game Assets
    MapMain map;
    Camera camera;
    InputManager input;

    public GameMain (Main _main) {
        main = _main;
        createGameAssets();
    }

    public void setupGame(){
        map.createMap(nX, nY, nW, nH, nTileSize, nTileSize);
    }

    private void createGameAssets(){
        map = new MapMain(this);
        camera = new Camera(this, main.nWidth, main.nHeight);
        camera.setFollowBox(300, 200);
        input = new InputManager(this);
    }

    @Override
    public void render(float delta){
        input.handleInput();

        camera.update(batch);

        // Render Game Assets
        map.render(batch);

        camera.follow(sprBox.getX(), sprBox.getY(), 100, 100);

        batch.begin();
        sprBox.draw(batch);
        batch.end();
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
