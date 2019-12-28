package com.ranmaxli.api.utils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by liran on 2017/2/13.
 */
public class StringUtil {

    public static <T> String joinlistWithSeparator(List<T> objects, String separator) {
        String str = "";
        for (int i = 0; i < objects.size(); i++) {
            str = str + objects.get(i).toString();
            str = str + separator;
        }
        if (str.length() > 0) {
            str = str.substring(0, str.length() - separator.length());
        }
        return str;
    }

    public static String ArrayToDelimitedString(String[] source, String delimiter, String surround) {
        String str = "";
        for (int i = 0; i < source.length; i++) {
            str = str + surround + source[i] + surround + delimiter;
        }
        if (str.length() > 0) {
            str = str.substring(0, str.length() - delimiter.length());
        }
        return str;
    }

    public static String trim(String str) {
        return trimEnd(trimStart(str));
    }

    public static String trimStart(String str) {
        return trimStart(str, "\\s");
    }

    public static String trimStart(String str, String prefix) {
        return str.replaceFirst("^" + prefix + "+", "");
    }

    public static String trimEnd(String str) {
        return str.replaceFirst("\\s+$", "");
    }

    public static String subEnd(String str) {
        return subEnd(str, ".");
    }

    public static String subEnd(String str, String prefix) {
        return str.substring(str.lastIndexOf(prefix) + 1).toLowerCase();
    }

    public static boolean isNullOrEmpty(String s) {
        if (s == null || s.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int toInt(Object obj) {
        return toInt(obj, 0);
    }

    public static int toInt(Object obj, int value) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
        }
        return value;
    }

    public static boolean toBoolean(Object obj) {
        return toBoolean(obj, false);
    }

    public static boolean toBoolean(Object obj, boolean value) {
        if (obj == null) return value;
        if (Pattern.compile("y|yes|true|1|是|对").matcher(obj.toString().toLowerCase()).matches()) return true;
        if (Pattern.compile("n|no|false|0|否|错").matcher(obj.toString().toLowerCase()).matches()) return false;
        return value;
    }

    public static double toDouble(Object obj) {
        double a = 0;
        if (obj != null) {
            try {
                a = Double.parseDouble(obj.toString());
            } catch (Exception ex) {
            }
        }
        return a;
    }

    public static Long toLong(Object obj) {
        Long a = 0L;
        if (obj != null) {
            try {
                a = Long.parseLong(obj.toString());
            } catch (Exception ex) {
            }
        }
        return a;
    }

    public static String UniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String toString(Object obj) {
        String str = "";
        try {
            if (obj == null)
                str = "";
            str = obj.toString();
        } catch (Exception ex) {

        }
        return str;
    }

    public static String decode(String str) {
        String result = "";
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (Exception ex) {
            result = str;
        }
        return result;
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列

        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // 将每个字符转换成ASCII码

            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    //region 数组的并集、交际、差集 chw 2017-9-6 10:23:44
    //求两个字符串数组的并集，利用set的元素唯一性
    public static String[] union(String[] arr1, String[] arr2) {
        Set<String> set = new HashSet<String>();
        for (String str : arr1) {
            set.add(str);
        }
        for (String str : arr2) {
            set.add(str);
        }
        String[] result = {};
        return set.toArray(result);
    }

    //求两个数组的交集
    public static String[] intersect(String[] arr1, String[] arr2) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        LinkedList<String> list = new LinkedList<String>();
        for (String str : arr1) {
            if (!map.containsKey(str)) {
                map.put(str, Boolean.FALSE);
            }
        }
        for (String str : arr2) {
            if (map.containsKey(str)) {
                map.put(str, Boolean.TRUE);
            }
        }

        for (Map.Entry<String, Boolean> e : map.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }

        String[] result = {};
        return list.toArray(result);
    }

    //求两个数组的差集
    public static String[] minus(String[] arr1, String[] arr2) {
        LinkedList<String> list = new LinkedList<String>();
        LinkedList<String> history = new LinkedList<String>();
        String[] longerArr = arr1;
        String[] shorterArr = arr2;
        //找出较长的数组来减较短的数组
        if (arr1.length > arr2.length) {
            longerArr = arr2;
            shorterArr = arr1;
        }
        for (String str : longerArr) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        for (String str : shorterArr) {
            if (list.contains(str)) {
                history.add(str);
                list.remove(str);
            } else {
                if (!history.contains(str)) {
                    list.add(str);
                }
            }
        }

        String[] result = {};
        return list.toArray(result);
    }
    //endregion
}
