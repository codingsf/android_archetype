package com.join.android.app.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by mawj on 2014/12/1.
 */
public class MetaUtils {

    public static String getAppID(Context context)  {
        String key = "6";
        try {
        ApplicationInfo appInfo = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(),
                        PackageManager.GET_META_DATA);

            key = appInfo.metaData.getInt("XOPENSDK_APPKEY") + "";
        } catch (Exception e) {
        }
        return key;
    }

    /**
     * 获取渠道号
     *
     * @param context
     * @return
     * @throws android.content.pm.PackageManager.NameNotFoundException
     */
    public static String getAd(Context context)  {

        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            String key = appInfo.metaData.getString("qd_code");
            return key;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "-1";
    }

}
