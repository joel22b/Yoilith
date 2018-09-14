package com.icsgame.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.icsgame.screens.ScrGame;

public class InputManager {

    ScrGame game;

    public InputManager(ScrGame _game){
        game = _game;
    }

    public void handleInput(){
        wasd();
    }

    private void wasd(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            game.sprBox.translate(0, 3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            game.sprBox.translate(-3, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.sprBox.translate(0, -3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            game.sprBox.translate(3, 0);
        }
    }
}
