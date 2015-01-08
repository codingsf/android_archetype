package com.join.android.app.common.constants;

/**
 * Created by lala on 14/12/23.
 */
public class BroadcastAction {

    /**开始下载*/
    public final static String ACTION_DOWNLOAD_START = "com.join.android.app.common.broadcast.downloadStart";

    /**暂停下载*/
    public final static String ACTION_DOWNLOAD_PAUSE = "com.join.android.app.common.broadcast.downloadPause";

    /**删除下载*/
    public final static String ACTION_DOWNLOAD_DEL = "com.join.android.app.common.broadcast.downloadDel";

    /**下载完成*/
    public final static String ACTION_DOWNLOAD_COMPLETE = "com.join.android.app.common.broadcast.downloadComplete";

    /**网络异常,下载中断*/
    public final static String ACTION_DOWNLOAD_INTERRUPT = "com.join.android.app.common.broadcast.interrupt";

    /**下载进度更新*/
    public final static String ACTION_DOWNLOAD_UPDATE_PROGRESS = "com.join.android.app.common.broadcast.download_update_progress";

    /**网络异常*/
    public final static String ACTION_NETWORK_ERROR = "com.join.android.app.common.broadcast.network_error";


}
