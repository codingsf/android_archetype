package com.join.android.app.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import org.androidannotations.annotations.EBean;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lala on 14/12/23.
 */
@EBean(scope = EBean.Scope.Singleton)
public class APKUtils {


    /**
     * 打开某APK程序
     * @param context
     * @param pak
     */
    public void open(Context context,String pak){
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(pak);
        context.startActivity(intent);
    }

    public boolean isRunning(Context context,String packageName){
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 安装APK
     * @param activity
     * @param apkFile
     */
    public void installAPK(Activity activity,File apkFile) {
        //代码安装
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        activity.startActivityForResult(intent, 1);
    }

    /**
     * 检测是否已安装
     * @param pak 包名
     * @return
     */
    public  boolean checkInstall(Context context,String pak) {
        boolean install=false;
        PackageManager pm=context.getPackageManager();
        try {
            PackageInfo info=pm.getPackageInfo(pak,1);
            if (info!=null&&info.activities.length>0) {
                install=true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return install;
    }

    /**
     * 获取待安装APK的信息
     * @param ctx
     * @param apkFile
     * @return
     */
    public  APKInfo getUninstallAPKInfo(Context ctx,File apkFile) {

        APKInfo apkInfo = new APKInfo();
        String versionName = null;
        String appName = null;
        String pakName = null;
        Drawable icon = null;

        PackageManager pm=ctx.getPackageManager();
        PackageInfo pakinfo=pm.getPackageArchiveInfo(apkFile.getAbsolutePath(),PackageManager.GET_ACTIVITIES);
        if (pakinfo!=null) {
            ApplicationInfo appinfo=pakinfo.applicationInfo;
            versionName=pakinfo.versionName;
            icon=pm.getApplicationIcon(appinfo);
            appName=(String) pm.getApplicationLabel(appinfo);
            pakName=appinfo.packageName;
        }
        apkInfo.setVersionName(versionName);
        apkInfo.setAppName(appName);
        apkInfo.setPackageName(pakName);
        apkInfo.setIcon(icon);
        return apkInfo;
    }

    /**
     * 获取已安装APK的信息
     * @param ctx
     * @param pak
     * @return
     */
    public  APKInfo getInstallAPKInfo(Context ctx,String pak) {
        APKInfo apkInfo = new APKInfo();
        int versionCode = 0;
        String versionName = null;
        String appName = null;
        String pakName = pak;
        Drawable icon = null;
        PackageManager pm=ctx.getPackageManager();
        PackageInfo appinfo;
        try {
            appinfo = pm.getPackageInfo(pak,PackageManager.GET_ACTIVITIES);
            if (appinfo!=null) {
                versionName = appinfo.versionName;
                versionCode = appinfo.versionCode;
                appName = appinfo.applicationInfo.loadLabel(ctx.getPackageManager()).toString();
                icon=appinfo.applicationInfo.loadIcon(ctx.getPackageManager());
                pakName=appinfo.packageName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        apkInfo.setVersionCode(versionCode);
        apkInfo.setVersionName(versionName);
        apkInfo.setAppName(appName);
        apkInfo.setPackageName(pakName);
        apkInfo.setIcon(icon);

        return apkInfo;
    }

    /**
     * 得到手机中所有已安装的应用
     * @param ctx
     * @return
     */
    public  List<APKInfo> getInstallApkInfo(Context ctx){
        ArrayList<APKInfo> appList = new ArrayList<APKInfo>();
        List<PackageInfo> packages = ctx.getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packages.size();i++) {
            PackageInfo packageInfo = packages.get(i);
            APKInfo tmpInfo = new APKInfo();
            tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(ctx.getPackageManager()).toString());
            tmpInfo.setPackageName(packageInfo.packageName);
            tmpInfo.setVersionName(packageInfo.versionName);
            tmpInfo.setVersionCode(packageInfo.versionCode);
            tmpInfo.setIcon(packageInfo.applicationInfo.loadIcon(ctx.getPackageManager()));
            appList.add(tmpInfo);
        }
        return appList;
    }

    /**
     * 静默安装
     * @param file
     */
    public void installInSilence(File file)
    {
        try {
            Class<?> pmService;
            Class<?> activityTherad;
            Method method;

            activityTherad = Class.forName("android.app.ActivityThread");
            Class<?> paramTypes[] = getParamTypes(activityTherad , "getPackageManager");
            method = activityTherad.getMethod("getPackageManager", paramTypes);
            Object PackageManagerService = method.invoke(activityTherad);

            pmService = PackageManagerService.getClass();

            Class<?> paramTypes1[] = getParamTypes(pmService , "installPackage");
            method = pmService.getMethod("installPackage", paramTypes1);
            method.invoke(PackageManagerService , Uri.fromFile(file) , null , 0 , null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 静默安装
     * @param file
     * @return
     */
    public boolean slientInstall(File file) {
        boolean result = false;
        Process process = null;
        OutputStream out = null;
        try {
            process = Runtime.getRuntime().exec("su");
            out = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeBytes("chmod 777 " + file.getPath() + "\n");
            dataOutputStream.writeBytes("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install -r " +
                    file.getPath());
            // 提交命令
            dataOutputStream.flush();
            // 关闭流操作
            dataOutputStream.close();
            out.close();
            int value = process.waitFor();

            // 代表成功
            if (value == 0) {
                result = true;
            } else if (value == 1) { // 失败
                result = false;
            } else { // 未知情况
                result = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Class<?>[] getParamTypes(Class<?> cls, String mName) {
        Class<?> cs[] = null;

        Method[] mtd = cls.getMethods();

        for (int i = 0; i < mtd.length; i++) {
            if (!mtd[i].getName().equals(mName)) {
                continue;
            }
            cs = mtd[i].getParameterTypes();
        }
        return cs;
    }


    public class APKInfo{
        private String versionName;
        private int versionCode;
        private String appName;
        private String packageName;
        private Drawable icon;

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }
    }

}

