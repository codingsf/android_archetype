package com.php25.PDownload;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.join.android.app.common.constants.BroadcastAction;
import com.php25.tools.DigestTool;

import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created with mawanjin
 * User: mawanjin
 * Date: 14-9-10
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
public class DownloadTool {
    public static final String TAG = DownloadTool.class.getName();

    /**
     * 开启一个下载线程
     * @param context
     * @param url 下载地址
     * @param portraitURL 图片地址
     * @param showName 显示名称
     * @param dtype 大类目 目前统一用APK
     * @param fileType 类型 目前统一用APK
     */
//    public synchronized static void startDownload(DownloadApplication context, String url, String portraitURL,String showName, Dtype dtype, String fileType,DownloadToolCallback callback) {
//        if (context.containsDownloadManager(DigestTool.md5(url))) {
//            DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(url));
//            downloadManager.download(url,portraitURL, showName, dtype, fileType,downloadFileDao, callback);
//        } else {
//            DownloadManager downloadManager = new DownloadManager(context);
//            context.addDownloadManager(DigestTool.md5(url), downloadManager);
//            downloadManager.download(url,portraitURL, showName, dtype, fileType,downloadFileDao,callback);
//        }
//    }

    /**
     * 正在下载和已下载完成，这两种情况都会返回true.如果你想判断是否在下载状态请用方法:isDownloadingNow
     *
     * @param context
     * @param url
     * @return
     */
//    public static boolean isDownloading(DownloadApplication context, String url) {
//        if (context.containsDownloadManager(DigestTool.md5(url)) || isFinished(context, url)) return true;
//        return false;
//    }
//
//    public static boolean isDownloadingNow(DownloadApplication context, String url) {
//        if (context.containsDownloadManager(DigestTool.md5(url)) && !isStopped(context, url)) return true;
//        return false;
//    }


    /**
     * 开启下载进度更新线程
     *
     * @param context
     * @param handler
     * @param downloadFile
     * @return
     */
//    public static Future updateDownloadProgress(DownloadApplication context, DownloadHandler handler, DownloadFile downloadFile) {
//        final DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(downloadFile.getUrl()));
//        downloadManager.setDownloadHandler(handler);
//        return downloadManager.updateDownloadProgress(downloadFile);
//    }
//
//    public static void stopUpdateProgress(DownloadApplication context, DownloadFile downloadFile) {
//        final DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(downloadFile.getUrl()));
//        if (downloadManager != null)
//            downloadManager.setStopped(true);
//    }

    /**
     * 停止下载线程
     *
     * @param context
     * @param downloadFile
     */
//    public static void stopDownload(DownloadApplication context, DownloadFile downloadFile) {
//        DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(downloadFile.getUrl()));
//        if (downloadManager != null) {
//            downloadManager.setStopped(true);
//            context.removeDownloadManager(DigestTool.md5(downloadFile.getUrl()));
//            context.getFutureMap().remove(downloadFile.getTag());
//            int size = context.getFutureMap().size();
//            Log.d(TAG,"size="+size);
//        }
//
//    }

    /**
     * 判断下载线程是否停止
     *
     * @param context
     * @param url
     * @return
//     */
//    public static boolean isStopped(DownloadApplication context, String url) {
//        DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(url));
//        return downloadManager.isStopped();
//    }

    /**
     * 获取所有需要下载的任务列表
     *
     * @param context
     * @return
//     */
//    public static List<DownloadFile> getAllDownloadingTask(DownloadApplication context) {
//        return context.getDownloadFileDao().queryAllDownloadingTask();
//    }
//
//    /**
//     * 获取所有下载的任务列表
//     *
//     * @param context
//     * @return
//     */
//    public static List<DownloadFile> getAllDownload(DownloadApplication context) {
//        return context.getDownloadFileDao().queryAll();
//    }
//
//    /**
//     * 获取所有已下载的文件
//     *
//     * @param context
//     * @return
//     */
//    public static List<DownloadFile> getAllDownloaded(DownloadApplication context, Dtype dtype) {
//        return context.getDownloadFileDao().queryAllDownloaded(dtype);
//    }
//
//    /**
//     * 获取所有已下载的文件
//     *
//     * @param context
//     * @return
//     */
//    public static List<DownloadFile> getAllDownloaded(DownloadApplication context) {
//        return context.getDownloadFileDao().queryAllDownloaded(null);
//    }

    /**
     * 获取当前url是否已经下载完了。
     *
     * @param
     * @return
     */
//    public static boolean isFinished(DownloadApplication context, String url) {
//        DownloadFile temp = context.getDownloadFileDao().queryByTag(DigestTool.md5(url));
//        if (temp == null) return false;
//        return temp.getFinished();
//    }
//
//    /**
//     * 删除下载任务,包括数据库和文件(若有)
//     */
//    public static void delDownloadTask(DownloadApplication context, DownloadFile downloadFile) {
//        if(downloadFile==null)return;
//        //停止下载任务
//        stopDownload(context, downloadFile);
//
//        //删除文件
//        File file = new File(downloadFile.getAbsolutePath());
//        if (file.exists()) file.delete();
//
//        //删除数据库中的记录
//        context.getDownloadFileDao().delete(downloadFile);
//
//    }
//
//    /**
//     * 根据url删除文件
//     */
//    public static void deleteDownloadTask(DownloadApplication context, String url) {
//        delDownloadTask(context, context.getDownloadFileDao().queryByTag(DigestTool.md5(url)));
//    }
//
//    /***
//     * 根据url来得到该url对应的文件
//     */
//    public static String getFileByUrl(DownloadApplication context,String url){
//         DownloadFile file = context.getDownloadFileDao().queryByTag(DigestTool.md5(url));
//        if(file==null)return "";
//        return file.getAbsolutePath();
//    }
//
//    public static DownloadFile getDownLoadFile(DownloadApplication context,String url) {
//        DownloadFile file = context.getDownloadFileDao().queryByTag(DigestTool.md5(url));
//        return file;
//    }


    public static String Extra_DOWNLOAD_File = "downloadFile";

   public static void download(Context context,DownloadFile downloadFile){
       Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_START);
       intent.putExtra(Extra_DOWNLOAD_File,downloadFile);
       context.sendBroadcast(intent);
   }

    public static void pause(Context context,DownloadFile downloadFile){
       Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_PAUSE);
       intent.putExtra(Extra_DOWNLOAD_File,downloadFile);
       context.sendBroadcast(intent);
   }

    public static void del(Context context,DownloadFile downloadFile){
       Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_DEL);
       intent.putExtra(Extra_DOWNLOAD_File,downloadFile);
       context.sendBroadcast(intent);
   }



    public interface DownloadToolCallback{
        public void afterStartDownload(DownloadFile downloadFile);
    }

}
