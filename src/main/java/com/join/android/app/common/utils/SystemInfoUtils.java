package com.join.android.app.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lala on 14/12/22.
 */
public class SystemInfoUtils {

    Context mContext;
    static SystemInfoUtils systemInfoUtils;


    private SystemInfoUtils(Context context){
        mContext = context;
    }

    public static SystemInfoUtils getInstance(Context context){
        if(systemInfoUtils==null)systemInfoUtils = new SystemInfoUtils(context);
        return systemInfoUtils;
    }


    /**
     * 获取android当前可用内存大小
     */
    public String getAvailMemory() {// 获取android当前可用内存大小

        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //mi.availMem; 当前系统的可用内存

        return Formatter.formatFileSize(mContext, mi.availMem);// 将获取的内存大小规格化
    }

    /**
     * 获得系统总内存
     */
    public String getTotalMemory() {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(mContext, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    /**
     * 获得手机屏幕宽高
     * @return
     */
    public String getHeightAndWidth(Activity activity){
        int width=activity.getWindowManager().getDefaultDisplay().getWidth();
        int heigth=activity.getWindowManager().getDefaultDisplay().getHeight();
        String str=width+""+heigth+"";
        return str;
    }
    /**
     * 获取IMEI号，IESI号，手机型号
     */
    public String getInfo() {
        TelephonyManager mTm = (TelephonyManager)mContext.getSystemService(mContext.TELEPHONY_SERVICE);

        String imei = mTm.getDeviceId();
        String imsi = mTm.getSubscriberId();
        String mtype = android.os.Build.MODEL; // 手机型号
        String mtyb= android.os.Build.BRAND;//手机品牌
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        Log.i("text", "手机IMEI号："+imei+"手机IESI号："+imsi+"手机型号："+mtype+"手机品牌："+mtyb+"手机号码"+numer);
        return "[version:"+getVersionName()+",imei:"+imei+",imsi:"+imsi+",model:"+mtype+",brand:"+mtyb+",number:"+numer+"]";
    }

    public String getVersionName(){
        // 获取packagemanager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(mContext.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        String version = packInfo.versionName;
        return version;
    }

    /**
     * .获取手机MAC地址
     * 只有手机开启wifi才能获取到mac地址
     */
    public String getMacAddress(){
        String result = "";
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();
        Log.i("text", "手机macAdd:" + result);
        return result;
    }
    /**
     * 手机CPU信息
     */
    public String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        Log.i("text", "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
        return cpuInfo;
    }
}
