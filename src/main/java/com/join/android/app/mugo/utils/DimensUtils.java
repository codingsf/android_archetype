package com.join.android.app.mugo.utils;

import android.content.Context;

/**
 * Created by mawj on 2014/12/2.
 */
public class DimensUtils {

    private static int _8dp = 0;
    private static int _10dp = 0;
    private static int _5dp = 0;
    private static int _15dp = 0;
    private static int _17dp = 0;
    private static int _20dp = 0;
    private static int _25dp = 0;
    private static int _28dp = 0;
    private static int _30dp = 0;
    private static int _33dp=0;
    private static int _40dp = 0;
    private static int _45dp = 0;
    private static int _50dp = 0;
    private static int _53dp = 0;
    private static int _60dp = 0;
    private static int _70dp = 0;
    private static int _80dp = 0;
    private static int _85dp = 0;
    private static int _100dp = 0;
    private static int _150dp = 0;
    private static int _200dp=0;
    private static int _264dp=0;
    private static int _308dp = 0;


    public static int get8dp(Context context){
        if(_8dp==0) _8dp = DensityUtil.dip2px(context,10);
        return _8dp;

    }

    public static int get5dp(Context context) {
        if (_5dp == 0) _5dp = DensityUtil.dip2px(context, 5);
        return _5dp;
    }
    public static int get10dp(Context context) {
        if (_10dp == 0) _10dp = DensityUtil.dip2px(context, 10);
        return _10dp;

    }
    public static int get15dp(Context context) {
        if (_15dp == 0) _15dp = DensityUtil.dip2px(context, 15);
        return _15dp;
    }public static int get17dp(Context context) {
        if (_17dp == 0) _17dp = DensityUtil.dip2px(context, 17);
        return _17dp;
    }
    public static int get20dp(Context context) {
        if (_20dp == 0) _20dp = DensityUtil.dip2px(context, 20);
        return _20dp;
    }

    public static int get25dp(Context context) {
        if (_25dp == 0) _25dp = DensityUtil.dip2px(context, 25);
        return _25dp;
    } public static int get30dp(Context context) {
        if (_30dp == 0) _30dp = DensityUtil.dip2px(context, 30);
        return _30dp;
    }
    public static int get28dp(Context context) {
        if (_28dp == 0) _28dp = DensityUtil.dip2px(context, 28);
        return _28dp;
    }

    public static int get40dp(Context context){
        if(_40dp==0) _40dp = DensityUtil.dip2px(context,40);
        return _40dp;
    } public static int get45dp(Context context) {
        if (_45dp == 0) _45dp = DensityUtil.dip2px(context, 45);
        return _45dp;
    }

    public static int get50dp(Context context){
        if(_50dp==0) _50dp = DensityUtil.dip2px(context,50);
        return _50dp;
    }
    public static int get53dp(Context context) {
        if (_53dp == 0) _53dp = DensityUtil.dip2px(context, 53);
        return _53dp;
    }
 public static int get60dp(Context context){
        if(_60dp==0) _60dp = DensityUtil.dip2px(context,60);
        return _60dp;
    }
 public static int get70dp(Context context){
        if(_70dp==0) _70dp = DensityUtil.dip2px(context,70);
        return _70dp;
    }
    public static int get80dp(Context context) {
        if (_80dp == 0) _80dp = DensityUtil.dip2px(context, 80);
        return _80dp;
    }
    public static int get85dp(Context context) {
        if (_85dp == 0) _85dp = DensityUtil.dip2px(context, 85);
        return _85dp;
    }

    public static int get100dp(Context context){
        if(_100dp==0) _100dp = DensityUtil.dip2px(context,100);
        return _100dp;
    }

    public static int get200dp(Context context){
        if(_200dp==0) _200dp = DensityUtil.dip2px(context,200);
        return _200dp;
    }   public static int get264dp(Context context){
        if(_264dp==0) _264dp = DensityUtil.dip2px(context,264);
        return _264dp;
    }

    public static int get308dp(Context context) {
        if (_308dp == 0) _308dp = DensityUtil.dip2px(context, 308);
        return _308dp;
    }

    public static int get33dp(Context context) {

        if (_33dp == 0) _33dp = DensityUtil.dip2px(context,33);
        return _33dp;
    }
    public static int get150dp(Context context) {
        if (_150dp == 0) _150dp = DensityUtil.dip2px(context, 150);
        return _150dp;
    }
}
