package com.example.mycelebrityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
{

    private GameFragment gameFragment;
    Difficulty level;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.game);
    }

    public void setSettings(State state)
    {
        level = gameFragment.getLevel();

        String text = String.format(Locale.getDefault(), "state: %s level: %s", state, level);
        Log.i("MainActivity", text);

                    Intent intent = new Intent(this, QuestionActivity.class);
                    intent.putExtra("level", level);
                    startActivity(intent);
    }
}
