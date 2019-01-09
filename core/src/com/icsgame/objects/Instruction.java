package com.icsgame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Instruction {

    Vector2 vPos, vSize;

    Texture txtBG;

    Sprite[] arTxts;

    String[] arFonts;
    Vector3[] arFontPos;

    BitmapFont font;

    public Instruction(int nX, int nY, int nW, int nH, String sType) {
        vPos = new Vector2(nX, nY);
        vSize = new Vector2(nW, nH);

        font = new BitmapFont(Gdx.files.internal("fontHighscores.fnt"));
        font.setColor(Color.WHITE);

        loadType(sType);
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < arTxts.length; i++) {
            arTxts[i].draw(batch);
        }
        for (int i = 0; i < arFonts.length; i++) {
            font.getData().setScale(arFontPos[i].z);
            font.draw(batch, arFonts[i], arFontPos[i].x, arFontPos[i].y);
        }
    }

    public void loadType(String sType) {

        // Load File
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("instructions/" + sType + ".properties");

            // load a properties file
            prop.load(input);

            // Set up arrays
            // Textures
            arTxts = new Sprite[Integer.valueOf(prop.getProperty("txtNum"))];

            for (int i = 0; i < arTxts.length; i++) {
                arTxts[i] = new Sprite();
                Texture txtTemp = new Texture(String.valueOf(prop.getProperty("txt"+i+"File")));
                arTxts[i].setTexture(txtTemp);
                arTxts[i].setRegion(txtTemp);
                arTxts[i].setX(Integer.valueOf(prop.getProperty("txt"+i+"X")));
                arTxts[i].setY(Integer.valueOf(prop.getProperty("txt"+i+"Y")));
                arTxts[i].setSize(Integer.valueOf(prop.getProperty("txt"+i+"W")),
                        Integer.valueOf(prop.getProperty("txt"+i+"H")));
            }

            // Fonts and Text
            int nFontNum = Integer.valueOf(prop.getProperty("fontNum"));
            arFonts = new String[nFontNum];
            arFontPos = new Vector3[nFontNum];

            for (int i = 0; i < arFonts.length; i++) {
                arFonts[i] = String.valueOf(prop.getProperty("font"+i+"Text"));
                arFontPos[i] = new Vector3();
                arFontPos[i].set(Integer.valueOf(prop.getProperty("font"+i+"X")),
                        Integer.valueOf(prop.getProperty("font"+i+"Y")),
                        Float.valueOf(prop.getProperty("font"+i+"Size")));
            }

            // Load Background
            txtBG = new Texture(String.valueOf(prop.getProperty("background")));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
