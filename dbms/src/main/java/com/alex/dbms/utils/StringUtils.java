package com.alex.dbms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 字符串工具类
 * @Author:     alex
 * @CreateDate: 2019/11/14 9:43
 * @Version:    1.0
 *
*/
public class StringUtils {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * @Description: 判空操作
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:44
     * @param value
     * @return
    */
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
    }

    /**
     * @Description: 下划线转驼峰
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:46
     * @param str
     * @return
    */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuilder sb = new StringBuilder();
        while(matcher.find())
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * @Description: 驼峰转下划线
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:52
     * @param
     * @return
    */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find())
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * @Description: 首字母转小写
     * @Author:      alex
     * @CreateDate:  2019/11/14 9:55
     * @param str
     * @return
    */
    public static String uncapitalize(String str) {
        if (Character.isLowerCase(str.charAt(0)))
            return str;
        return new StringBuilder().append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * @Description: 首字母转大写
     * @Author:      alex
     * @CreateDate:  2019/11/14 10:00
     * @param str
     * @return
    */
    public static String capitalize(String str) {
        if (Character.isUpperCase(str.charAt(0)))
            return str;
        return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
    }
}

