package com.join.android.app.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import com.join.android.app.common.manager.DialogManager;
import com.join.android.app.common.manager.FileManager;
import com.join.android.app.common.utils.BitMapUtils;
import com.join.android.app.common.view.CropImage;
import com.join.android.app.common.view.CropImageView;
import com.kingnet.android.app.mugo.R;

import java.io.*;


public class ImageFilterCropActivity extends Activity {
    public static final String PATH = "path";
    private static final String CAT_IMG = "cat_img";
    public static final int IMAGE_CUL_RESULT_OK = 205;
    private Button mCancel;
    private Button mDetermine;
    private CropImageView mDisplay;
    private ProgressBar mProgressBar;
    private Button mLeft;
    private Button mRight;

    public static final int SHOW_PROGRESS = 0;
    public static final int REMOVE_PROGRESS = 1;

    private String mPath;// 修改的图片地址
    private Bitmap mBitmap;// 修改的图片
    private CropImage mCropImage; // 裁剪工具类
    private DialogManager dialogManager = DialogManager.getInstance();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.imagefilter_crop_activity);

//        findViewById();
        setListener();
        init();
    }

//    private void findViewById() {
//        mCancel = (Button) findViewById(R.id.imagefilter_crop_cancel);
//        mDetermine = (Button) findViewById(R.id.imagefilter_crop_determine);
//        mDisplay = (CropImageView) findViewById(R.id.imagefilter_crop_display);
//        mProgressBar = (ProgressBar) findViewById(R.id.imagefilter_crop_progressbar);
//        mLeft = (Button) findViewById(R.id.imagefilter_crop_left);
//        mRight = (Button) findViewById(R.id.imagefilter_crop_right);
//    }

    //保存裁剪后的图片到本地
    public String saveCutPic(Bitmap bitmap) {

        FileOutputStream fos = null;
        try {
            File file = FileManager.getInstance(this).createTmpFile(CAT_IMG + ".png");
            fos = new FileOutputStream(file);
            compressImage(bitmap,fos);
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void setListener() {
        mCancel.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
        mDetermine.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 保存修改的图片到本地,并返回图片地址
                mPath = saveCutPic(mCropImage.cropAndSave());
                Intent intent = new Intent();
                intent.putExtra(PATH, mPath);
                setResult(IMAGE_CUL_RESULT_OK, intent);
                finish();
            }
        });
        mLeft.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 左旋转
                mCropImage.startRotate(270.f);
            }
        });
        mRight.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // 有旋转
                mCropImage.startRotate(90.f);
            }
        });
    }

    private void init() {
        // 接收传递的图片地址
        mPath = getIntent().getStringExtra(PATH);
        try {
            // 获取修改的图片
            mBitmap = BitMapUtils.getBitmap(this, mPath, dialogManager.windowWidth(this), dialogManager.windowHeight(this));
            // 如果图片不存在,则返回,存在则初始化
            if (mBitmap == null) {
                dialogManager.makeText(this, "没有找到图片", DialogManager.DIALOG_TYPE_ERROR);
                finish();
            } else {
                resetImageView(mBitmap);
            }
        } catch (Exception e) {
            dialogManager.makeText(this, "没有找到图片", DialogManager.DIALOG_TYPE_ERROR);
            finish();
        }
    }

    /**
     * 初始化图片显示
     *
     * @param b
     */
    private void resetImageView(Bitmap b) {
        mDisplay.clear();
        mDisplay.setImageBitmap(b);
        mDisplay.setImageBitmapResetBase(b, true);
        mCropImage = new CropImage(this, mDisplay, handler);
        mCropImage.crop(b);
    }

    private void compressImage(Bitmap image,FileOutputStream fos) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>80) {	//循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        byte[] bitmapdata = baos.toByteArray();
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 控制进度条
     */
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_PROGRESS:
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case REMOVE_PROGRESS:
                    handler.removeMessages(SHOW_PROGRESS);
                    mProgressBar.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };
}
