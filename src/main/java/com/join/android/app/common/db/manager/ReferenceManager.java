package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.Reference;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class ReferenceManager extends BaseManager<Reference> {

    private static ReferenceManager referenceManager;
    private static RuntimeExceptionDao<Reference, Integer> dao;

    private ReferenceManager() {
        super(dao);
    }

    public static ReferenceManager getInstance() {
        if (referenceManager == null) {
            dao = DBManager.getInstance(null).getHelper().getReferenceDao();
            referenceManager = new ReferenceManager();
        }
        return referenceManager;
    }

}
