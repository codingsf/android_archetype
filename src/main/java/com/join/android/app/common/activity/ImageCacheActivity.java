package com.join.android.app.common.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import com.BaseActivity;
import com.kingnet.android.app.mugo.R;
import com.join.android.app.common.adapter.SampleListDetailAdapter;
import com.join.android.app.common.utils.CacheManager;
import com.join.android.app.common.utils.ImageOptionFactory;
import com.join.android.app.common.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;


import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import org.androidannotations.annotations.*;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 下午1:41
 */
@EActivity(R.layout.image_cache_activity_layout)
public class ImageCacheActivity extends BaseActivity {


    @ViewById
    ListView listView;

    @ViewById
    ImageView img1;

    @ViewById
    ImageView img2;

    @ViewById
    RoundImageView img3;

    @ViewById
    Button clearAllBtn;

    @AfterViews
    void AfterViews() {
        ImageLoader.getInstance().displayImage("http://imgstatic.baidu.com/img/image/qiche320s.jpg", img1);
        ImageLoader.getInstance().displayImage("http://imgstatic.baidu.com/img/image/qiche320s.jpg", img2, ImageOptionFactory.getPortraitOptions(), null, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {

            }
        });
        ImageLoader.getInstance().displayImage("http://imgstatic.baidu.com/img/image/qiche320s.jpg", img3);
        listView.setAdapter(new SampleListDetailAdapter(this));
        boolean pauseOnScroll = false;
        boolean pauseOnFling = true;
        PauseOnScrollListener listener = new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling);
        listView.setOnScrollListener(listener);


    }

    @ItemClick(R.id.listView)
    void listItemClicked(String url) {
        ImageCacheDetailActivity_.intent(this).url(url).start();
    }

    @Click
    void clearAllBtnClicked() {
        CacheManager.clear("http://www.baidu.com");
        CacheManager.clearAll(this);
    }

}
