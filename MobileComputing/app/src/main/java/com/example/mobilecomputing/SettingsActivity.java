package com.example.mobilecomputing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.preference.ColorPickerPreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ActionBar bar = getSupportActionBar();
        final Button back = (MaterialButton) findViewById(R.id.buttonBack);
        final TextView textView = (TextView) findViewById(R.id.textView);
        final ColorPickerView cpv = (ColorPickerView) findViewById(R.id.colorPickerView);
        ColorPickerPreferenceManager manager = ColorPickerPreferenceManager.getInstance(this);
        manager.setColor("MyColorPicker", getIntent().getIntExtra("color", 0));
        cpv.setPreferenceName("MyColorPicker");

        cpv.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                back.setBackgroundColor(envelope.getColor());
                bar.setBackgroundDrawable(new ColorDrawable(envelope.getColor()));
                textView.setText("#" + envelope.getHexCode());
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                    returnIntent.putExtra("color", cpv.getColor());
                    setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
