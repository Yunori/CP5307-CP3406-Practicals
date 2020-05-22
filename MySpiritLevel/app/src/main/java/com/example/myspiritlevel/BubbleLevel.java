package com.example.myspiritlevel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BubbleLevel implements SensorEventListener {
    static private final double GRAVITY = 9.81d;
    static private final double MIN_DEGREE = -10d;
    static private final double MAX_DEGREE = 10d;

    private TextView userMessage;
    private TextView positionsMessage;
    private Canvas canvasY;
    private Canvas canvasX;
    private Rect rectangleY;
    private Rect rectangleX;
    private Paint paintRectangle;
    private Bitmap bitmapY;
    private Bitmap bitmapX;
    private ImageView mImageViewY;
    private ImageView mImageViewX;
    private Paint paintLine;

    private double thetaX;
    private double thetaY;


    BubbleLevel(Context ctx) {
        mImageViewY = ((Activity) ctx).findViewById(R.id.iv);
        mImageViewX = ((Activity) ctx).findViewById(R.id.ix);

        userMessage = ((Activity) ctx).findViewById(R.id.user_message);
        positionsMessage = ((Activity) ctx).findViewById(R.id.positions);

        bitmapY = Bitmap.createBitmap(
                50,
                400,
                Bitmap.Config.ARGB_8888
        );

        bitmapX = Bitmap.createBitmap(
                400,
                50,
                Bitmap.Config.ARGB_8888
        );

        canvasY = new Canvas(bitmapY);
        canvasY.drawColor(Color.LTGRAY);

        canvasX = new Canvas(bitmapX);
        canvasY.drawColor(Color.LTGRAY);

        rectangleY = new Rect(0,0, canvasY.getWidth(), canvasY.getHeight());
        rectangleX = new Rect(0,0, canvasX.getWidth(), canvasX.getHeight());

        paintRectangle = new Paint();
        paintRectangle.setStyle(Paint.Style.FILL);
        paintRectangle.setColor(Color.YELLOW);
        paintRectangle.setAntiAlias(true);

        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.FILL);
        paintLine.setColor(Color.BLACK);
        paintLine.setAntiAlias(true);
        paintLine.setStrokeWidth(2f);

        thetaX = 0d;
        thetaY = 0d;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        double gx = sensorEvent.values[0] > GRAVITY ? GRAVITY : sensorEvent.values[0];
        double gy = sensorEvent.values[1] > GRAVITY ? GRAVITY : sensorEvent.values[1];
        double gz = sensorEvent.values[2];

        gx = Math.max(gx, -GRAVITY);
        gy = Math.max(gy, -GRAVITY);

        thetaX = Math.toDegrees(Math.asin(gy/GRAVITY));
        thetaY = Math.toDegrees(Math.asin(gx/GRAVITY));

        canvasY.drawRect(rectangleY, paintRectangle);
        canvasX.drawRect(rectangleX, paintRectangle);

        canvasY.drawLine(0, getLineLocation(MAX_DEGREE), canvasY.getWidth(), getLineLocation(MAX_DEGREE), paintLine);
        canvasY.drawLine(0, getLineLocation(MIN_DEGREE), canvasY.getWidth(), getLineLocation(MIN_DEGREE), paintLine);

        canvasX.drawLine(getLineLocation(MAX_DEGREE), 0, getLineLocation(MAX_DEGREE), canvasX.getHeight(), paintLine);
        canvasX.drawLine(getLineLocation(MIN_DEGREE), 0, getLineLocation(MIN_DEGREE), canvasX.getHeight(), paintLine);

        canvasY.drawLine(0, getLineLocation(thetaY), canvasY.getWidth(), getLineLocation(thetaY), paintLine);
        canvasX.drawLine(getLineLocation(thetaX), 0, getLineLocation(thetaX), canvasX.getHeight(), paintLine);


        mImageViewY.setImageBitmap(bitmapY);
        mImageViewX.setImageBitmap(bitmapX);
        positionsMessage.setText("Angle X: " + String.valueOf((int) thetaX) + "   Angle Y: " + String.valueOf((int) thetaY));
        if (thetaX >= MIN_DEGREE && thetaX <= MAX_DEGREE && thetaY >= MIN_DEGREE && thetaY <= MAX_DEGREE && gz > 0d) {
            userMessage.setBackgroundColor(Color.GREEN);
            userMessage.setText(R.string.phone_centered);
        } else {
            userMessage.setBackgroundColor(Color.RED);
            userMessage.setText(R.string.phone_not_centered);
        }
    }

    private int getLineLocation(double angle){
        double value =  ( - angle + 90d) * 2.222d;
        return (int) value;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public double[] getPhoneAngles() {
        return new double[]{thetaX, thetaY};
    }
}

