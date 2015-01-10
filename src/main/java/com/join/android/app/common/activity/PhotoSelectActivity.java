package com.join.android.app.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import com.BaseActivity;
import com.join.android.app.common.manager.PhotoManager;
import com.kingnet.android.app.mugo.R;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-26
 * Time: 下午5:27
 */
@EActivity(R.layout.photo_select_activity)
public class PhotoSelectActivity extends BaseActivity {
    public static final int REQUEST_CODE = 1001;

    @ViewById
    Button btnSelect;

    private boolean flag;

    @Click
    void btnSelectClicked() {
        PhotoManager.getInstance(this).showPhotoPopupWindow();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_OK)
            return;
        if (resultCode == ImageFilterCropActivity.IMAGE_CUL_RESULT_OK) {
            String photo = data.getStringExtra(ImageFilterCropActivity.PATH);
            if (photo != null) {
                flag = false;
                //do something .like upload photo
//                TaskManager.getInstance().submit(new UploadPhotoFileTask(this, handler), photo, userInfo.getUserID());
            }
        } else if (requestCode == PhotoManager.IMAGE_CODE) {//拍照
            PhotoManager.getInstance(this).cutPhoto(PhotoManager.getInstance(this).getFilePath(), this);
        } else if (resultCode == HomePicActivity.RESULT_CODE_OK) {
            PhotoManager.getInstance(this).cutPhoto(data.getStringExtra(HomePicActivity.SELECT_PHOTO_PIC_PATH), this);
        }
    }


}
