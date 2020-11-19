package com.example.kview.demo;


import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

public class InputNumLengthFilter implements InputFilter {

    private static final String TAG = "InputNumLengthFilter";

    private int maxPoint;
    private int maxInteger;
    public InputNumLengthFilter(int maxPoint, int maxInteger) {
        this.maxPoint = maxPoint;
        this.maxInteger = maxInteger;
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
       int maxLength = maxInteger + maxPoint + 1;
        // 删除等特殊字符，直接返回
        if (nullFilter(source)) return null;
        String dValue = dest.toString();
        if(!source.toString().matches("[0-9.]")){
            return "";
        }

        if( source.toString().contains(".")&& dest.toString().contains(".")){
            return "";
        }
        if(source.toString().startsWith(".") && dest.toString().trim().length() == 0){
            return "";
        }

         // eg :  12345.89  76
        String[] splitArray = dValue.split("\\.");//在点前后分开两段

        if (splitArray.length > 0) {
            String intValue = splitArray[0];
            int errorIndex = dValue.indexOf(".");
            if (errorIndex == -1) {
                errorIndex = dValue.length();
            }
            if (intValue.length() >= maxLength - maxPoint - 1 && dstart <= errorIndex) {
                Log.d(TAG, "filter:  这里是判断条件: "  + (intValue.length()) + " - -- " +  (maxLength - maxPoint - 1) + "----" + ( dstart <= errorIndex) );
                Log.d(TAG, "filter:  这里是判断条件@2: "  + (intValue.length()) + " - -- " +  maxPoint + "----" + ( dstart <= errorIndex) );

                if (".".equals(source.toString())) {
                    // 这里return null 则和return "." 效果一样。 则可以追加一个 "." 字符
                    return null;
                }
                return "";
            }
        }

        if (splitArray.length > 1 && dstart == dValue.length()) {
            String dotValue = splitArray[1];
            int diff = dotValue.length() + 1 - maxPoint;
            if (diff > 0) {
                try {
                    return source.subSequence(start, end - diff);
                } catch (IndexOutOfBoundsException e) {
                    return source;
                }
            }
        }

        if (dest.length() == maxLength - 1 && ".".equals(source.toString())) {
            return "";
        }

        // 超出最大位数时，不再追加字符串
        if (dest.length() >= maxLength) {
            return "";
        }
        return null;
    }

    //  第一个参数是小数部分的位数，第二个参数是总长度（包括小数点）

    private boolean nullFilter(CharSequence source) {
        return source.toString().isEmpty();
    }
}
