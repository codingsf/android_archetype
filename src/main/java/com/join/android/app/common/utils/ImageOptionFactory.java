package com.join.android.app.common.utils;

import com.join.android.app.mgps.R;
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

    public static DisplayImageOptions getDefaultOptions() {
        if (defaultOptions == null) {
            defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).showImageOnLoading(R.drawable.default_image_placeholder).showImageOnFail(R.drawable.default_image_error).displayer(new FadeInBitmapDisplayer(500)).build();
        }
        return defaultOptions;
    }

    public static DisplayImageOptions getPortraitOptions() {

        if (portraitOptions == null) {
            portraitOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).showImageOnLoading(R.drawable.portrait_image_placeholder).showImageOnFail(R.drawable.default_image_error).displayer(new RoundedBitmapDisplayer(20)).imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
        }
        return portraitOptions;
    }
}
