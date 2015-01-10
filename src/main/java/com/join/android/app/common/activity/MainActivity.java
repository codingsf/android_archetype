package com.join.android.app.common.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Button;
import com.BaseActivity;
import com.join.android.app.common.servcie.DownloadCoreService;
import com.join.android.app.common.servcie.DownloadCoreService_;
import com.kingnet.android.app.mugo.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-26
 * Time: 上午11:32
 */

@EActivity(R.layout.main_activity_layout)
public class MainActivity extends BaseActivity {

    @ViewById
    Button btnImgListView;
    @ViewById
    Button btnDB;
    @ViewById
    Button btnPref;
    @ViewById
    Button btnRestFul;
    @ViewById
    Button btnFragment;
    @ViewById
    Button btnAA;
    @ViewById
    Button btnProgressBar;
    @ViewById
    Button startOtherApp;

    @ViewById
    Button downloadService;
    @ViewById
    Button installInSilence;

    @AfterViews
    void afterViews() {

    }

    @Click
    void btnImgListViewClicked() {
        showLoading();
        ImageCacheActivity_.intent(this).start();
    }

    @Click
    void btnDBClicked() {
        DBActivity_.intent(this).start();
    }

    @Click
    void btnFragmentClicked() {
        MyFragmentActivity_.intent(this).start();
    }

    @Click
    void btnAAClicked() {
        MyActivity_.intent(this).start();
    }

    @Click
    void btnPrefClicked() {
        PrefActivity_.intent(this).start();
    }

    @Click
    void btnRestFulClicked() {
        RestFulActivity_.intent(this).start();
    }

    @Click
    void btnProgressBarClicked() {
        ProgressActivity_.intent(this).start();
    }

    @Click
    void startOtherAppClicked(){

        PackageManager packageManager = this.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.example.myapp");
        startActivity(intent);
    }

    @Click
    void downloadServiceClicked(){
        DownloadServiceActivity_.intent(this).start();
    }

    @Click
    void installInSilenceClicked(){

    }

    @Click
    void photoClicked(){
        PhotoSelectActivity_.intent(this).start();
    }

}
