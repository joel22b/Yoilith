package com.icsgame.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.icsgame.game.weapons.Gun;
import com.icsgame.screens.ScrGame;

public class Basic extends Enemy {

    Gun gun;

    public Basic(ScrGame game, Texture txt, int w, int h, float fSpeed) {
        super(game, txt, w, h, fSpeed, 20, 20);
        gun = new Gun(game, this);
        weapon = gun;
    }

    public Basic(ScrGame game, Texture txt, Rectangle rect, float fSpeed) {
        super(game, txt, rect, fSpeed);
    }
}
