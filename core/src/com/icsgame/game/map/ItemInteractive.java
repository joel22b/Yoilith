package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/* ======================== ItemInteractive ================================
Extends an Item
The version of an Item that the player can interact with
========================================================================= */

public class ItemInteractive extends Item {

    public ItemInteractive(MapMain map, Texture txt, Vector2 vPos, int nW, int nH){
        super(map, txt, vPos, nW, nH);
    }
}
