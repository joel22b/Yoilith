package com.icsgame.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
import com.icsgame.game.map.MapMain;

public class GameMain implements Screen {

    Main main;
    public SpriteBatch batch = new SpriteBatch();

    // Game Assets
    MapMain map;
    Camera camera;
    InputManager input;

    public GameMain (Main _main) {
        main = _main;
        createGameAssets();
    }

    public void setupGame(){
        map.createMap(0, 0, 20, 10, 100, 100);
    }

    private void createGameAssets(){
        map = new MapMain(this);
        camera = new Camera(this, main.nWidth, main.nHeight);
        input = new InputManager(this);
    }

    @Override
    public void render(float delta){
        input.handleInput();

        camera.update(batch);

        // Render Game Assets
        map.render(batch);
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
