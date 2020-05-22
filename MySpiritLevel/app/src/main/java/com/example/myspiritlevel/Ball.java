package com.example.myspiritlevel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class Ball implements SensorEventListener {

    private Display display;
    private RectF mRectF;
    private final int RADIUS = 25;
    private int mXCenter;
    private int mYCenter;
    private Bitmap mainBitmap;
    private Canvas mainCanvas;
    private ImageView mImageViewXY;
    private Paint mPaint;
    Ball(Context ctx) {
        mImageViewXY = ((Activity) ctx).findViewById(R.id.ixy);
        display = ((WindowManager)ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int mWidthScreen = display.getWidth();
        int mHeightScreen = display.getHeight();
        ViewGroup.LayoutParams params = mImageViewXY.getLayoutParams();
        params.width = mHeightScreen /2;
        params.height = mHeightScreen /2;
        mImageViewXY.setLayoutParams(params);
        mainBitmap = Bitmap.createBitmap(
                mHeightScreen /2,
                mHeightScreen /2,
                Bitmap.Config.ARGB_8888
        );
        mXCenter = (int)(mWidthScreen * 0.5);
        mYCenter = (int)(mWidthScreen * 0.5);
        mainCanvas = new Canvas(mainBitmap);
        mainCanvas.drawColor(Color.WHITE);
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setAlpha(192);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float GRAVITY = 9.8f;
        double gx = Math.min(event.values[0], GRAVITY);
        double gy = Math.min(event.values[1], GRAVITY);

        gx = Math.max(gx, -GRAVITY);
        gy = Math.max(gy, -GRAVITY);

        double thetaX = Math.toDegrees(Math.asin(gy / GRAVITY));
        double thetaY = Math.toDegrees(Math.asin(gx / GRAVITY));

        mXCenter = (int) -thetaX;
        mYCenter = (int) -thetaY;

        mXCenter = mXCenter*5;
        mYCenter = mYCenter*5;
        if(mXCenter > mainCanvas.getWidth()/2 - RADIUS) {
            mXCenter = mainCanvas.getWidth()/2 - RADIUS;
            Log.e("mxSup", String.valueOf(-mainCanvas.getWidth()/2));
        }
        if(mXCenter < -mainCanvas.getWidth()/2 + RADIUS) {
            mXCenter = -mainCanvas.getWidth()/2 + RADIUS;
            Log.e("mxSup", String.valueOf(-mainCanvas.getWidth()/2));
        }
        if(mYCenter > mainCanvas.getHeight()/2 - RADIUS) {
            mYCenter = mainCanvas.getHeight()/2 - RADIUS;
            Log.e("mxSup", String.valueOf(-mainCanvas.getHeight()/2));
        }
        if(mYCenter < -mainCanvas.getHeight()/2 + RADIUS) {
            mYCenter = -mainCanvas.getHeight()/2 + RADIUS;
            Log.e("mxSup", String.valueOf(-mainCanvas.getHeight()/2));
        }

        mRectF.set(mainCanvas.getWidth()/2 + mXCenter - RADIUS, mainCanvas.getHeight()/2 + mYCenter - RADIUS, mainCanvas.getWidth()/2 + mXCenter + RADIUS, mainCanvas.getHeight()/2 + mYCenter + RADIUS);
        mainCanvas.drawColor(Color.WHITE);
        mainCanvas.drawOval(mRectF, mPaint);
        mImageViewXY.setImageBitmap(mainBitmap);
    }
}
