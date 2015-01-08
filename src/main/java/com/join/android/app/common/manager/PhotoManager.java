package com.join.android.app.common.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.LruCache;
import com.join.android.app.common.activity.HomePicActivity;
import com.join.android.app.common.activity.ImageFilterCropActivity;
import com.join.android.app.common.pop.PhotoPopupWindow;

import java.io.File;

/**
 * User: mawanjin@join-cn.com
 * Date: 10/24/13
 * Time: 1:23 下午
 */
public class PhotoManager {

    private static PhotoManager manager;
    private PhotoPopupWindow photoPopupWindow;

    private FileManager fileManager;
    public static final int IMAGE_CODE = 302; //拍照
    public static final String PIC_PNG = "_pic.png";
    private static String path;

    private PhotoManager(Context context) {
        fileManager = FileManager.getInstance(context);
        photoPopupWindow = new PhotoPopupWindow(context);
        photoPopupWindow.setFocusable(true);
        photoPopupWindow.setOutsideTouchable(true);
    }
    public void showPhotoPopupWindow(){
        photoPopupWindow.show();
    }

    public static PhotoManager getInstance(Context context) {
        return manager = new PhotoManager(context);
    }


    public String getFilePath() {
        return path;
    }

    //拍照
    public void doTakePhoto(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = fileManager.createTmpFile(System.currentTimeMillis() + PIC_PNG);
        path = file.getAbsolutePath();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(intent, IMAGE_CODE);
    }

    //裁剪图片
    public void cutPhoto(String url, Activity activity) {
        if (url != null) {
            Intent intent = new Intent(activity, ImageFilterCropActivity.class);
            intent.putExtra(ImageFilterCropActivity.PATH, url);
            activity.startActivityForResult(intent, 0);
        }
    }

    /**
     * @param isSingle 是否是多选
     * @param count    最多可以选多少个，0  可以无限选
     */
    //查看本地相册
    private void doPhoto(Activity activity, boolean isSingle, int count) {

        Intent intent = new Intent(activity, HomePicActivity.class);
        intent.putExtra(HomePicActivity.SELECT_TYPE, isSingle);
        intent.putExtra(HomePicActivity.PHOTO_PIC_COUNT, count);
        activity.startActivityForResult(intent, 0);
    }

    public void doPhotoSingle(Activity activity) {
        doPhoto(activity, true, 0);
    }

    public LruCache<String, Bitmap> initLruCache() {
        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        // 设置图片缓存大小为程序最大可用内存的1/8
        return new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

}
