package com.example.mydrumkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static DisplayView displayView;
    private static SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        soundManager = new SoundManager(this);
        displayView = findViewById(R.id.display_view);
        new AsyncLoadSounds().execute();
    }

    public static class AsyncLoadSounds extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            soundManager.loadSound();
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            displayView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            switch (displayView.getPosition(event.getX(), event.getY())) {
                                case CRASH_ONE:
                                    displayView.setDisplayMessage("Crash");
                                    soundManager.play(soundManager.getSoundID("crash"));
                                    break;
                                case SPLASH:
                                    displayView.setDisplayMessage("Splash");
                                    soundManager.play(soundManager.getSoundID("splash"));
                                    break;
                                case CRASH_TWO:
                                    displayView.setDisplayMessage("Crash");
                                    soundManager.play(soundManager.getSoundID("crash"));
                                    break;
                                case RIDE:
                                    displayView.setDisplayMessage("Ride");
                                    soundManager.play(soundManager.getSoundID("ride"));
                                    break;
                                case CHH:
                                    displayView.setDisplayMessage("Close Hi-Hat");
                                    soundManager.play(soundManager.getSoundID("chh"));
                                    break;
                                case TOM_ONE:
                                    displayView.setDisplayMessage("Tom");
                                    soundManager.play(soundManager.getSoundID("tom"));
                                    break;
                                case TOM_TWO:
                                    displayView.setDisplayMessage("Tom");
                                    soundManager.play(soundManager.getSoundID("midtom"));
                                    break;
                                case TOM_THREE:
                                    displayView.setDisplayMessage("Tom");
                                    soundManager.play(soundManager.getSoundID("tom"));
                                    break;
                                case OHH:
                                    displayView.setDisplayMessage("Open Hi-Hat");
                                    soundManager.play(soundManager.getSoundID("ohh"));
                                    break;
                                case KICK_ONE:
                                    displayView.setDisplayMessage("Kick");
                                    soundManager.play(soundManager.getSoundID("kick"));
                                    break;
                                case SNARE:
                                    displayView.setDisplayMessage("Snare");
                                    soundManager.play(soundManager.getSoundID("snare"));
                                    break;
                                case KICK_TWO:
                                    displayView.setDisplayMessage("Kick");
                                    soundManager.play(soundManager.getSoundID("kick"));
                                    break;
                                case FLOOR:
                                    displayView.setDisplayMessage("Floor");
                                    soundManager.play(soundManager.getSoundID("floor"));
                                    break;
                                default:
                                    displayView.setDisplayMessage("None");
                                    break;
                            }
                            return true;
                        case MotionEvent.ACTION_UP:
                            v.performClick();
                            return true;
                    }
                    return false;
                }
            });
        }
    }
}
