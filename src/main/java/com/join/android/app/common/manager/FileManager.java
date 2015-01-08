package com.join.android.app.common.manager;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileManager {

    private Context context;
    private static FileManager manager;

    private File dataPath;


    private FileManager(Context context) {
        this.context = context;

        dataPath = context.getFilesDir();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dataPath = context.getExternalFilesDir("data");
        }
    }

    public static FileManager getInstance(Context context) {
        if (manager == null) manager = new FileManager(context);
        return manager;
    }


    public File createTmpFile(String fileName) {
        File file = new File(String.format("%s/%s", getTempPath(), fileName));
        return file;
    }

    private String getTempPath() {
        String root = createOrGetDir(String.format("%s/temp/", dataPath.getPath()));
        return root;
    }

    private String createOrGetDir(String filepath) {
        File file = new File(filepath);
        if (file.exists()) {
            return file.getPath();
        }
        file.mkdirs();
        return file.getPath();
    }

}