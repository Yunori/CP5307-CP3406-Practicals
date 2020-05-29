package com.example.photoeditor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.skydoves.colorpickerview.preference.ColorPickerPreferenceManager;
import java.util.Objects;

public class SettingsFragment extends Fragment {

    private Button backButton;
    private ActionBar bar;
    private TextView textView;
    private SharedPreferences sharedPreferences;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        backButton = getActivity().findViewById(R.id.buttonBack);
        textView = getActivity().findViewById(R.id.textView);
        ColorPickerView cpv = getActivity().findViewById(R.id.colorPickerView);
        ColorPickerPreferenceManager manager = ColorPickerPreferenceManager.getInstance(getActivity());
        sharedPreferences = getActivity().getSharedPreferences("PRES_FILE", 0);
        manager.setColor("MyColorPicker", sharedPreferences.getInt("color", 111111));
        cpv.setPreferenceName("MyColorPicker");
        SeekBar strokeSeekBar = getActivity().findViewById(R.id.seekbar);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        strokeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setLabelText(progress);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("STROKE_WIDTH", progress);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        cpv.setColorListener(new ColorEnvelopeListener() {
            @Override
            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("RED", envelope.getArgb()[1]);
                editor.putInt("GREEN", envelope.getArgb()[2]);
                editor.putInt("BLUE", envelope.getArgb()[3]);
                editor.apply();
                backButton.setBackgroundColor(envelope.getColor());
                bar.setBackgroundDrawable(new ColorDrawable(envelope.getColor()));
                textView.setText("#" + envelope.getHexCode());
            }
        });

        int strokeWidth = Math.round(MainActivityFragment.strokeWidth);
        strokeSeekBar.setProgress(strokeWidth);
        setLabelText(strokeWidth);
    }

    private void setLabelText(int progress) {
        TextView textView = getActivity().findViewById(R.id.seekbar_label);
        textView.setText("Stroke Width (" + String.valueOf(progress) + ")");
    }
}
