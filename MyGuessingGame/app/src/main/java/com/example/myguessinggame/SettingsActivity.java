package com.example.myguessinggame;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    private TextView minValue, maxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        minValue = (TextView) findViewById(R.id.minValue);
        maxValue = (TextView) findViewById(R.id.maxValue);
        final Button buttonApply = (Button) findViewById(R.id.buttonApply);
        final Button buttonBack = (Button) findViewById(R.id.buttonBack);
        final TextView check = (TextView) findViewById(R.id.checkValues);
        final SeekBar skmin=(SeekBar) findViewById(R.id.seekBarMin);
        final SeekBar skmax=(SeekBar) findViewById(R.id.seekBarMax);
        buttonApply.setEnabled(true);
        minValue.setText(getIntent().getStringExtra("min"));
        maxValue.setText(getIntent().getStringExtra("max"));
        skmin.setProgress(Integer.parseInt(getIntent().getStringExtra("min")));
        skmax.setProgress(Integer.parseInt(getIntent().getStringExtra("max")));
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                if(skmin.getProgress() <= skmax.getProgress()) {
                    returnIntent.putExtra("min", skmin.getProgress());
                    returnIntent.putExtra("max", skmax.getProgress());
                    setResult(Activity.RESULT_OK, returnIntent);
                }
                else {
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                }
                finish();
            }
        });
        skmin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                minValue.setText(String.valueOf(progress));
                if(skmin.getProgress() > skmax.getProgress()) {
                    buttonApply.setEnabled(false);
                    check.setText("Values are incorrect");
                }
                else {
                    buttonApply.setEnabled(true);
                    check.setText("Values are correct");
                }
            }
        });
        skmax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                maxValue.setText(String.valueOf(progress));
                if(skmin.getProgress() > skmax.getProgress()) {
                    buttonApply.setEnabled(false);
                    check.setText("Values are incorrect");
                }
                else {
                    buttonApply.setEnabled(true);
                    check.setText("Values are correct");
                }
            }
        });
    }
}
