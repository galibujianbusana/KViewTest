package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

public class StringsUtil {

    private static final String TAG = "StringsUtil";

    /**
     * 格式化钱数，每三位以逗号隔开
     */
    public static String NumberJionComma(String number) {
        StringBuffer sb = new StringBuffer(number);
        int location = sb.length() - 3;
        if (number.contains(".")) {
            location = sb.length() - 6;
        }
        for (int i = location; i > 0; i = i - 3) {
            sb.insert(i, ",");
        }
        return sb.toString();
    }
    public static String addComma(String str){

        boolean neg = false;
        if (str.startsWith("-")){  //处理负数
            str = str.substring(1);
            neg = true;
        }
        String tail = null;
        if (str.indexOf('.') != -1){ //处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4){
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg){
            sb.insert(0, '-');
        }
        if (tail != null){
            sb.append(tail);
        }
        return sb.toString();
    }


    public static void log(Activity context){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;  // 宽度（PX）
        Log.d(TAG, "log:w: - - > " + width);
        int height = metric.heightPixels;  // 高度（PX）
        Log.d(TAG, "log:h: - - > " + height);

        float density = metric.density;  // 密度（0.75 / 1.0 / 1.5）
        Log.d(TAG, "log:density: - - > " + density);

        int densityDpi = metric.densityDpi;  // 密度DPI（120 / 160 / 240）

        Log.d(TAG, "log:densityDpi: - - > " + densityDpi);

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        //因为有的dpi为160的1.5倍，所以计算完后，为了没有小数，加0.5
        Log.d(TAG, "dip2px: " + scale);
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
