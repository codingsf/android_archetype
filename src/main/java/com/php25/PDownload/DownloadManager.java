package com.php25.PDownload;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import com.join.android.app.common.constants.BroadcastAction;
import com.join.android.app.common.enums.Dtype;
import com.join.android.app.common.servcie.DownloadCoreServiceHelper;
import com.join.android.app.common.utils.DateUtils;
import com.php25.tools.DigestTool;
import org.androidannotations.annotations.Bean;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Future;

/**
 * Created with mawanjin
 * User: mawanjin
 * Date: 14-9-1
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public class DownloadManager {

    private String TAG = DownloadManager.class.getName();

    private String basePath;

    private boolean finished;

    private boolean stopped;

    private boolean stoppedProgress;

    private DownloadHandler downloadHandler;

    private Context context;

    public synchronized boolean isFinished() {
        return finished;
    }

    public synchronized void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isStoppedProgress() {
        return stoppedProgress;
    }

    public void setStoppedProgress(boolean stoppedProgress) {
        this.stoppedProgress = stoppedProgress;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public DownloadHandler getDownloadHandler() {
        return downloadHandler;
    }

    public void setDownloadHandler(DownloadHandler downloadHandler) {
        this.downloadHandler = downloadHandler;
    }

    public DownloadManager(DownloadHandler downloadHandler, Context context) {
        this.downloadHandler = downloadHandler;
        this.context = context;
        this.basePath = context.getExternalFilesDir("").getAbsolutePath();
        this.setStopped(true);
//        app = (DownloadApplication) context.getApplicationContext();
    }

    public DownloadManager(Context context) {
        this.context = context;
        this.basePath = context.getExternalFilesDir("").getAbsolutePath();
        this.setStopped(true);
//        app = (DownloadApplication) context.getApplicationContext();
    }


    public void download(final String url,final String portraitURL, final String showName, final String packageName ,final Dtype dtype, final String fileType, final DownloadFileDao downloadFileDao, final DownloadTool.DownloadToolCallback callback) {
        if (!isStopped()) {
            Log.d(TAG,"download is going on...");
            return;
        }
        this.setStopped(false);

        DownloadCoreServiceHelper.execute(new Runnable() {
            @Override
            public void run() {
                DownloadFile temp = null;
                InputStream in = null;
                OutputStream out = null;

                ObjectInputStream inputStream = null;
                ObjectOutputStream outputStream = null;
                try {
                    URL u = new URL(url);
                    URLConnection conn = u.openConnection();
                    conn.setDoOutput(false);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(30000);

                    String filePath = null;
                    String fileName = null;

                        filePath = basePath + "/" + DigestTool.md5(url);
                        fileName = DigestTool.md5(url);

                    final File downloadFile = new File(filePath);
                    //创建meta文件
                    temp  = downloadFileDao.queryByTag(DigestTool.md5(url));
                    if(temp == null) {
                        temp = new DownloadFile();
                        temp.setAbsolutePath(filePath);
                        temp.setBasePath(basePath);
                        temp.setTag(DigestTool.md5(url));
                        temp.setUrl(url);
                        temp.setName(fileName);
                        temp.setShowName(showName);
                        temp.setDtype(dtype.name());
                        temp.setPortraitURL(portraitURL);
                        temp.setDownloading(true);
                        temp.setFinished(false);
                        temp.setFileType(fileType);
                        temp.setPackageName(packageName);
                        temp.setCreateTime(DateUtils.FormatForNormalTimeFromMil(System.currentTimeMillis()));

                        long id = downloadFileDao.insert(temp);
                        temp.setId(id);
                        out = new FileOutputStream(downloadFile);
                    } else {
                        File ff = new File(temp.getAbsolutePath());

                        if(temp.getTotalSize()==ff.length()){
                            temp.setFinished(true);
                            temp.setFinishTime(DateUtils.FormatForNormalTimeFromMil(System.currentTimeMillis()));
                            downloadFileDao.update(temp);
                            DownloadCoreServiceHelper.removeDownloadManager(temp.getTag());
                            Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_COMPLETE);
                            intent.putExtra("file",temp);
                            context.sendBroadcast(intent);
                            return;
                        }else{
                            conn.setRequestProperty("RANGE", "bytes="+ff.length()+"-");
                            out = new FileOutputStream(downloadFile,true);
                        }
                    }
                    callback.afterStartDownload(temp);

                    String contentType = conn.getContentType();
                    final int contentLength = conn.getContentLength();
                    if(temp.getTotalSize()==null||temp.getTotalSize()==0){
                        temp.setTotalSize(contentLength);
                    }


                    if (contentType == null) {
                        contentType = conn.guessContentTypeFromName(url);
                    }

                    if (contentType != null && contentType.startsWith("text/") || contentLength == -1) {
                        throw new Exception("This is not a binary file.");
                    }

                    downloadFileDao.update(temp);

                    //开始下载
                    in = conn.getInputStream();

                    byte buff[] = new byte[1024];
                    int pos = 0;
                    while (!isStopped() && ((pos = in.read(buff)) != -1)) {
                        out.write(buff, 0, pos);
                        out.flush();
                    }
                    if (!isStopped()) {
                        setFinished(true);
                    } else {
                        temp.setDownloading(false);
                        downloadFileDao.update(temp);
                    }

                    if (isFinished()) {
                        temp.setFinished(true);
                        temp.setFinishTime(DateUtils.FormatForNormalTimeFromMil(System.currentTimeMillis()));
                        downloadFileDao.update(temp);
                        DownloadCoreServiceHelper.removeDownloadManager(temp.getTag());
                        Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_COMPLETE);
                        intent.putExtra("file",temp);
                        context.sendBroadcast(intent);
                    }
                    in.close();
                    out.close();
                    Log.v(TAG, "end download");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    //发生异常暂停下载
                    DownloadCoreServiceHelper.removeDownloadManager(temp.getTag());
                    Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_INTERRUPT);
                    intent.putExtra("file",temp);
                    context.sendBroadcast(intent);
                    throw new RuntimeException(e);
                }catch (Exception e){
                    //发生异常暂停下载
                    DownloadCoreServiceHelper.removeDownloadManager(temp.getTag());
                    Intent intent = new Intent(BroadcastAction.ACTION_DOWNLOAD_INTERRUPT);
                    intent.putExtra("file",temp);
                    context.sendBroadcast(intent);
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                finally {
                    try {
                        if (null != in) {
                            in.close();
                            in = null;
                        }

                        if (null != out) {
                            out.close();
                            out = null;
                        }

                        if (null != inputStream) {
                            inputStream.close();
                            inputStream = null;
                        }

                        if (null != outputStream) {
                            outputStream.close();
                            outputStream = null;
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }

    public Future updateDownloadProgress(final DownloadFile file) {
        final File downloadFile = new File(file.getAbsolutePath());
        //下载进度提示
        Future future = DownloadCoreServiceHelper.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!isFinished() && !stopped) {
                        if (null != downloadHandler) {
                            float filelength = downloadFile.length();

                            if (file.getTotalSize() == null || new Float(file.getTotalSize()) == 0) {
                                continue;
                            }
                            float totalSize = new Float(file.getTotalSize());
                            float p = filelength / totalSize;
                            downloadHandler.updateProcess(p);
                        }
                        Thread.sleep(2000);
                    }
                    if (isFinished()) {
                        if (null != downloadHandler)
                            downloadHandler.finished();
                        Log.v(TAG, "download has finished!!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        });
        return future;
    }



}
