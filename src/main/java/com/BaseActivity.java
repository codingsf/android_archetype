package com;

import android.app.Activity;
import android.os.Bundle;
import com.join.android.app.common.db.DatabaseHelper;
import com.join.android.app.common.db.manager.*;
import com.join.android.app.common.dialog.CommonDialogLoading;
import com.join.android.app.common.exception.DefaultExceptionHandler;


/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-10
 * Time: 下午3:20
 */
public class BaseActivity extends Activity {

    private DatabaseHelper databaseHelper = null;
    private CommonDialogLoading loading;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        DBManager.getInstance(this).onDestroy();
    }

    public DatabaseHelper getHelper() {
        if (databaseHelper == null)
            databaseHelper = DBManager.getInstance(this).getHelper();

        return databaseHelper;
    }

    /**
     * db stuff
     * when needs multiple databases we need create database manually.
     * for example ,after login
     *
     * @param dbName
     */
    public void createDB(String dbName) {
        DBManager.getInstance(this).createDB(dbName);
    }

    public void showLoading() {
        loading = new CommonDialogLoading(this);
        loading.show();
    }

    public void dismissLoading() {
        if (loading == null) return;
        try {
            loading.dismiss();
        } catch (Exception e) {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(
                this.getApplicationContext()));
    }

    @Override
    protected void onStop() {
        dismissLoading();
        super.onStop();
    }
}
