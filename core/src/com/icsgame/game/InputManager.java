package com.icsgame.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputManager {

    GameMain game;

    public InputManager(GameMain _game){
        game = _game;
    }

    public void handleInput(){
        wasd();
    }

    private void wasd(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            game.camera.camera.translate(0, 2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            game.camera.camera.translate(-2, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.camera.camera.translate(0, -2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            game.camera.camera.translate(2, 0);
        }
    }
}
