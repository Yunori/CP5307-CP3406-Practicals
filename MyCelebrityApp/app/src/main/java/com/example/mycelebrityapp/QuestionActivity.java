package com.example.mycelebrityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;
import java.util.Objects;

public class QuestionActivity extends AppCompatActivity
{

    private CountDownTimer timer;
    private MaterialButton exitButton;
    private QuestionFragment questionFragment;
    private StatusFragment statusFragment;
    private long timeLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Objects.requireNonNull(getSupportActionBar()).hide();
            exitButton = findViewById(R.id.exit);
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitButton.setEnabled(false);
                    checkState(State.GAME_OVER);
                }
            });
        }
        Difficulty level = (Difficulty) getIntent().getSerializableExtra("level");
        FragmentManager fragmentManager = getSupportFragmentManager();
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
        if(statusFragment != null) {
            assert questionFragment != null;
            statusFragment.setScore(questionFragment.getScore());
        }
        if (savedInstanceState != null){
            timeLeft = savedInstanceState.getLong("time");
            enableTimer(true, null);
        }

        else {
            enableTimer(false, level);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            item.setEnabled(false);
            checkState(State.GAME_OVER);
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkState(State state)
    {
        switch (state)
        {
            case CONTINUE_GAME:
                if(statusFragment != null)
                    statusFragment.setScore(questionFragment.getScore());
                break;
            case GAME_OVER:
                timer.cancel();
                statusFragment.setScore(questionFragment.getScore());
                statusFragment.setMessage("Game Over!");
                questionFragment.setVisibility(false);
                Intent intent = new Intent(this, GameOverActivity.class);
                intent.putExtra("score", questionFragment.getScore());
                finish();
                startActivity(intent);
        }
    }

    public void enableTimer(boolean restore, Difficulty difficulty)
    {
        long timerSeconds = timeLeft;
        if(!restore) {
            switch (difficulty) {
                case MEDIUM:
                    timerSeconds = 61000;
                    break;
                case HARD:
                    timerSeconds = 91000;
                    break;
                case EXPERT:
                    timerSeconds = 181000;
                    break;
                default:
                    timerSeconds = 31000;
            }
        }
        timer = new CountDownTimer(timerSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeLeft = millisUntilFinished;
                statusFragment.setMessage(String.format(Locale.getDefault(),
                        "Time Remaining: %d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish()
            {
                checkState(State.GAME_OVER);
            }
        };

        timer.start();
    }

    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("time", timeLeft);
        timer.cancel();
    }

    @Override
    public void onBackPressed() {
        checkState(State.GAME_OVER);
    }
}
