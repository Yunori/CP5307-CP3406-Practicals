package com.example.mydrumkit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class DisplayView extends View {

    private Paint paint;
    private String displayMessage = "Drum Kit";

    public DisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        PointF size = getSize();
        canvas.drawText(displayMessage, size.x / 2f, size.y / 2f, paint);
    }

    public void setDisplayMessage(String message) {
        displayMessage = message;
        Log.e("3", message);
        this.invalidate();
    }

    public PointF getSize() {
        return (new PointF(getWidth(), getHeight()));
    }

    public Position getPosition(float x, float y) {
        PointF size = getSize();

        if (x <= size.x / 5f && y <= size.y / 3f)
            return Position.CRASH_ONE;
        else if (x > size.x / 5f && x <= size.x / 5f * 2 && y <= size.y / 3f)
            return Position.SPLASH;
        else if (x > size.x / 5f * 3 && x <= size.x / 5f * 4 && y <= size.y / 3f)
            return Position.CRASH_TWO;
        else if (x > size.x / 5f * 4 && y <= size.y / 3f)
            return Position.RIDE;
        else if (y > size.y / 3f && x <= size.x / 5f && y <= size.y / 3f * 2)
            return Position.CHH;
        else if (x > size.x / 5f && y > size.y / 3f && x <= size.x / 5f * 2 && y <= size.y / 3f * 2)
            return Position.TOM_ONE;
        else if (x > size.x / 5f * 3 && y > size.y / 3f && x <= size.x / 5f * 4 && y <= size.y / 3f * 2)
            return Position.TOM_THREE;
        else if (y > size.y / 3f * 2 && x <= size.x / 5f)
            return Position.OHH;
        else if (y > size.y / 3f * 2 && x > size.x / 5f && x <= size.x / 5f * 2)
            return Position.KICK_ONE;
        else if (y > size.y / 3f * 2 && x > size.x / 5f * 3 && x <= size.x / 5f * 4)
            return Position.KICK_TWO;
        else if (x > size.x / 5f * 2 && y > size.y / 3f * 0.5 && x <= size.x / 5f * 3 && y <= size.y / 3f * 1.5)
            return Position.TOM_TWO;
        else if (x > size.x / 5f * 2 && y > size.y / 3f * 1.5 && x <= size.x / 5f * 3 && y <= size.y / 3f * 2.5)
            return Position.SNARE;
        else if (x > size.x / 5f * 4 && y > size.y / 3f * 1.5 && y <= size.y / 3f * 2.5)
            return Position.FLOOR;
        return Position.NONE;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}