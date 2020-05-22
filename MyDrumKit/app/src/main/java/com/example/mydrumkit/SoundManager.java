package com.example.mydrumkit;

import android.content.Context;
import android.media.SoundPool;

import java.util.HashMap;

class SoundManager {

    private Context context;
    private SoundPool soundPool;
    private HashMap<String, Integer> soundPoolMap = new HashMap<>();

    SoundManager(Context context) {
        this.context = context;

        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(10);
        soundPool = builder.build();
    }

    void loadSound() {
        soundPoolMap.put("chh", soundPool.load(context, R.raw.chh, 1));
        soundPoolMap.put("crash", soundPool.load(context, R.raw.crash, 1));
        soundPoolMap.put("floor", soundPool.load(context, R.raw.floor, 1));
        soundPoolMap.put("kick", soundPool.load(context, R.raw.kick, 1));
        soundPoolMap.put("midtom", soundPool.load(context, R.raw.midtom, 1));
        soundPoolMap.put("ohh", soundPool.load(context, R.raw.ohh, 1));
        soundPoolMap.put("ride", soundPool.load(context, R.raw.ride, 1));
        soundPoolMap.put("snare", soundPool.load(context, R.raw.snare, 1));
        soundPoolMap.put("splash", soundPool.load(context, R.raw.splash, 1));
        soundPoolMap.put("tom", soundPool.load(context, R.raw.tom, 1));
    }

    HashMap<String, Integer> getSoundPoolMap() {
        return soundPoolMap;
    }

    int getSoundID(String resourcesID) {
        return soundPoolMap.get(resourcesID);
    }

    void play(int soundId) {
        soundPool.play(soundId, 1, 1, 1, 0, 1);
    }
}
