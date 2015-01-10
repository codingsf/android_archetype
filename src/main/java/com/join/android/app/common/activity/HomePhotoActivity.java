package com.join.android.app.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.join.android.app.common.adapter.PhotoAdapter;
import com.join.android.app.common.data.ImageInfo;
import com.join.android.app.common.manager.DialogManager;
import com.kingnet.android.app.mugo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePhotoActivity extends Activity {
    public static final String PHOTO_NAME = "photo_name";
    public static final String PHOTO_PIC_LIST_PATH = "list_pic_path";
    private PhotoAdapter mAdapter;
    private List<ImageInfo> bitmaps = null;
    public static final int RESULT_CODE_OK = 301;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        GridView mDisplay = (GridView) findViewById(R.id.photo_display);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bitmaps = getThumbnailsPhotosInfo();
        mAdapter = new PhotoAdapter(this, bitmaps);
        mDisplay.setAdapter(mAdapter);
        mDisplay.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Intent intent = new Intent();
                intent.putExtra(PHOTO_NAME, bitmaps.get(position).getDisplayName());
                intent.putExtra(PHOTO_PIC_LIST_PATH, bitmaps.get(position).getTag());
                setResult(RESULT_CODE_OK, intent);
                HomePhotoActivity.this.finish();
            }
        });
    }

    public ArrayList<ImageInfo> getThumbnailsPhotosInfo() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            return getAlbumsInfo(cursor);
        } catch (Exception err) {
            err.printStackTrace();
            DialogManager.getInstance().makeText(this, getString(R.string.no_sdcard), DialogManager.DIALOG_TYPE_WARRING);
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    private ArrayList<ImageInfo> getAlbumsInfo(Cursor cursor) {
        HashMap<String, ImageInfo> albumsInfos = new HashMap<String, ImageInfo>();
        ImageInfo imageInfo;
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
            if (albumsInfos.containsKey(album)) {
                albumsInfos.get(album).getTag().add(path);
            } else {
                imageInfo = new ImageInfo();
                imageInfo.setDisplayName(album);
                imageInfo.setFirstPath(path);
                ArrayList<String> tag = new ArrayList<String>();
                tag.add(path);
                imageInfo.setTag(tag);
                albumsInfos.put(album, imageInfo);
            }
        }
        ArrayList<ImageInfo> bitmaps = new ArrayList<ImageInfo>();
        for (Map.Entry<String, ImageInfo> entry : albumsInfos.entrySet()) {
            bitmaps.add(entry.getValue());
        }
        return bitmaps;
    }
}
