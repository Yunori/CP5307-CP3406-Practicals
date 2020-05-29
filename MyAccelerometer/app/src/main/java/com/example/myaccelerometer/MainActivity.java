package com.example.myaccelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor accelerometer;
    TextView xValue,yValue,zValue, gValue, gxValue, gyValue, gzValue, laValue, laxValue, layValue, lazValue, angleXValue, angleYValue, angleZValue;
    BigDecimal bd;
    final double alpha = 0.8;
    double[] gravity = new double[3];
    double[] linear_acceleration = new double[3];
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        xValue = findViewById(R.id.tvXValue);
        yValue = findViewById(R.id.tvYValue);
        zValue = findViewById(R.id.tvZValue);
        gValue = findViewById(R.id.tvGravityValue);
        gxValue = findViewById(R.id.tvGravityXValue);
        gyValue = findViewById(R.id.tvGravityYValue);
        gzValue = findViewById(R.id.tvGravityZValue);
        laValue = findViewById(R.id.tvLinearValue);
        laxValue = findViewById(R.id.tvLinearXValue);
        layValue = findViewById(R.id.tvLinearYValue);
        lazValue = findViewById(R.id.tvLinearZValue);
        angleXValue = findViewById(R.id.angleXValue);
        angleYValue = findViewById(R.id.angleYValue);
        angleZValue = findViewById(R.id.angleZValue);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double gx = event.values[0] > 9.80 ? 9.80 : event.values[0];
        double gy = event.values[1] > 9.80 ? 9.80 : event.values[1];
        double gz = event.values[2] > 9.80 ? 9.80 : event.values[1];

        gx = Math.max(gx, -9.80);
        gy = Math.max(gy, -9.80);
        gz = Math.max(gz, -9.80);

        double thetaX = Math.toDegrees(Math.asin(gy / 9.80));
        double thetaY = Math.toDegrees(Math.asin(gx / 9.80));
        double thetaZ = Math.toDegrees(Math.asin(gz / 9.80));

        bd = new BigDecimal(event.values[0]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        xValue.setText("X Value: "+ bd);
        bd = new BigDecimal(event.values[1]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        yValue.setText("Y Value: "+ bd);
        bd = new BigDecimal(event.values[2]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        zValue.setText("Z Value: "+ bd);
        bd = new BigDecimal(Math.sqrt(event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2])/9.80);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        gValue.setText("Gravity force:" + bd);
        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        bd = new BigDecimal(gravity[0]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        gxValue.setText("Gravity force X:" + bd);
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        bd = new BigDecimal(gravity[1]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        gyValue.setText("Gravity force Z:" + bd);
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
        bd = new BigDecimal(gravity[2]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        gzValue.setText("Gravity force Z:" + bd);
        bd = new BigDecimal(Math.sqrt((event.values[0] - gravity[0]) * (event.values[0] - gravity[0]) + (event.values[1] - gravity[1]) * (event.values[1] - gravity[1]) + (event.values[2] - gravity[2]) * (event.values[2] - gravity[2])));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        laValue.setText("Linear Acceleration:" + bd);
        linear_acceleration[0] = event.values[0] - gravity[0];
        bd = new BigDecimal(linear_acceleration[0]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        laxValue.setText("Linear Acceleration X:" + bd);
        linear_acceleration[1] = event.values[1] - gravity[1];
        bd = new BigDecimal(linear_acceleration[1]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        layValue.setText("Linear Acceleration Y:" + bd);
        linear_acceleration[2] = event.values[2] - gravity[2];
        bd = new BigDecimal(linear_acceleration[2]);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        lazValue.setText("Linear Acceleration Z:" + bd);
        bd = new BigDecimal(thetaX);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        angleXValue.setText("Angle X:" + bd);
        bd = new BigDecimal(thetaY);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        angleYValue.setText("Angle Y:" + bd);
        bd = new BigDecimal(thetaZ);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        angleZValue.setText("Angle Z:" + bd);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(MainActivity.this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(MainActivity.this,accelerometer);
    }
}
