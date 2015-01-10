package com.join.android.app.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.join.android.app.common.servcie.DownloadCoreService_;

/**
 * Created by lala on 15/1/10.
 */
public class UserPresentReceiver extends BroadcastReceiver {
    private static final String TAG = "UserPresentReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.e(TAG, "receive broadcast");
        DownloadCoreService_.intent(context).start();
    }
}
