package com.join.android.app.common.utils;

import android.content.Context;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;


/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 下午5:03
 */
public class CacheManager {
    public static void clearAll(Context context) {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiscCache();
    }


    public static void clear(String url) {

        MemoryCacheUtils.removeFromCache(url, ImageLoader.getInstance().getMemoryCache());
        DiskCacheUtils.removeFromCache(url, ImageLoader.getInstance().getDiscCache());

    }
}
