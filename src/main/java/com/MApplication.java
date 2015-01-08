package com;

import android.app.Application;
import com.join.android.app.common.db.manager.DBManager;
import com.join.android.app.common.servcie.DownloadCoreService_;
import com.join.android.app.common.utils.ImageOptionFactory;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import org.androidannotations.annotations.EApplication;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-11
 * Time: 下午4:58
 */
@EApplication
public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initCache();
        DBManager.getInstance(this).createDB("mg_ps");
        DownloadCoreService_.intent(getApplicationContext()).start();
    }

    private void initCache() {
        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(3)//default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(100)
//                .writeDebugLogs()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .defaultDisplayImageOptions(ImageOptionFactory.getDefaultOptions())
                .build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBManager.getInstance(this).onDestroy();
        System.exit(0);
    }


}
