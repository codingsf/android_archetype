package com.join.android.app.mugo.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by wuw on 2014/11/19.
 */
public class MD5Utils {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String getSafeSign(Map<String, String> map, String appkey) {
        List<String> query_string = new ArrayList<String>();
        try {
            for (String key : map.keySet()) {
                if (key.equals("sig") || key.equals("sign")) {
                    continue;
                }
                query_string.add(key + "=" + map.get(key));
            }
            Collections.sort(query_string);
            String str = appkey + "" + query_string.toString().replace("[", "").replace("]", "").replace(", ", "&");
            // 创建具有指定算法名称的信息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
            byte[] results = md.digest(str.getBytes());
            // 将得到的字节数组变成字符串返回\
            return byteArrayToHexString(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 轮换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
