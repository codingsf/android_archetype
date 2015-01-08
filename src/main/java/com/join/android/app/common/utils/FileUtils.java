package com.join.android.app.common.utils;

import java.text.DecimalFormat;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-17
 * Time: 上午11:04
 */
public class FileUtils {
    /**
     * 转换文件的大小以B,KB,M,G等计算
     *
     * @param fileS
     * @return
     */
    public static String FormatFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.0");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }

        if(fileSizeString.equals(".0B")){
            return "0B";
        }
        return fileSizeString;
    }

}
