package com.icsgame.soundEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.Random;

public class SoundEngine {

    Random random;

    // Volume
    float fVolMusic, fVolSound;

    // Background Music
    Music[] music;
    int[] arnMusicPlayed;
    int nSong;

    public SoundEngine() {
        random = new Random();

        setMusicVolume(1.0f);
        fVolSound = 1.0f;

        // Load Background Music
        nSong = 0;
        music = new Music[3];
        arnMusicPlayed = new int[3];
        clearMusicHistory();

        music[0] = Gdx.audio.newMusic(Gdx.files.internal(""));
        music[1] = Gdx.audio.newMusic(Gdx.files.internal(""));
        music[2] = Gdx.audio.newMusic(Gdx.files.internal(""));
    }

    public void update() {
        if (!music[nSong].isPlaying()) {
            nextSong();
        }
    }

    public void setMusicVolume(float fVolMusic) {
        this.fVolMusic = fVolMusic;

        for (int i = 0; i < music.length; i++) {
            music[i].setVolume(this.fVolMusic);
        }
    }

    public void clearMusicHistory() {
        for (int i = 0; i < arnMusicPlayed.length; i++) {
            arnMusicPlayed[i] = 0;
        }
    }

    public void nextSong() {
        int nRandom, nCount = 0;
        do {
            if (nCount > arnMusicPlayed.length+2) {
                clearMusicHistory();
            }
            nRandom = random.nextInt(arnMusicPlayed.length);
            nCount++;
        } while (arnMusicPlayed[nRandom] != 0);
        nSong = nRandom;
        music[nSong].play();
    }
}
