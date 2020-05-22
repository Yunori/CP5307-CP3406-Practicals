package com.example.myspiritlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    BubbleLevel bubbleLevel;
    Ball ball;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ball = new Ball(this);
        bubbleLevel = new BubbleLevel(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(bubbleLevel, sensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(ball, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(bubbleLevel, sensor);
        sensorManager.unregisterListener(ball, sensor);
    }
}
