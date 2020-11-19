package com.example.kview.demo;


import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

public class InputNumLengthFilter2 implements InputFilter {

    private static final String TAG = "InputNumLengthFilter";


    public InputNumLengthFilter2() {

    }

    /**
     *
     * @param source 新输入的字符串内容      string Char
     * @param start 新输入的字符串的开始长度  0
     * @param end 新输入的字符串的结束位置   string.length
     * @param dest  输入框原本的内容   eg: 569
     * @param dstart    输入框追加信息的位置(即是光标位置)  eg: 569 -> 3
     * @param dend      输入框追加信息的位置(即是光标位置)  eg: 569 -> 3
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Log.d(TAG, "filter:  这里是追加字符串: " + source + " - - - start: " + start + " - - - end: " + end);
        Log.d(TAG, "filter:  这里是原本字符串: " + dest.toString() + " - - - dstart: " + dstart + " - - - dend: " + dend);
        // 删除等特殊字符，直接返回
        if (nullFilter(source)) return null;
        String dValue = dest.toString();
        if(!source.toString().matches("[0-9.,]")){
            return "";
        }

        if( source.toString().contains(".")&& dest.toString().contains(".")){
            return "";
        }
        if(source.toString().startsWith(".") && dest.toString().trim().length() == 0){
            return "";
        }

        if((dValue + source).length() % 3 == 0){
            return  source + ",";
        }


        return null;
    }

    //  第一个参数是小数部分的位数，第二个参数是总长度（包括小数点）

    private boolean nullFilter(CharSequence source) {
        return source.toString().isEmpty();
    }
}

