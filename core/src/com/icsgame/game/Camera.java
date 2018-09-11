package com.icsgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Camera {

    GameMain game;
    OrthographicCamera camera;
    StretchViewport viewport;

    public Camera(GameMain _game, int w, int h){
        game = _game;

        // Setup Camera
        // From Ameer
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new StretchViewport(w, h, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        resize(w, h);
    }

    public void update(SpriteBatch batch){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void resize(int w, int h){
        viewport.update(w, h);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }
}
