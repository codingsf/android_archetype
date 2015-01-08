package com.php25.PDownload;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.join.android.app.common.enums.Dtype;
import org.androidannotations.annotations.EBean;
//import com.join.mobi.enums.Dtype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with mawanjin
 * User: mawanjin
 * Date: 14-9-11
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */
@EBean(scope = EBean.Scope.Singleton)
public class DownloadFileDao {
    private SQLiteDatabase database;

    public DownloadFileDao(Context context) {
        database = context.openOrCreateDatabase("com_php25_PDownload_metaFile.db", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS DownloadFile (id INTEGER PRIMARY KEY AUTOINCREMENT,tag VARCHAR,url VARCHAR,basePath VARCHAR,name VARCHAR,absolutePath VARCHAR,totalSize INTEGER,downloading SMALLINT,showName VARCHAR,createTime VARCHAR,dtype VARCHAR,finished SMALLINT,fileType VARCHAR,finishTime VARCHAR,packageName VARCHAR,portraitURL VARCHAR,percentage VARCHAR)");
    }

    public void close() {
        database.close();
    }

    public long insert(DownloadFile downloadFile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag",downloadFile.getTag());
        contentValues.put("url",downloadFile.getUrl());
        contentValues.put("basePath",downloadFile.getBasePath());
        contentValues.put("name",downloadFile.getName());
        contentValues.put("createTime",downloadFile.getCreateTime());
        contentValues.put("dtype",downloadFile.getDtype());
        contentValues.put("absolutePath",downloadFile.getAbsolutePath());
        contentValues.put("totalSize",downloadFile.getTotalSize());
        contentValues.put("downloading", downloadFile.getDownloading());
        contentValues.put("finished", downloadFile.getFinished());
        contentValues.put("showName", downloadFile.getShowName());
        contentValues.put("finishTime", downloadFile.getFinishTime());
        contentValues.put("fileType", downloadFile.getFileType());
        contentValues.put("packageName", downloadFile.getPackageName());
        contentValues.put("portraitURL", downloadFile.getPortraitURL());
        contentValues.put("percentage", downloadFile.getPercent());

        return database.insert("DownloadFile",null,contentValues);
    }

    public void update(DownloadFile downloadFile) {
        database.execSQL("update DownloadFile set downloading=? ,finished=? ,finishTime=?,totalSize=?,percentage=? where id=?", new Object[]{downloadFile.getDownloading(),downloadFile.getFinished(),downloadFile.getFinishTime(), downloadFile.getTotalSize(),downloadFile.getPercent(),downloadFile.getId()});
    }

    public void delete(DownloadFile downloadFile) {
        database.execSQL("delete from downloadFile where id=?", new Object[]{downloadFile.getId()});
    }

    public DownloadFile queryByTag(String tag) {
        DownloadFile downloadFile = null;
        Cursor cursor = database.rawQuery("select * from DownloadFile where tag=?", new String[]{tag});
        if (cursor.moveToNext()) {
            downloadFile = new DownloadFile();
            downloadFile.setId(cursor.getLong(cursor.getColumnIndex("id")));
            downloadFile.setTag(cursor.getString(cursor.getColumnIndex("tag")));
            downloadFile.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            downloadFile.setBasePath(cursor.getString(cursor.getColumnIndex("basePath")));
            downloadFile.setName(cursor.getString(cursor.getColumnIndex("name")));
            downloadFile.setShowName(cursor.getString(cursor.getColumnIndex("showName")));
            downloadFile.setFinishTime(cursor.getString(cursor.getColumnIndex("finishTime")));
            downloadFile.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            downloadFile.setDtype(cursor.getString(cursor.getColumnIndex("dtype")));
            downloadFile.setFileType(cursor.getString(cursor.getColumnIndex("fileType")));
            downloadFile.setAbsolutePath(cursor.getString(cursor.getColumnIndex("absolutePath")));
            downloadFile.setTotalSize(cursor.getInt(cursor.getColumnIndex("totalSize")));
            downloadFile.setPackageName(cursor.getString(cursor.getColumnIndex("packageName")));
            downloadFile.setPortraitURL(cursor.getString(cursor.getColumnIndex("portraitURL")));
            downloadFile.setPercent(cursor.getString(cursor.getColumnIndex("percentage")));


            short value = cursor.getShort(cursor.getColumnIndex("downloading"));
            short finished = cursor.getShort(cursor.getColumnIndex("finished"));
            switch (value) {
                case 0:
                    downloadFile.setDownloading(false);
                    break;
                case 1:
                    downloadFile.setDownloading(true);
                    break;
            }
            switch (finished) {
                case 0:
                    downloadFile.setFinished(false);
                    break;
                case 1:
                    downloadFile.setFinished(true);
                    break;
            }
        }
        cursor.close();
        return downloadFile;
    }

    /**
     * 获取所有下载任务，包括已下载和未下载的
     * @return
     */
    public List<DownloadFile> queryAll() {
        List<DownloadFile> downloadFiles = new ArrayList<DownloadFile>();
        Cursor cursor = database.rawQuery("select * from DownloadFile", null);
        while (cursor.moveToNext()) {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setId(cursor.getLong(cursor.getColumnIndex("id")));
            downloadFile.setTag(cursor.getString(cursor.getColumnIndex("tag")));
            downloadFile.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            downloadFile.setBasePath(cursor.getString(cursor.getColumnIndex("basePath")));
            downloadFile.setName(cursor.getString(cursor.getColumnIndex("name")));
            downloadFile.setShowName(cursor.getString(cursor.getColumnIndex("showName")));
            downloadFile.setFinishTime(cursor.getString(cursor.getColumnIndex("finishTime")));
            downloadFile.setDtype(cursor.getString(cursor.getColumnIndex("dtype")));
            downloadFile.setFileType(cursor.getString(cursor.getColumnIndex("fileType")));
            downloadFile.setAbsolutePath(cursor.getString(cursor.getColumnIndex("absolutePath")));
            downloadFile.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            downloadFile.setTotalSize(cursor.getInt(cursor.getColumnIndex("totalSize")));
            downloadFile.setPackageName(cursor.getString(cursor.getColumnIndex("packageName")));
            downloadFile.setPortraitURL(cursor.getString(cursor.getColumnIndex("portraitURL")));
            downloadFile.setPercent(cursor.getString(cursor.getColumnIndex("percentage")));

            short value = cursor.getShort(cursor.getColumnIndex("downloading"));
            short finished = cursor.getShort(cursor.getColumnIndex("finished"));
            switch (value) {
                case 0:
                    downloadFile.setDownloading(false);
                    break;
                case 1:
                    downloadFile.setDownloading(true);
                    break;
            }
            switch (finished) {
                case 0:
                    downloadFile.setFinished(false);
                    break;
                case 1:
                    downloadFile.setFinished(true);
                    break;
            }
            downloadFiles.add(downloadFile);
        }
        cursor.close();
        return downloadFiles;
    }

    /**
     * 获取所有下载未完成的任务
     * @return
     */
    public List<DownloadFile> queryAllDownloadingTask() {
        List<DownloadFile> downloadFiles = new ArrayList<DownloadFile>();
        Cursor cursor = database.rawQuery("select * from DownloadFile where finished=0", null);
        while (cursor.moveToNext()) {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setId(cursor.getLong(cursor.getColumnIndex("id")));
            downloadFile.setTag(cursor.getString(cursor.getColumnIndex("tag")));
            downloadFile.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            downloadFile.setBasePath(cursor.getString(cursor.getColumnIndex("basePath")));
            downloadFile.setName(cursor.getString(cursor.getColumnIndex("name")));
            downloadFile.setShowName(cursor.getString(cursor.getColumnIndex("showName")));
            downloadFile.setFinishTime(cursor.getString(cursor.getColumnIndex("finishTime")));
            downloadFile.setDtype(cursor.getString(cursor.getColumnIndex("dtype")));
            downloadFile.setFileType(cursor.getString(cursor.getColumnIndex("fileType")));
            downloadFile.setAbsolutePath(cursor.getString(cursor.getColumnIndex("absolutePath")));
            downloadFile.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            downloadFile.setTotalSize(cursor.getInt(cursor.getColumnIndex("totalSize")));
            downloadFile.setPackageName(cursor.getString(cursor.getColumnIndex("packageName")));
            downloadFile.setPortraitURL(cursor.getString(cursor.getColumnIndex("portraitURL")));
            downloadFile.setPercent(cursor.getString(cursor.getColumnIndex("percentage")));

            short value = cursor.getShort(cursor.getColumnIndex("downloading"));
            short finished = cursor.getShort(cursor.getColumnIndex("finished"));
            switch (value) {
                case 0:
                    downloadFile.setDownloading(false);
                    break;
                case 1:
                    downloadFile.setDownloading(true);
                    break;
            }

            switch (finished) {
                case 0:
                    downloadFile.setFinished(false);
                    break;
                case 1:
                    downloadFile.setFinished(true);
                    break;
            }
            downloadFiles.add(downloadFile);
        }
        cursor.close();
        return downloadFiles;
    }

    /**
     * 获取所有已下载完成的任务,如果Dtype为null则取出所有类型的任务
     * @param dtype
     * @return
     */
    public List<DownloadFile> queryAllDownloaded(Dtype dtype) {

        List<DownloadFile> downloadFiles = new ArrayList<DownloadFile>();
        Cursor cursor = null;
        if(dtype==null){
            cursor = database.rawQuery("select * from DownloadFile where finished =1 ",null);
        }else
            cursor = database.rawQuery("select * from DownloadFile where dtype=? and finished =1 ", new String[]{dtype.name()});

        while (cursor.moveToNext()) {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setId(cursor.getLong(cursor.getColumnIndex("id")));
            downloadFile.setTag(cursor.getString(cursor.getColumnIndex("tag")));
            downloadFile.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            downloadFile.setBasePath(cursor.getString(cursor.getColumnIndex("basePath")));
            downloadFile.setName(cursor.getString(cursor.getColumnIndex("name")));
            downloadFile.setShowName(cursor.getString(cursor.getColumnIndex("showName")));
            downloadFile.setFinishTime(cursor.getString(cursor.getColumnIndex("finishTime")));
            downloadFile.setDtype(cursor.getString(cursor.getColumnIndex("dtype")));
            downloadFile.setFileType(cursor.getString(cursor.getColumnIndex("fileType")));
            downloadFile.setAbsolutePath(cursor.getString(cursor.getColumnIndex("absolutePath")));
            downloadFile.setCreateTime(cursor.getString(cursor.getColumnIndex("createTime")));
            downloadFile.setTotalSize(cursor.getInt(cursor.getColumnIndex("totalSize")));
            downloadFile.setPackageName(cursor.getString(cursor.getColumnIndex("packageName")));
            downloadFile.setPortraitURL(cursor.getString(cursor.getColumnIndex("portraitURL")));
            downloadFile.setPercent(cursor.getString(cursor.getColumnIndex("percentage")));

            short value = cursor.getShort(cursor.getColumnIndex("downloading"));
            short finished = cursor.getShort(cursor.getColumnIndex("finished"));
            switch (value) {
                case 0:
                    downloadFile.setDownloading(false);
                    break;
                case 1:
                    downloadFile.setDownloading(true);
                    break;
            }
            switch (finished) {
                case 0:
                    downloadFile.setFinished(false);
                    break;
                case 1:
                    downloadFile.setFinished(true);
                    break;
            }
            downloadFiles.add(downloadFile);
        }
        cursor.close();
        return downloadFiles;
    }
}
