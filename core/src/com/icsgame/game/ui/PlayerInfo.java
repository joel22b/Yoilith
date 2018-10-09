package com.icsgame.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.game.Player;
import com.icsgame.game.utils.Camera;

public class PlayerInfo {

    Camera camera;
    Player player;

    // Setup measurements
    int nMargin = 20 , nObjectSize = 40;

    // UI Assets
    BitmapFont fontBlack;
    Texture txtHeart, txtBullet, txtBlack, txtRed, txtGreen, txtBlue, txtGrey;

    public PlayerInfo(Camera camera){
        this.camera = camera;

        // Create Font
        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);

        // Setup UI
        txtHeart = new Texture("extra/heart.png");
        txtBullet = new Texture("extra/bullet.png");
        txtBlack = new Texture("extra/black.png");
        txtRed = new Texture("extra/red.png");
        txtGreen = new Texture("extra/green.png");
        txtBlue = new Texture("extra/blue.png");
        txtGrey = new Texture("extra/grey.png");
    }

    public void render(SpriteBatch batch){
        batch.begin();

        // Render Health Bar
        batch.draw(txtHeart, camera.getX()-(camera.getW()/2)+nMargin, camera.getY()+(camera.getH()/2)-nObjectSize-nMargin, nObjectSize, nObjectSize);
        renderBar(batch, camera.getX()-(camera.getW()/2)+nMargin+objectOffset(), camera.getY()+(camera.getH()/2)- nObjectSize -nMargin, nObjectSize, txtGreen, txtRed, player.getHealth(), player.getHealthMax(), 5);

        // Render Ammo Bar
        batch.draw(txtBullet, camera.getX()-(camera.getW()/2)+nMargin, camera.getY()+(camera.getH()/2)- (objectOffset()*2), nObjectSize, nObjectSize);
        renderBar(batch, camera.getX()-(camera.getW()/2)+nMargin+ objectOffset(), camera.getY()+(camera.getH()/2)- (objectOffset()*2), nObjectSize, txtBlue, txtGrey, player.getHealth(), player.getHealthMax(), 2);

        batch.end();
    }

    public void renderBar(SpriteBatch batch, float x, float y, int h, Texture txtFront, Texture txtBack, int nValue, int nMax, int nScale){
        // Background
        batch.draw(txtBlack, x, y, nMax*nScale, h);

        // Bar
        batch.draw(txtBack, x+2, y+2, (nMax*nScale)-4, h-4);
        batch.draw(txtFront, x+2, y+2, (nValue*nScale)-4, h-4);

        // Text
        fontBlack.draw(batch, String.valueOf(nValue), x+((nMax*nScale)/2-23), y+h-10);
    }

    private int objectOffset() { return nMargin+nObjectSize;}

    public void setPlayer(Player player) { this.player = player; }
}
