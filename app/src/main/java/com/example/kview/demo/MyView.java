package com.example.kview.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path pathBg = new Path();
        Paint paintBg = new Paint();
        float preFloatX = 0, preFloatY = 0;


            pathBg.reset();
            paintBg.reset();//重置
            paintBg.setColor(Color.LTGRAY);
            paintBg.setStyle(Paint.Style.FILL);//设置空心
            Path path1=new Path();
            path1.moveTo(180, 200);
            path1.lineTo(200, 200);
            path1.lineTo(210, 210);
            path1.lineTo(200, 220);
            path1.lineTo(180, 220);
            path1.lineTo(170, 210);
            path1.close();//封闭
            canvas.drawPath(path1, paintBg);


    }
}
