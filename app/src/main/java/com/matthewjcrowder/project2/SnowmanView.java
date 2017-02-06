package com.matthewjcrowder.project2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by mcrowder65 on 2/6/17.
 */

public class SnowmanView extends View {

    Snowball bottom;
    Snowball middle;
    Snowball top;

    public SnowmanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        Paint paint = new Paint();
        bottom = new Snowball(width/2, height / 2, 100, paint);
        middle = new Snowball(width / 2, height / 2 - 165, 75, paint);
        top = new Snowball(width / 2, height / 2 - 275, 50, paint);
    }


    @Override
    public void onDraw(Canvas c)
    {
        drawCircle(bottom, c);
        drawCircle(middle, c);
        drawCircle(top, c);
    }

    private void drawCircle(Snowball ball, Canvas c) {
        c.drawCircle(ball.x, ball.y, ball.radius, ball.paint);
    }
    class Snowball {
        public int x;
        public int y;
        public int radius;
        public Paint paint;
        Snowball(int x, int y, int radius, Paint paint) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.paint = new Paint(paint);
        }
    }
}
