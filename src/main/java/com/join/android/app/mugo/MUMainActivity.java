package com.join.android.app.mugo;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.BaseActivity;
import com.join.android.app.common.constants.BroadcastAction;
import com.join.android.app.common.manager.DialogManager;
import com.join.android.app.common.servcie.DownloadCoreService_;
import com.join.android.app.common.utils.*;
import com.join.android.app.mugo.dialog.ADDialog;
import com.join.android.app.mugo.dto.AD;
import com.join.android.app.mugo.dto.APKUrl;
import com.join.android.app.mugo.pref.SysPref_;
import com.join.android.app.mugo.rpc.MURPCService;
import com.join.android.app.mugo.rpc.StatisticsService;
import com.join.android.app.mugo.view.PorterDuffView;
import com.kingnet.android.app.mugo.R;
import com.php25.PDownload.DownloadFile;
import com.php25.PDownload.DownloadFileDao;
import com.php25.PDownload.DownloadTool;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestClientException;

import java.io.File;
import java.util.List;

/**
 * Created by lala on 15/1/8.
 */
@EActivity(R.layout.mu_main)
public class MUMainActivity extends BaseActivity {
String TAG = getClass().getSimpleName();
    boolean isDownloading = true;

    @ViewById
    ImageView btnDownload;
    @ViewById
    PorterDuffView pretty;

    @ViewById
    TextView speed;
    @ViewById
    TextView download_now;

    @StringRes(resName = "net_excption")
    String netExcption;
    @StringRes(resName = "connect_server_excption")
    String netConnectException;

    @RestService
    MURPCService murpcService;

    @Bean
    DownloadFileDao downloadFileDao;

    @Pref
    SysPref_ sysPref;

    DownloadFile downloadFile;

    @AfterViews
    void afterViews() {

        //加载广告
        ad();

        if(!sysPref.active().get()){
            active();
        }
        //检测本地是否已存在
        if(!sysPref.packageName().get().equals("-1")&&APKUtils_.getInstance_(this).checkInstall(this,sysPref.packageName().get())){
            BBSActivity_.intent(this).start();
            finish();
        }else{
            //检测数据库中是否有记录
            List<DownloadFile> downloadFiles = downloadFileDao.queryAll();
            if(downloadFiles!=null&&downloadFiles.size()>0){
                //检测是否已下载完成
                downloadFile = downloadFiles.get(0);

                if(downloadFile.getFinished()){
                    //检测文件是否存在
                    File f = new File(downloadFile.getAbsolutePath());
                    if(!f.exists()){
                        downloadFileDao.delete(downloadFile);
                        showLoading();
                        loadUrlFromServer();
                        return;
                    }

                    pretty.setProgress(1);
                    pretty.setPorterDuffMode(false);
                    download_now.setText(getString(R.string.download_complete));
                    btnDownload.setImageDrawable(getResources().getDrawable(R.drawable.install));
                    btnDownload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            APKUtils_.getInstance_(MUMainActivity.this).installAPK(MUMainActivity.this,new File(downloadFile.getAbsolutePath()));


                        }
                    });

                }else{
                    if(StringUtils.isNotEmpty(downloadFile.getPercent())&&downloadFile.getPercent().contains("%"))
                        pretty.setProgress(Float.parseFloat(downloadFile.getPercent().split("%")[0])/100);
                    else
                        pretty.setProgress(0);
                    File f = new File(downloadFile.getAbsolutePath());
                    speed.setText("0B/秒" + "       " + FileUtils.FormatFileSize(f.length()) + "/" + FileUtils.FormatFileSize(downloadFile.getTotalSize()));
                    startDownload();
                }

            }else{
                showLoading();
                loadUrlFromServer();
            }

        }

    }

    @Background
    void ad(){
        try{
            AD ad = murpcService.getAD(MetaUtils.getAd(this));
            showAD(ad);
        }catch (RestClientException e){

        }

    }

    @UiThread
    void showAD(final AD ad){

        if(ad!=null&& StringUtils.isNotEmpty(ad.getImgUrl())){
            ADDialog dialog = new ADDialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar,ad);
            dialog.show();
        }
    }

    @Background
    void active(){
        try{
            StatisticsService.getInstance().acceleratorActive(SystemInfoUtils.getInstance(this).getMacAddress(),MetaUtils.getAppID(this),MetaUtils.getAd(this)+"");
        }catch (Exception e){

        }

    }

    @Background
    void activeDownload(){
        try{
            StatisticsService.getInstance().acceleratorDownload(SystemInfoUtils.getInstance(this).getMacAddress(),MetaUtils.getAppID(this),MetaUtils.getAd(this)+"");
        }catch (Exception e){}

    }

    @Background
    void loadUrlFromServer() {
        if (NetWorkUtils.isNetworkConnected(this)) {
            try {
                APKUrl apkUrl = murpcService.getAPKUrl(MetaUtils.getAd(this));
                start(apkUrl);
            } catch (Exception e) {
                e.printStackTrace();
                serverConnectionException();
            }
        } else {
            netWorkException();
        }
    }

    @UiThread
     void start(final APKUrl apkUrl) {
        dismissLoading();
        if(apkUrl.getRet()==0){
            Toast.makeText(this,"网络连接失败",Toast.LENGTH_LONG).show();

            return;
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                    downloadFile = new DownloadFile();
                    downloadFile.setUrl(apkUrl.getApkUrl());
                    downloadFile.setShowName(apkUrl.getApkName());
                    DownloadTool.download(MUMainActivity.this, downloadFile);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    void startDownload(){
        DownloadCoreService_.intent(getApplicationContext()).start();
        if(!NetWorkUtils.isNetworkConnected(this)){
            Toast.makeText(this,"请连接网络",Toast.LENGTH_LONG).show();
            download_now.setText(getString(R.string.net_excption));
            pauseDownload();
            isDownloading = false;
            return;
        }

        if(downloadFile==null){
            showLoading();
            loadUrlFromServer();
            return;
        }
        btnDownload.setImageDrawable(getResources().getDrawable(R.drawable.pause_download));
        DownloadTool.download(this, downloadFile);
        isDownloading = true;
        download_now.setText(getString(R.string.download_now));
    }

    void pauseDownload(){
        btnDownload.setImageDrawable(getResources().getDrawable(R.drawable.start_download));
        DownloadTool.pause(this, downloadFile);
        isDownloading = false;
        download_now.setText(getString(R.string.download_pause));
        if(downloadFile!=null){
            long nowSize = new File(downloadFile.getAbsolutePath()).length();
            speed.setText("0B/秒" + "       " + FileUtils.FormatFileSize(nowSize) + "/" + FileUtils.FormatFileSize(downloadFile.getTotalSize()));
        }

    }

    void pauseDownloadWhenNetworkException(){
        isDownloading = false;
        btnDownload.setImageDrawable(getResources().getDrawable(R.drawable.start_download));
        download_now.setText(getString(R.string.net_excption));
        DownloadTool.pause(this, downloadFile);
    }

    @Click
    void btnDownloadClicked() {
        if (isDownloading) {
            pauseDownload();
        } else {
            startDownload();
        }
    }

    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_UPDATE_PROGRESS)
    @UiThread
    void onDownloadUpdateProgress(@Receiver.Extra DownloadFile file) {
        if(downloadFile==null)return;
        if (file.getUrl().equals(downloadFile.getUrl())) {
            downloadFile = file;
            //计算速度
            long nowSize = new File(file.getAbsolutePath()).length();
            if (!first){
                if(nowSize - lastSize<0)return;
                speed.setText(FileUtils.FormatFileSize(nowSize - lastSize) + "/秒" + "       " + FileUtils.FormatFileSize(nowSize) + "/" + FileUtils.FormatFileSize(file.getTotalSize()));
            }

            else
                first = false;

            lastSize = nowSize;
            float p = Float.parseFloat(file.getPercent().split("%")[0]);
            pretty.setProgress(p/100);
        }
    }

    /**
     * 无网络或网络异常
     *
     * @param file
     */
    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_INTERRUPT)
    @UiThread
    void onDownloadInterrupt(@Receiver.Extra DownloadFile file) {
        if (file.getUrl().equals(downloadFile.getUrl())) {
            Toast.makeText(this,netExcption,Toast.LENGTH_LONG).show();
            pauseDownloadWhenNetworkException();
        }
    }

    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_COMPLETE)
    @UiThread
    void onDownloadCompete(@Receiver.Extra DownloadFile file) {
        if (file.getUrl().equals(downloadFile.getUrl())){
            downloadFile = file;
            activeDownload();
            if(sysPref!=null)
            sysPref.packageName().put(APKUtils_.getInstance_(this).getUninstallAPKInfo(this, new File(file.getAbsolutePath())).getPackageName());


            download_now.setText(getString(R.string.download_complete));
            pretty.setProgress(1);
            pretty.setPorterDuffMode(false);
            download_now.setText(getString(R.string.download_complete));
            btnDownload.setImageDrawable(getResources().getDrawable(R.drawable.install));
            btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    APKUtils_.getInstance_(MUMainActivity.this).installAPK(MUMainActivity.this,new File(downloadFile.getAbsolutePath()));
                }
            });
            speed.setVisibility(View.GONE);

            APKUtils_.getInstance_(this).installAPK(this, new File(downloadFile.getAbsolutePath()));
        }

    }

    @Receiver(actions = ConnectivityManager.CONNECTIVITY_ACTION)
    @UiThread
    void onNetWorkChanged() {
        if(NetWorkUtils.isNetworkConnected(this)){
            startDownload();
        }else
            pauseDownloadWhenNetworkException();
    }

    @UiThread
    void serverConnectionException() {
        Toast.makeText(this,netConnectException,Toast.LENGTH_LONG).show();
        dismissLoading();
        pauseDownloadWhenNetworkException();
    }

    @UiThread
    void netWorkException() {
        Toast.makeText(this,netExcption,Toast.LENGTH_LONG).show();
        dismissLoading();
        pauseDownloadWhenNetworkException();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //判断是否已安装，安装了就跳转到bbs
        //检测本地是否已存在
        if(!sysPref.packageName().get().equals("-1")&&APKUtils_.getInstance_(this).checkInstall(this,sysPref.packageName().get())){
            BBSActivity_.intent(this).start();
            finish();
        }
    }

    private long lastSize;
    private boolean first = true;

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(false);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
