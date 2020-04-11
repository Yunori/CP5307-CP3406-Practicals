package com.example.myguessinggame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int randomNumber = 0, minNumber = 0, maxNumber = 10000, tryNum = 0;
    private TextInputEditText editText;
    private TextView minTextView, maxTextView, infoTextView, tryNumTextView;
    private Button buttonGuess, buttonSettings;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSettings = (Button) findViewById(R.id.buttonSettings);
        Button buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonGuess = (Button) findViewById(R.id.buttonGuess);

        editText = (TextInputEditText) findViewById(R.id.editText);
        infoTextView = (TextView) findViewById(R.id.infoTextView);
        minTextView = (TextView) findViewById(R.id.minTextView);
        maxTextView = (TextView) findViewById(R.id.maxTextView);
        tryNumTextView = (TextView) findViewById(R.id.tryNumTextView);

        buttonGuess.setEnabled(false);
        setupGame();
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSettings.setEnabled(false);
                startSettings();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupGame();
            }
        });
        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals(""))
                    guess(Integer.parseInt(editText.getText().toString()));
            }
        });
    }

    public void startSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("min", String.valueOf(minNumber));
        intent.putExtra("max", String.valueOf(maxNumber));
        startActivityForResult(intent, 1);
    }

    private void setupGame() {
        tryNum = 0;
        minTextView.setText("min" + System.getProperty("line.separator") + String.valueOf(minNumber));
        maxTextView.setText("max" + System.getProperty("line.separator") + String.valueOf(maxNumber));
        infoTextView.setText("Guess the number");
        tryNumTextView.setText("Tries: " + String.valueOf(tryNum));
        Random random = new Random();
        randomNumber = random.nextInt((maxNumber - minNumber) + 1) + minNumber;
        buttonGuess.setEnabled(true);
    }

    private void guess(int guessNum) {
        buttonGuess.setEnabled(false);
        editText.setText("");
        tryNum++;
        tryNumTextView.setText("Tries: " + String.valueOf(tryNum));
        if(guessNum == randomNumber) {
            infoTextView.setText("Victory !!!");
        }
        else if(guessNum < randomNumber) {
            infoTextView.setText("Try higher");
            buttonGuess.setEnabled(true);
        }
        else if(guessNum > randomNumber) {
            infoTextView.setText("Try lower");
            buttonGuess.setEnabled(true);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                minNumber = data.getIntExtra("min", 0);
                maxNumber = data.getIntExtra("max", 0);
                setupGame();
            }
        }
        buttonSettings.setEnabled(true);
    }
}
