package com.join.android.app.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-17
 * Time: 上午11:15
 */
public class DateUtils {



    public static String FormatForNormalTimeFromMil(long mill){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mill);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String rs = sdf.format(calendar.getTime());
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String FormatForCourseLastLearningTime(String time){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String rs = FormatForDownloadTime(sdf.parse(time).getTime());
            return rs;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String FormatForExamIntroLastLearningTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String rs = FormatForDownloadTime(sdf.parse(time).getTime());
            return rs;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 下载日期是几分钟前、几天前
     *
     * @return
     */
    public static String FormatForDownloadTime(long begin) {
        long now = System.currentTimeMillis();
        long gap = (now - begin) / 1000;
        if (gap < 60) return "刚刚";
        else if((gap / 60 / 60 / 24/30/12)>1){
            return gap / 60 / 60 / 24/30/12 + "年前";
        }else if((gap / 60 / 60 / 24/30)>1){
            return gap / 60 / 60 / 24/30 + "月前";
        }else if((gap / 60 / 60 / 24)>1){
            return gap / 60 / 60 / 24 + "天前";
        }else if (gap > 60 && gap < 60 * 60 * 60&& gap/(60 * 60)>0) {
            return gap / 60 / 60 + "小时前";
        }else if (gap > 60 && gap < 60 * 60) {
            return gap / 60 + "分钟前";
        }

        return gap / 60 / 60 / 24 + "天前";
    }

    /**
     * 秒数转化成标准时间格式
     *
     * @return
     */
    public static String SecondToNormalTime(long s) {
        String _N, _K, _M;
        long N = s / 3600;

        s = s % 3600;
        long K = s / 60;
        s = s % 60;
        long M = s;

        _N = N + "";
        _K = K + "";
        _M = M + "";
        if (K < 10) _K = "0" + K;
        if (M < 10) _M = "0" + M;
        if (N == 0) {
            return _K + ":" + _M;
        } else {
            if (N < 10) _N = "0" + N;
        }

        return _N + ":" + _K + ":" + _M;

    }

    public static String ConvertDateToNormalString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date Parse(String date,String fmt){
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
