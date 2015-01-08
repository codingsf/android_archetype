package com.join.android.app.common.manager;

import android.os.AsyncTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: xingsen@join-cn.com
 * Date: 13-6-28
 * Time: 上午9:03
 */
public class TaskManager {
    private ExecutorService executor;
    private static TaskManager manager;

    public static TaskManager getInstance() {
        if (manager == null) manager = new TaskManager(10);
        return manager;
    }

    private TaskManager(int nThreads) {
        executor = Executors.newFixedThreadPool(nThreads);
    }

    public <Params, Progress, Result> AsyncTask<Params, Progress, Result> submit(AsyncTask<Params, Progress, Result> task, Params... params) {
        return task.executeOnExecutor(executor, params);
    }
}
