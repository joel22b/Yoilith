package com.icsgame.soundEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.Random;

public class SoundEngine {

    Random random;

    // Volume
    float fVolMusic, fVolSound;

    // Background Music
    Music[] music;
    ArrayList<Music> sounds;
    String sGunShot, sExplosion;
    int[] arnMusicPlayed;
    int nSong;

    public SoundEngine() {
        random = new Random();

        // Load Background Music
        nSong = 0;
        music = new Music[6];
        arnMusicPlayed = new int[6];
        clearMusicHistory();

        music[0] = Gdx.audio.newMusic(Gdx.files.internal("audio/bobaBox.mp3"));
        music[1] = Gdx.audio.newMusic(Gdx.files.internal("audio/electroman.mp3"));
        music[2] = Gdx.audio.newMusic(Gdx.files.internal("audio/background1.mp3"));
        music[3] = Gdx.audio.newMusic(Gdx.files.internal("audio/background2.mp3"));
        music[4] = Gdx.audio.newMusic(Gdx.files.internal("audio/background3.mp3"));
        music[5] = Gdx.audio.newMusic(Gdx.files.internal("audio/background4.mp3"));
        //music[2] = Gdx.audio.newMusic(Gdx.files.internal("audio/"));

        // Load Sounds
        sounds = new ArrayList<>();

        sGunShot = "audio/gunShot.mp3";
        sExplosion = "audio/explosion.mp3";

        // Set Volumes
        setVolMusic(1.0f);
        setVolSound(1.0f);
    }

    public void update() {
        if (!music[nSong].isPlaying()) {
            nextSong();
        }

        for (int i = 0; i < sounds.size(); i++) {
            if (!sounds.get(i).isPlaying()) {
                sounds.remove(i);
                i--;
            }
        }
    }

    public void setMusicVolume() {
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
        music[nSong].stop();
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

    public float getVolMusic() { return fVolMusic; }

    public void setVolMusic(float fVolMusic) {
        this.fVolMusic = fVolMusic;
        setMusicVolume();
    }

    public void addVolMusic(float fVolMusic) {
        setVolMusic(getVolMusic()+fVolMusic);
    }

    public float getVolSound() { return fVolSound; }

    public void setVolSound(float fVolSound) {
        this.fVolSound = fVolSound;
    }

    public void addVolSound(float fVolSound) {
        setVolSound(getVolSound()+fVolSound);
    }

    private void addSound(String soundFile) {
        Music sound = Gdx.audio.newMusic(Gdx.files.internal(soundFile));
        sounds.add(sound);
        sounds.get(sounds.size()-1).setVolume(fVolSound);
        sounds.get(sounds.size()-1).play();
    }

    public void playGunShot() { addSound(sGunShot); }

    public void playExplosion() { addSound(sExplosion); }
}
