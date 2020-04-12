package com.example.mycalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private MaterialButton buttonZero, buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix,
            buttonSeven, buttonEight, buttonNine, buttonDot, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonClear, buttonEqual, buttonReturn;
    private TextView resultTextView, tempTextView;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private double val1 = Double.NaN;
    private double val2;
    private char ACTION;
    NumberFormat decimalFormat;
    String tempChar = "";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        decimalFormat = new DecimalFormat("#.##########");
        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.zero);
                tempTextView.setText(tempChar);
            }
        });
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.one);
                tempTextView.setText(tempChar);
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.two);
                tempTextView.setText(tempChar);
            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.three);
                tempTextView.setText(tempChar);
            }
        });
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.four);
                tempTextView.setText(tempChar);
            }
        });
        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.five);
                tempTextView.setText(tempChar);
            }
        });
        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.six);
                tempTextView.setText(tempChar);
            }
        });
        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.seven);
                tempTextView.setText(tempChar);
            }
        });
        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.eight);
                tempTextView.setText(tempChar);
            }
        });
        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempChar = tempTextView.getText().toString() + getString(R.string.nine);
                tempTextView.setText(tempChar);
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tempTextView.getText().toString().equals("") && !tempTextView.getText().toString().contains(".") && ((tempTextView.getText().toString().charAt(0) == '-' && tempTextView.getText().toString().length() > 1) || tempTextView.getText().toString().charAt(0) != '-')) {
                    Log.e("kek", "kkkk");
                    tempChar = tempTextView.getText().toString() + getString(R.string.dot);
                    tempTextView.setText(tempChar);
                }
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempTextView.getText().toString().matches("-?\\d+(?:\\.\\d+)?")) {
                    compute();
                    ACTION = ADDITION;
                    tempChar = decimalFormat.format(val1) + getString(R.string.add);
                    resultTextView.setText(tempChar);
                    tempTextView.setText(null);
                }
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempTextView.getText().toString().equals("") || tempTextView.getText().toString().matches("-?\\d+(?:\\.\\d+)?")) {
                    if(tempTextView.getText().toString().equals(""))
                        tempTextView.setText("-");
                    else{
                        compute();
                        ACTION = SUBTRACTION;
                        tempChar = decimalFormat.format(val1) + getString(R.string.sub);
                        resultTextView.setText(tempChar);
                        tempTextView.setText(null);
                    }
                }
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempTextView.getText().toString().matches("-?\\d+(?:\\.\\d+)?")) {
                    compute();
                    ACTION = MULTIPLICATION;
                    tempChar = decimalFormat.format(val1) + getString(R.string.mul);
                    resultTextView.setText(tempChar);
                    tempTextView.setText(null);
                }
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempTextView.getText().toString().matches("-?\\d+(?:\\.\\d+)?")) {
                    compute();
                    ACTION = DIVISION;
                    tempChar = decimalFormat.format(val1) + getString(R.string.div);
                    resultTextView.setText(tempChar);
                    tempTextView.setText(null);
                }
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempTextView.getText().toString().matches("-?\\d+(?:\\.\\d+)?")) {
                    compute();
                    resultTextView.setText(decimalFormat.format(val1));
                    val1 = Double.NaN;
                    ACTION = '0';
                    tempTextView.setText(null);
                }
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Double.NaN;
                val2 = Double.NaN;
                tempTextView.setText(null);
                resultTextView.setText("0");
            }
        });
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempTextView.getText().length() > 0) {
                    CharSequence currentText = tempTextView.getText();
                    tempTextView.setText(currentText.subSequence(0, currentText.length() - 1));
                }
            }
        });
    }

    private void setupUIViews() {
        buttonZero = findViewById(R.id.buttonZero);
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonFour = findViewById(R.id.buttonFour);
        buttonFive = findViewById(R.id.buttonFive);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSeven = findViewById(R.id.buttonSeven);
        buttonEight = findViewById(R.id.buttonEight);
        buttonNine = findViewById(R.id.buttonNine);
        buttonDot = findViewById(R.id.buttonDot);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSubstract);
        buttonMul = findViewById(R.id.buttonMultiply);
        buttonDiv = findViewById(R.id.buttonDivide);
        buttonClear = findViewById(R.id.buttonClear);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonReturn = findViewById(R.id.buttonReturn);
        resultTextView = findViewById(R.id.result);
        tempTextView = findViewById(R.id.temp);
    }

    private void compute() {
        if(!Double.isNaN(val1)) {
                val2 = Double.parseDouble(tempTextView.getText().toString());
                tempTextView.setText(null);
                if (ACTION == ADDITION)
                    val1 = this.val1 + val2;
                else if (ACTION == SUBTRACTION)
                    val1 = this.val1 - val2;
                else if (ACTION == MULTIPLICATION)
                    val1 = this.val1 * val2;
                else if (ACTION == DIVISION)
                    val1 = this.val1 / val2;
        }
        else {
                val1 = Double.parseDouble(tempTextView.getText().toString());
        }
    }
}
