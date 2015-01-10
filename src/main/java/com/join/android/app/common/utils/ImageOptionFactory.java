package com.join.android.app.common.utils;

import com.kingnet.android.app.mugo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-26
 * Time: 上午10:27
 */

public class ImageOptionFactory {
    private static DisplayImageOptions defaultOptions;
    private static DisplayImageOptions portraitOptions;
    private static DisplayImageOptions adOptions;

    public static DisplayImageOptions getDefaultOptions() {
        if (defaultOptions == null) {
            defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).showImageOnLoading(R.drawable.ad_default).showImageOnFail(R.drawable.ad_default).displayer(new FadeInBitmapDisplayer(500)).build();
        }
        return defaultOptions;
    }

    /**
     * 广告
     *
     * @return
     */
    public static DisplayImageOptions getADDialogOptions() {
        if (adOptions == null) {
            adOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).showImageOnLoading(R.drawable.ad_default).showImageOnFail(R.drawable.ad_default).displayer(new FadeInBitmapDisplayer(500)).build();
        }
        return adOptions;
    }

    public static DisplayImageOptions getPortraitOptions() {

        if (portraitOptions == null) {
            portraitOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).showImageOnLoading(R.drawable.ad_default).showImageOnFail(R.drawable.ad_default).displayer(new RoundedBitmapDisplayer(20)).imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
        }
        return portraitOptions;
    }
}
