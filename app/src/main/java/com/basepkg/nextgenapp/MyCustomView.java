package com.basepkg.nextgenapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyCustomView extends View {

    private static final String TAG = "MyRectangleTAG_";

    private static final int ORIGIN = 0;

    private Paint paint;

    private int height;
    private int width;
    private int halfHeight;
    private int halfWidth;

    public MyCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        halfHeight = height / 2;
        halfWidth = width / 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Corners
        canvas.drawLine(ORIGIN, ORIGIN, width, ORIGIN, paint);
        canvas.drawLine(ORIGIN, ORIGIN, ORIGIN, height, paint);
        canvas.drawLine(width, ORIGIN, width, height, paint);
        canvas.drawLine(ORIGIN, height, width, height, paint);

        // Cross
        canvas.drawLine(ORIGIN, halfHeight, width, halfHeight, paint);
        canvas.drawLine(halfWidth, ORIGIN, halfWidth, height, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        processTouchEvent(x, y);
        return super.onTouchEvent(event);
    }

    private void processTouchEvent(float x, float y) {
        String area;

        if (x < halfWidth && y < halfHeight) {
            area = "1";
        } else if (x > halfWidth && y <halfHeight) {
            area = "2";
        } else if (x < halfWidth && y > halfHeight) {
            area = "3";
        } else {
            area = "4";
        }
        Toast.makeText(getContext(), "Section " + area, Toast.LENGTH_SHORT).show();
    }
}