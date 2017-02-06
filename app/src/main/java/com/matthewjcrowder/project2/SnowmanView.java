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
    public SnowmanView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int x = 500;
    int y = 500;
    int r = 100;
    Paint mPaint = new Paint();
    @Override
    public void onDraw(Canvas c)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        c.drawCircle(height/2,width/2,r,mPaint);
    }
}
