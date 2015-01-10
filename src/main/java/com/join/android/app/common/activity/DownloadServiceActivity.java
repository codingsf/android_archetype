package com.join.android.app.common.activity;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.BaseActivity;
import com.join.android.app.common.adapter.DownloadedListAdapter;
import com.join.android.app.common.constants.BroadcastAction;
import com.join.android.app.common.enums.Dtype;
import com.join.android.app.common.utils.APKUtils;
import com.kingnet.android.app.mugo.R;
import com.php25.PDownload.DownloadFile;
import com.php25.PDownload.DownloadFileDao;
import com.php25.PDownload.DownloadTool;
import org.androidannotations.annotations.*;

import java.io.File;
import java.util.List;

/**
 * Created by lala on 14/12/23.
 */
@EActivity(R.layout.download_service_activity_layout)
public class DownloadServiceActivity extends BaseActivity {

    @ViewById
    Button downloadCenter;
    @ViewById
    Button doInstall;
    @ViewById
    Button installedList;
    @ViewById
    Button btnStartService;
    @ViewById
    Button btnWeixin;
    @ViewById
    Button deleteDownload;
    @ViewById
    TextView txtIndicator;
    @ViewById
    TextView txtWeixinIndicator;
    @ViewById
    ListView listView;

    @Bean
    APKUtils apkUtils;
    @Bean
    DownloadFileDao downloadFileDao;

    DownloadFile downloadFile = new DownloadFile();
    DownloadFile weixinDownloadFile = new DownloadFile();

    @AfterViews
    void afterViews() {

        weixinDownloadFile.setUrl("http://gdown.baidu.com/data/wisegame/fab4bcd5101053a8/weixin_501.apk");
        weixinDownloadFile.setPortraitURL("http://t12.baidu.com/it/u=3088020551,4198140884&fm=96");
        weixinDownloadFile.setShowName("微信");
        weixinDownloadFile.setPackageName("com.tencent.mm");

        downloadFile.setUrl("http://gdown.baidu.com/data/wisegame/2c6a60c5cb96c593/QQ_182.apk");
        downloadFile.setPortraitURL("http://img3sw.baidu.com/soft/3a/12350/2f1844d25944d442dc2695a260376513.png?version=1147706935");
        downloadFile.setShowName("qq");
        downloadFile.setPackageName("com.tencent.mobileqq");

        DownloadedListAdapter mAdapter = new DownloadedListAdapter(this, downloadFileDao.queryAllDownloaded(Dtype.APK));
        listView.setAdapter(mAdapter);

    }

    @Click
    void btnWeixinClicked() {
        DownloadTool.download(this, weixinDownloadFile);
    }

    @Click
    void installedListClicked() {
        APKUtils.APKInfo installAPKInfo = apkUtils.getInstallAPKInfo(this, "com.tencent.mobileqq");
        List<DownloadFile> files = downloadFileDao.queryAllDownloaded(Dtype.APK);
        if (files != null && files.size() > 0) {
            APKUtils.APKInfo uninstallAPKInfo = apkUtils.getUninstallAPKInfo(this, new File(files.get(0).getAbsolutePath()));
            txtIndicator.setText(uninstallAPKInfo.getAppName() + ";" + uninstallAPKInfo.getVersionCode() + ";" + uninstallAPKInfo.getVersionName() + ";" + uninstallAPKInfo.getPackageName());
        }
    }

    @Click
    void btnStartServiceClicked() {

        if (btnStartService.getText().equals("暂停")) {
            DownloadTool.pause(this, downloadFile);
            btnStartService.setText("继续下载");
            return;
        }
        DownloadTool.download(this, downloadFile);
        btnStartService.setText("暂停");
    }

    @Click
    void doInstallClicked() {

    }

    @Click
    void deleteDownloadClicked() {
        DownloadTool.del(this, downloadFile);
        DownloadTool.del(this, weixinDownloadFile);
    }


    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_UPDATE_PROGRESS)
    @UiThread
    void onDownloadUpdateProgress(@Receiver.Extra DownloadFile file) {
        if (file.getUrl().equals(weixinDownloadFile.getUrl())) txtWeixinIndicator.setText(file.getPercent());
        else
            txtIndicator.setText(file.getPercent());
    }


    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_COMPLETE)
    @UiThread
    void onDownloadCompete(@Receiver.Extra DownloadFile file) {
        if (file.getUrl().equals(weixinDownloadFile.getUrl()))
            txtWeixinIndicator.setText(file.getShowName() + "has been download completed.");
        else
            txtIndicator.setText(file.getShowName() + "has been download completed.");
    }

    /**
     * 无网络或网络异常
     *
     * @param file
     */
    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_INTERRUPT)
    @UiThread
    void onDownloadInterrupt(@Receiver.Extra DownloadFile file) {
        if (file.getUrl().equals(weixinDownloadFile.getUrl()))
            txtWeixinIndicator.setText(file.getShowName() + "'s downloading has been interrupted.");
        else
            txtIndicator.setText(file.getShowName() + "'s downloading has been interrupted.");
        btnStartService.setText("继续下载");
    }

}
