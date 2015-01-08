package com.join.android.app.common.exception;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.join.android.app.common.activity.MainActivity;
import com.join.android.app.common.activity.MainActivity_;
import com.join.android.app.common.manager.DialogManager;
import com.join.android.app.common.utils.SystemInfoUtils;

/**
 * Created by lala on 14/12/22.
 */
public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Context context = null;



    public DefaultExceptionHandler(Context context) {

        this.context = context;

    }



    @Override

    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();


        // 收集异常信息 并且发送到服务器

        sendCrashReport(ex);



        // 等待半秒

        try {

            Thread.sleep(500);

        } catch (InterruptedException e) {

            //

        }



        // 处理异常

        handleException();



    }



    private void sendCrashReport(Throwable ex) {



        StringBuffer exceptionStr = new StringBuffer(SystemInfoUtils.getInstance(context).getInfo()+";error::");

        exceptionStr.append(ex.getMessage());



        StackTraceElement[] elements = ex.getStackTrace();

        for (int i = 0; i < elements.length; i++) {

            exceptionStr.append(elements[i].toString());

        }

        Log.e(DefaultExceptionHandler.this.getClass().getName(),exceptionStr.toString());
        //TODO
        //发送收集到的Crash信息到服务器

    }



    private void handleException() {

        //TODO

        //这里可以对异常进行处理。

        //比如提示用户程序崩溃了。

        //比如记录重要的信息，尝试恢复现场。

        //或者干脆记录重要的信息后，直接杀死程序。
        System.exit(0);
        MainActivity_.intent(context).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK).start();

    }

}
