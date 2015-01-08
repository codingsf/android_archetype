package com.join.android.app.common.activity;

import android.widget.ImageView;
import com.BaseActivity;
import com.join.android.app.mgps.R;
import com.join.android.app.common.utils.BitMapUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 下午4:00
 */
@EActivity(R.layout.image_cache_detail_activity_layout)
public class ImageCacheDetailActivity extends BaseActivity {

    @Extra
    String url;

    @ViewById
    ImageView img;

    @ViewById
    ImageView img1;

    @AfterViews
    void AfterViews() {
        ImageLoader.getInstance().displayImage(url,img);
        //load from resource by file name
        img1.setImageBitmap(BitMapUtils.getFromRes(this,"icon.png"));
    }

}
