package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.Notice;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class NoticeManager extends BaseManager<Notice> {

    private static NoticeManager noticeManager;
    private static RuntimeExceptionDao<Notice, Integer> dao;

    private NoticeManager() {
        super(dao);
    }

    public static NoticeManager getInstance() {
        if (noticeManager == null) {
            dao = DBManager.getInstance(null).getHelper().getNoticeDao();
            noticeManager = new NoticeManager();
        }
        return noticeManager;
    }

}
