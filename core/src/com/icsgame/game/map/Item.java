package com.icsgame.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Item {

    protected MapMain map;
    protected Texture txt;
    protected Vector2 vPos;
    protected int nW, nH;

    public Item(MapMain _map, Texture _txt, Vector2 _vPos, int _nW, int _nH){
        map = _map;
        txt = _txt;
        vPos = _vPos;
        nW = _nW;
        nH = _nH;
    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(txt, vPos.x, vPos.y, nW, nH);
        batch.end();
    }
}
