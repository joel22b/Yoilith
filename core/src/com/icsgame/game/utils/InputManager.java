package com.icsgame.game.utils;


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
        click();
        keyboard();
    }

    private void click(){
        if(Gdx.input.isTouched()){
            game.getPlayer().getGun().fire();
        }
    }

    private void wasd(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            game.getPlayer().addVel(0f, 0.5f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            game.getPlayer().addVel(-0.5f, 0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.getPlayer().addVel(0f, -0.5f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            game.getPlayer().addVel(0.5f, 0f);
        }
    }

    private void keyboard(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            game.getPlayer().getGun().reload();
        }
    }
}
