package com.example.kview.demo;


import android.app.Activity;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author RobX (robxyy@gmail.com)
 */
public final class Strings {

    private static final Pattern ALPHABET_NUMBER_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$");

    private Strings() {
        // No instances.
    }

    public static boolean isBlank(CharSequence string) {
        return (string == null || string.toString().trim().length() == 0);
    }

    public static String replaceBlank(String string) {
        if (string != null) {
            string.replaceAll("\\s", "");
        }
        return string;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() <= 0;
    }

    public static double parseDouble(String string) {
        double result = 0;
        if (!Strings.isBlank(string)) {
            try {
                result = Double.parseDouble(string);
            } catch (NumberFormatException e) {
                e.printStackTrace();

            }
        }
        return result;
    }

    public static float parseFloat(String string) {
        float result = 0;
        if (!Strings.isBlank(string)) {
            try {
                result = Float.parseFloat(string);
            } catch (NumberFormatException e) {
                e.printStackTrace();

            }
        }
        return result;
    }

    public static int parseInt(String string) {
        int result = 0;
        if (!Strings.isBlank(string)) {
            try {
                result = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                e.printStackTrace();

            }
        }
        return result;
    }

    public static long parseLong(String string) {
        long result = 0;
        if (!Strings.isBlank(string)) {
            try {
                result = Long.parseLong(string);
            } catch (NumberFormatException e) {
                e.printStackTrace();

            }
        }
        return result;
    }

    public static String touzi_ed_values22 = "";
    /**
     * 在数字型字符串千分位加逗号
     * @param str
     * @param edtext
     * @return sb.toString()
     */
    public static String addComma(String str, EditText edtext){

        touzi_ed_values22 = edtext.getText().toString().trim().replaceAll(",","");

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

    /**
     * 精确计算两数之差
     *
     * @param arg1 总金额
     * @param arg2 首付金额
     * @return 贷款金额
     */
    public static double subtract(String arg1, String arg2) {
        double result = 0;
        if (isBlank(arg2)) {
            arg2 = "0";
        }
        if (!Strings.isBlank(arg1)) {
            BigDecimal decimal1 = new BigDecimal(arg1);
            BigDecimal decimal2 = new BigDecimal(arg2);
            result = decimal1.subtract(decimal2).doubleValue();
        }
        return result;
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            b.write(i);
        }
        return b.toString();
    }

    /**
     * 空数据格式化
     *
     * @param args 需要格式化对象
     * @return 格式化后对象
     */
    public static String removeNull( String args) {
        String notNull = "0";
        if (TextUtils.isEmpty(args) || "null".equalsIgnoreCase(args)) {
            return notNull;
        }
        return args;
    }

    /**
     * Filter out emoji.
     */
    public static InputFilter[] filterEmoji() {
        return new InputFilter[] {
                new InputFilter() {

                    Pattern emoji = Pattern.compile(

                            "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",

                            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                               int dstart,

                                               int dend) {

                        Matcher emojiMatcher = emoji.matcher(source);

                        if (emojiMatcher.find()) {

                            return "";
                        }
                        return null;
                    }
                }
        };
    }

    public static boolean isAllSame(String numOrStr) {
        numOrStr = numOrStr.trim();
        if (Strings.isBlank(numOrStr)) {
            return false;
        }
        int length = numOrStr.length();
        return !(length < 8 || length > 16) && !ALPHABET_NUMBER_PATTERN.matcher(numOrStr).matches();
    }

    public static String desensitization(String code) {
        StringBuilder builder = new StringBuilder();
        //char bankCode[] = code.toCharArray();
        //for (int i = 0; i < bankCode.length; i++) {
        //  if (i > 3 && i < 12) {
        //    builder.append("*");
        //  }
        //  if (i < 4 || i >= code.length() - 4) {
        //    builder.append(bankCode[i]);
        //  }
        //
        //  if ((i + 1) % 4 == 0 && i > 0) {
        //    Timber.e("这是i----"+i+"----"+bankCode[i]);
        //    builder.append(" ");
        //  }
        //}
        if (code.length() > 4) {
            builder.append(
                    code.substring(0, 4) + " **** **** " + code.substring(code.length() - 4, code.length()));
        }
        return builder.toString();
    }

    public static String desensitizatIdenty(String identy) {
        return desensitizatIdenty(identy, true);
    }

    public static String desensitizatIdenty(String identy, boolean isIdentity) {
        StringBuilder builder = new StringBuilder();
        char idCode[] = identy.toCharArray();
        if (isIdentity) {
            for (int i = 0; i < idCode.length; i++) {
                if (i > 3 && i < idCode.length - 4) {
                    builder.append("*");
                } else {
                    builder.append(idCode[i]);
                }
            }
        } else {
            for (int i = 0; i < idCode.length; i++) {
                if (i > 2 && i < idCode.length - 2) {
                    builder.append("*");
                } else {
                    builder.append(idCode[i]);
                }
            }
        }
        return builder.toString();
    }

    public static String desensitizatPhone(String identy) {
        if (isBlank(identy)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        char idCode[] = identy.toCharArray();
        for (int i = 0; i < idCode.length; i++) {
            if (i > 2 && i < idCode.length - 2) {
                builder.append("*");
            } else {
                builder.append(idCode[i]);
            }
        }
        return builder.toString();
    }
    //public static String desensitizatIdenty(String identy) {
    //  StringBuilder builder = new StringBuilder();
    //  char idCode[] = identy.toCharArray();
    //  for (int i = 0; i < idCode.length; i++) {
    //    if (i > idCode.length - 9 && i < idCode.length - 2) {
    //      builder.append("*");
    //    } else {
    //      builder.append(idCode[i]);
    //    }
    //  }
    //  return builder.toString();
    //}

    public static String desensitizatName(String name) {
        StringBuilder builder = new StringBuilder();
        char[] nameCode = name.toCharArray();
        for (int i = 0; i < nameCode.length; i++) {
            if (i == nameCode.length - 1) {
                builder.append(nameCode[i]);
            } else {
                builder.append("*");
            }
        }
        return builder.toString();
    }

    /**
     * 格式化钱数，每三位以逗号隔开
     */
    public static String NumberJionComma(int number) {
        StringBuffer sb = new StringBuffer(String.valueOf(number));
        for (int i = sb.length() - 3; i > 0; i = i - 3) {
            sb.insert(i, ",");
        }
        return sb.toString();
    }
    /**
     * double转String,保留小数点后两位
     * @param num
     * @return
     */
    public static String doubleToString(int num){
        String strNum = String.valueOf(num);
        int n = strNum.indexOf(".");
        if(n>0){
            //截取小数点后的数字
            String dotNum = strNum.substring(n+1);
            if("0".equals(dotNum)){
                return strNum+"0";
            }else{
                if(dotNum.length()==1){
                    return strNum +"0";
                }else{
                    return strNum;
                }
            }
        }else{
            return strNum+".00";
        }
    }

    public static String doubleToString(String num){
        int n = num.indexOf(".");
        if(n>0){
            //截取小数点后的数字
            String dotNum = num.substring(n+1);
            if("0".equals(dotNum)){
                return num+"0";
            }else{
                if(dotNum.length()==1){
                    return num +"0";
                }else{
                    return num;
                }
            }
        }else{
            return num+".00";
        }
    }
    /**
     * 格式化钱数，每三位以逗号隔开
     */
    public static String NumberJionComma(double number) {
        StringBuffer sb = new StringBuffer(String.valueOf(number));
        for (int i = sb.length() - 3; i > 0; i = i - 3) {
            sb.insert(i, ",");
        }
        return sb.toString();
    }

    /**
     * 格式化钱数，每三位以逗号隔开
     */
    public static String NumberJionComma(long number) {
        StringBuffer sb = new StringBuffer(String.valueOf(number));
        for (int i = sb.length() - 3; i > 0; i = i - 3) {
            sb.insert(i, ",");
        }
        return sb.toString();
    }

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

    /**
     * 统计逗号数量
     */
    public static int getCommaCount(String str, String key) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(key, index)) != -1) {
            index = index + key.length();

            count++;
        }
        return count;
    }

    private static String nums[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

    private static String pos_units[] = { "", "拾", "佰", "仟" };

    private static String weight_units[] = { "", "万", "亿" };

    /**
     * 将整数转换成汉字数字
     *
     * @param num 需要转换的数字
     * @return 转换后的汉字
     */
    public static String formatIntegerMoney(long num) {
        if (num == 0) {
            return "零";
        }

        int weigth = 0;//节权位
        String chinese = "";
        String chinese_section = "";
        boolean setZero = false;//下一小节是否需要零，第一次没有上一小节所以为false
        while (num > 0) {
            long section = num % 10000;//得到最后面的小节
            if (setZero) {//判断上一小节的千位是否为零，是就设置零
                chinese = nums[0] + chinese;
            }
            chinese_section = sectionTrans(section);
            if (section != 0) {//判断是都加节权位
                chinese_section = chinese_section + weight_units[weigth];
            }
            chinese = chinese_section + chinese;
            chinese_section = "";

            setZero = (section < 1000) && (section > 0);
            num = num / 10000;
            weigth++;
        }
        if ((chinese.length() == 2 || (chinese.length() == 3)) && chinese.contains("一十")) {
            chinese = chinese.substring(1, chinese.length());
        }
        if (chinese.indexOf("一十") == 0) {
            chinese = chinese.replaceFirst("一十", "十");
        }

        return chinese;
    }

    /**
     * 将每段数字转汉子
     */
    public static String sectionTrans(long section) {
        StringBuilder section_chinese = new StringBuilder();
        int pos = 0;//小节内部权位的计数器
        boolean zero = true;//小节内部的置零判断，每一个小节只能有一个零。
        while (section > 0) {
            long v = section % 10;//得到最后一个数
            if (v == 0) {
                if (!zero) {
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个
                    section_chinese.insert(0, nums[0]);
                }
            } else {
                zero = false;//有非零数字就把置零打开
                section_chinese.insert(0, pos_units[pos]);
                section_chinese.insert(0, nums[(int) v]);
            }
            pos++;
            section = section / 10;
        }

        return section_chinese.toString();
    }

    public static String traslateVersion(String version) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < version.length(); i++) {
            builder.append(version.charAt(i));
            if (i != version.length() - 1) {
                builder.append(".");
            }
        }
        return builder.toString();
    }

    public static String getIdentyType(int code) {
        String type = "身份证";
        switch (code) {
            case 0:
                type = "身份证";
                break;
            case 1:
                type = "中国护照";
                break;
            case 2:
                type = "军官证";
                break;
            case 3:
                type = "士兵证";
                break;
            case 4:
                type = "港澳居民来往内地通行证";
                break;
            case 5:
                type = "户口本";
                break;
            case 6:
                type = "外国护照";
                break;
            case 7:
                type = "其他";
                break;
            case 8:
                type = "文职证";
                break;
            case 9:
                type = "警官证";
                break;
            case 10:
                type = "台胞证";
                break;
            case 11:
                type = "外国人永久居留证";
                break;
            case 12:
                type = "身份证";
                break;
        }
        return type;
    }


    public static String categoryTrafer(int productCategory, int yieldRateShow) {
        String category = "";
        if (yieldRateShow == 0) {
            category = productCategory == 0 ? "业绩比较基准" : "7日年化收益率";
        } else {
            category = "年化收益率";
        }

        return category;
    }
}

