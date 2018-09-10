package com.icsgame.game;

import com.badlogic.gdx.Screen;
import com.icsgame.Main;
import com.icsgame.game.map.MapMain;

public class GameMain implements Screen {

    Main main;

    // Game Assets
    MapMain map;

    public GameMain (Main _main) {
        main = _main;
        createGameAssets();
    }

    public void setupGame(){
        map.createMap(100, 100, 20, 10, 10, 10);
    }

    private void createGameAssets(){
        map = new MapMain(this);
    }

    @Override
    public void render(float delta){
        // Render Game Assets
        map.render();
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
