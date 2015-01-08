package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.ResourceShare;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class ResourceShareManager extends BaseManager<ResourceShare> {

    private static ResourceShareManager resourceShareManager;
    private static RuntimeExceptionDao<ResourceShare, Integer> dao;

    private ResourceShareManager() {
        super(dao);
    }

    public static ResourceShareManager getInstance() {
        if (resourceShareManager == null) {
            dao = DBManager.getInstance(null).getHelper().getResourceShareDao();
            resourceShareManager = new ResourceShareManager();
        }
        return resourceShareManager;
    }

}
