package com.example.kview.demo;

import android.text.InputFilter;
import android.widget.EditText;

public class ViewHelper {


    /**
     * 让一个输入框只能输入指定位数小数 和整数位
     *
     * @param editText   EditText
     * @param maxInteger 最大整数位数
     * @param maxPoint   最大小数位数
     *  create by ggband
     */
    public static void setPricePointWithInteger(final EditText editText, final int maxPoint, final int maxInteger, InputFilter... inputFilters) {
        if (inputFilters == null || inputFilters.length == 0) {
            editText.setFilters(new InputFilter[]{new InputNumLengthFilter(maxPoint, maxInteger)});
        } else {
            InputFilter[] newInputFilters = new InputFilter[inputFilters.length + 1];
            System.arraycopy(inputFilters, 0, newInputFilters, 0, inputFilters.length);
            newInputFilters[inputFilters.length] = new InputNumLengthFilter(maxPoint, maxInteger);
            editText.setFilters(newInputFilters);
        }

    }


    public static void setPricePointWithInteger2(final EditText editText,  InputFilter... inputFilters) {

            InputFilter[] newInputFilters = new InputFilter[inputFilters.length + 1];
            System.arraycopy(inputFilters, 0, newInputFilters, 0, inputFilters.length);
            newInputFilters[inputFilters.length] = new InputNumLengthFilter2();
            editText.setFilters(newInputFilters);


    }



}
