package com.example.test;

import android.widget.TextView;

public class StringsUtil {

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


}
