package com.join.android.app.common.servcie;

import com.php25.PDownload.DownloadManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lala on 14/12/23.
 */
public class DownloadCoreServiceHelper {
    public static ExecutorService pools = Executors.newCachedThreadPool();

    public static void removeDownloadManager(String key) {
        DownloadManager downloadManager = getDownloadManager(key);
        map.remove(key);
        downloadManager.setStopped(true);
        downloadManager = null;
        futureMap.remove(key);
    }

    public static Map<String,Future> futureMap = new HashMap<String, Future>(0);
    public static Map<String, com.php25.PDownload.DownloadManager> map = new ConcurrentHashMap<String, DownloadManager>();
    public static void addDownloadManager(String key, DownloadManager downloadManager) {
        map.put(key, downloadManager);
    }

    public static DownloadManager getDownloadManager(String key) {
        return map.get(key);
    }



    public static boolean containsDownloadManager(String key) {
        return map.containsKey(key);
    }

    public static Map<String, Future> getFutureMap() {
        return futureMap;
    }

    public static void setFutureMap(Map<String, Future> _futureMap) {
        futureMap = _futureMap;
    }

    public static Future execute(Runnable runnable) {
        return pools.submit(runnable);
    }
}
