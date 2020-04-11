package com.example.mobilecomputing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.math.BigDecimal;

import static android.graphics.Color.parseColor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonReset, buttonOther, buttonSettings;
    TextView result;
    ActionBar bar;
    int otherStatus = 0;
    int color = parseColor("#00BCD4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupEvents();
    }

    private void setupUI() {
        result = findViewById(R.id.result);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonReset = findViewById(R.id.buttonReset);
        buttonOther = findViewById(R.id.buttonOther);
        buttonSettings = findViewById(R.id.buttonSettings);
        bar = getSupportActionBar();
    }

    private void setupEvents() {
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonOne.setTag(1);
        buttonTwo.setTag(2);
        buttonThree.setTag(3);
        buttonFour.setTag(4);
        buttonFive.setTag(5);
        buttonSix.setTag(6);
        buttonSeven.setTag(7);
        buttonEight.setTag(8);
        buttonNine.setTag(9);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("0");
            }
        });

        buttonOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!result.getText().toString().equals(""))
                    changeButtons();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSettings.setEnabled(false);
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                intent.putExtra("color", color);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void changeButtons() {
        if(otherStatus == 0) {
            buttonOne.setText("1/2");
            buttonTwo.setText("1/3");
            buttonThree.setText("1/4");
            buttonFour.setText("1/5");
            buttonFive.setText("1/6");
            buttonSix.setText("1/7");
            buttonSeven.setText("1/8");
            buttonEight.setText("1/9");
            buttonNine.setText("1/10");
            otherStatus = 1;
        }
        else {
            buttonOne.setText("1");
            buttonTwo.setText("2");
            buttonThree.setText("3");
            buttonFour.setText("4");
            buttonFive.setText("5");
            buttonSix.setText("6");
            buttonSeven.setText("7");
            buttonEight.setText("8");
            buttonNine.setText("9");
            otherStatus = 0;
        }
    }

    @Override
    public void onClick(View v) {
        if(otherStatus == 0) {
            result.setText(String.valueOf(new BigDecimal((String) result.getText()).add(BigDecimal.valueOf((Integer) v.getTag()))));
        }
        else {
            result.setText(String.valueOf(new BigDecimal((String) result.getText()).add(BigDecimal.valueOf(1).divide((BigDecimal.valueOf((Integer) v.getTag()+1)), 3, 0))));
            changeButtons()
;        }
    }

    private void changeColor() {
        buttonOne.setBackgroundColor(color);
        buttonTwo.setBackgroundColor(color);
        buttonThree.setBackgroundColor(color);
        buttonFour.setBackgroundColor(color);
        buttonFive.setBackgroundColor(color);
        buttonSix.setBackgroundColor(color);
        buttonSeven.setBackgroundColor(color);
        buttonEight.setBackgroundColor(color);
        buttonNine.setBackgroundColor(color);
        buttonOther.setBackgroundColor(color);
        buttonSettings.setBackgroundColor(color);
        buttonReset.setBackgroundColor(color);
        bar.setBackgroundDrawable(new ColorDrawable(color));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                color = data.getIntExtra("color", Color.parseColor("#00BCD4"));
                changeColor();
            }
        }
        buttonSettings.setEnabled(true);
    }
}
