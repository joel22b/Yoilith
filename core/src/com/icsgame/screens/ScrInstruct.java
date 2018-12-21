package com.icsgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icsgame.Main;
import com.icsgame.objects.Button;
import com.icsgame.objects.Instruction;

public class ScrInstruct implements Screen {

    Main main;
    SpriteBatch batch;
    Texture txtBG;
    Sprite sprBG;
    Button btnBack;
    BitmapFont font;

    Instruction[] arInstructions;

    public ScrInstruct(Main main) {
        this.main = main;
        batch = new SpriteBatch();
        txtBG = new Texture("backgroundBlackBoards.jpg");
        sprBG = new Sprite(txtBG, 0, 0, main.nWidth, main.nHeight);

        // Create Buttons
        btnBack = new Button(660, 60, 600, 216, "btnBack.png",
                "btnBackPressed.png", "Blank.png");

        // Create Font
        font = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        font.setColor(Color.YELLOW);

        // Set up Instructons
        arInstructions = new Instruction[1];

        arInstructions[0] = new Instruction(100, 100, 100, 100, "movement");
    }

    @Override
    public void render(float delta) {
        batch.begin();

        // Draw Background
        sprBG.draw(batch);
        font.draw( batch,"Instructions:", 60, main.nHeight-100);

        batch.end();

        // Update buttons
        btnBack.update(batch);

        // Checks if buttons are pressed
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        if(btnBack.justClicked()){
            main.changeScreen(0);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
