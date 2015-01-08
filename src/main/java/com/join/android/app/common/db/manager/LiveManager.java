package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.Live;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class LiveManager extends BaseManager<Live> {

    private static LiveManager liveManager;
    private static RuntimeExceptionDao<Live, Integer> dao;

    private LiveManager() {
        super(dao);
    }

    public static LiveManager getInstance() {
        if (liveManager == null) {
            dao = DBManager.getInstance(null).getHelper().getLiveDao();
            liveManager = new LiveManager();
        }
        return liveManager;
    }

}
