package com.join.android.app.common.servcie;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;
import com.join.android.app.common.constants.BroadcastAction;
import com.join.android.app.common.enums.Dtype;
import com.join.android.app.common.utils.NetWorkUtils;
import com.php25.PDownload.*;
import com.php25.tools.DigestTool;
import org.androidannotations.annotations.*;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by lala on 14/12/23.
 */
@EService
public class DownloadCoreService extends Service {
    String TAG = getClass().getName();
    @Bean
    DownloadFileDao downloadFileDao;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 开始下载或继续下载
     * @param downloadFile
     */
    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_START)
    void downLoad(@Receiver.Extra DownloadFile downloadFile) {
        Log.d(TAG, "method downLoad() called.");
        if(downloadFile==null)return;
        DownloadManager downloadManager;
        String tag = DigestTool.md5(downloadFile.getUrl());
        if (DownloadCoreServiceHelper.containsDownloadManager(tag)) {
            downloadManager = DownloadCoreServiceHelper.getDownloadManager(tag);
        } else {
            downloadManager = new DownloadManager(getApplicationContext());
            DownloadCoreServiceHelper.addDownloadManager(tag, downloadManager);
        }
        downloadManager.download(downloadFile.getUrl(),downloadFile.getPortraitURL(), downloadFile.getShowName(),downloadFile.getPackageName(), Dtype.APK, "apk",downloadFileDao, new DownloadTool.DownloadToolCallback() {
            @Override
            public void afterStartDownload(DownloadFile downloadFile) {
                updateDownloadProgress(downloadFile);
            }
        });
    }

    /**
     * 暂停下载
     * @param downloadFile
     */
    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_PAUSE)
    void pause(@Receiver.Extra DownloadFile downloadFile){
        DownloadManager downloadManager = DownloadCoreServiceHelper.getDownloadManager(DigestTool.md5(downloadFile.getUrl()));
        if (downloadManager != null) {
            downloadManager.setStopped(true);
            DownloadCoreServiceHelper.removeDownloadManager(DigestTool.md5(downloadFile.getUrl()));
        }
        DownloadCoreServiceHelper.getFutureMap().remove(downloadFile.getTag());
        Log.d(TAG, downloadFile.getShowName()+" 's downloading stopped ...["+downloadFile.getPercent()+"]");
    }

    /**
     * 删除任务、文件、数据库记录
     * @param downloadFile
     */
    @Receiver(actions = BroadcastAction.ACTION_DOWNLOAD_DEL)
    void delete(@Receiver.Extra DownloadFile downloadFile){
        //停止下载任务
        pause(downloadFile);
        downloadFile = downloadFileDao.queryByTag(DigestTool.md5(downloadFile.getUrl()));
        //删除文件
        if(downloadFile!=null){
            File file = new File(downloadFile.getAbsolutePath());
            if (file.exists()) file.delete();
            //删除数据库中的记录
            downloadFileDao.delete(downloadFile);
        }
    }


    /**
     * 跟踪下载进度
     * @param downloadFile
     */

    public void updateDownloadProgress(final DownloadFile downloadFile) {

        Map<String,Future> futureMap = DownloadCoreServiceHelper.getFutureMap();
        Object t = futureMap.get(downloadFile.getTag());
        if(t == null){
            DownloadManager downloadManager = DownloadCoreServiceHelper.getDownloadManager(downloadFile.getTag());
            if(downloadManager == null)return;
            downloadManager.setDownloadHandler(new DownloadHandler() {

                @Override
                public void updateProcess(float process) {
                    if(Float.isNaN(process)){
                        downloadFile.setPercent("0%");
                    }else{
                        String p = (((process)*100)+"");
                        if(p.length()>4)
                            p = p.substring(0,4);

                        downloadFile.setPercent(p+"%");

                    }
                    Log.d(TAG, downloadFile.getShowName()+" is downloading...["+downloadFile.getPercent()+"]");
                    Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_UPDATE_PROGRESS);
//                    intent.putExtra("percent",downloadFile.getPercent());
                    downloadFileDao.update(downloadFile);
                    intent.putExtra("file",downloadFile);
                    sendBroadcast(intent);
                }

                @Override
                public void finished() {
                    downloadFile.setPercent("100%");
                    Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_COMPLETE);
                    intent.putExtra("file",downloadFile);
                    sendBroadcast(intent);
                    Log.d(TAG, downloadFile.getShowName()+" download completed !");
                }

            });

            Future future = downloadManager.updateDownloadProgress(downloadFile);
            futureMap.put(downloadFile.getTag(),future);
        }
    }

    @Receiver(actions = ConnectivityManager.CONNECTIVITY_ACTION)
    @UiThread
    void onConnectivityChanged(Intent intent){
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            boolean isConnected = NetWorkUtils.isNetworkConnected(getApplicationContext());
                Log.d(TAG, "网络状态：" + isConnected);
                Log.d(TAG, "wifi状态：" + NetWorkUtils.isWifiConnected(getApplicationContext()));
                Log.d(TAG, "移动网络状态：" + NetWorkUtils.isMobileConnected(getApplicationContext()));
                Log.d(TAG, "网络连接类型：" + NetWorkUtils.getConnectedType(getApplicationContext()));
            if (isConnected) {
                Log.d(TAG, "已经连接网络");
            } else {
                Log.d(TAG, "已经断开网络");
            }
        }
    }

}
