package com.icsgame.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.game.Player;
import com.icsgame.game.utils.Camera;
import com.icsgame.objects.Bar;

/* ======================== PlayerInfo ================================
Displays the information about the player in the top left of the screen
==================================================================== */

public class PlayerInfo {

    Camera camera;
    Player player;

    // Setup measurements
    int nMargin = 20 , nObjectSize = 40;

    // UI Assets
    BitmapFont fontBlack;
    Texture txtHeart, txtBullet, txtBomb, txtBlack, txtRed, txtGreen, txtBlue, txtGrey;
    Bar barHealth, barGun, barBomb;

    public PlayerInfo(Camera camera){
        this.camera = camera;

        // Create Font
        fontBlack = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        fontBlack.setColor(Color.BLACK);

        // Setup UI
        txtHeart = new Texture("extra/heart.png");
        txtBullet = new Texture("extra/Bullet.png");
        txtBomb = new Texture("extra/Bomb.png");

        txtBlack = new Texture("extra/black.png");
        txtRed = new Texture("extra/red.png");
        txtGreen = new Texture("extra/green.png");
        txtBlue = new Texture("extra/blue.png");
        txtGrey = new Texture("extra/grey.png");
    }

    public void render(SpriteBatch batch){
        batch.begin();

        // Render the box to show the active weapon
        renderBox(batch, camera.getX()-(camera.getW()/2)+nMargin,
                camera.getY()+(camera.getH()/2)-(objectOffset()*2)-(objectOffset()*player.getActiveWeapon()), nObjectSize,
                nObjectSize, 5, txtGrey);


        // Render Health Bar
        batch.draw(txtHeart, camera.getX()-(camera.getW()/2)+nMargin,
                camera.getY()+(camera.getH()/2)-nObjectSize-nMargin, nObjectSize, nObjectSize);

        barHealth.update(camera.getX()-(camera.getW()/2)+nMargin+objectOffset(),
                camera.getY()+(camera.getH()/2)- (objectOffset()*1), player.getHealth(),
                player.getHealthMax());
        barHealth.render(batch);

        // Render Ammo Bar
        batch.draw(txtBullet, camera.getX()-(camera.getW()/2)+nMargin,
                camera.getY()+(camera.getH()/2)- (objectOffset()*2), nObjectSize, nObjectSize);

        barGun.update(camera.getX()-(camera.getW()/2)+nMargin+objectOffset(),
                camera.getY()+(camera.getH()/2)- (objectOffset()*2), player.getGun().getAmmo(),
                player.getGun().getAmmoMax());
        barGun.render(batch);

        // Render Bombs Bar
        batch.draw(txtBomb, camera.getX()-(camera.getW()/2)+nMargin,
                camera.getY()+(camera.getH()/2)- (objectOffset()*3), nObjectSize, nObjectSize);

        barBomb.update(camera.getX()-(camera.getW()/2)+nMargin+objectOffset(),
                camera.getY()+(camera.getH()/2)- (objectOffset()*3),
                player.getExplosiveLauncher().getAmmo(), player.getExplosiveLauncher().getAmmoMax());
        barBomb.render(batch);

        batch.end();
    }

    private void renderBox(SpriteBatch batch, float x, float y, float w, float h, float size, Texture txt){
        batch.draw(txt, x, y-size, w, size);
        batch.draw(txt, x-size, y, size, h);
        batch.draw(txt, x, y+h, w, size);
        batch.draw(txt, x+w, y, size, h);
    }

    private int objectOffset() { return nMargin+nObjectSize;}

    public void setPlayer(Player player) {
        this.player = player;

        barHealth = new Bar(txtRed, txtGreen, camera.getX()-(camera.getW()/2)+nMargin+objectOffset(),
                camera.getY()+(camera.getH()/2)- nObjectSize -nMargin, nObjectSize, player.getHealth(),
                player.getHealthMax(), 5, true);
        barGun = new Bar(txtGrey, txtBlue, camera.getX()-(camera.getW()/2)+nMargin+objectOffset(),
                camera.getY()+(camera.getH()/2)- (objectOffset()*2), nObjectSize, player.getGun().getAmmo(),
                player.getGun().getAmmoMax(), 5, true);
        barBomb = new Bar(txtRed, txtGrey, camera.getX()-(camera.getW()/2)+nMargin+objectOffset(),
                camera.getY()+(camera.getH()/2)- (objectOffset()*3), nObjectSize,
                player.getExplosiveLauncher().getAmmo(), player.getExplosiveLauncher().getAmmoMax(), 5, true);
    }
}
