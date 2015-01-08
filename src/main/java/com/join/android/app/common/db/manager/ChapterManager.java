package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.Chapter;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class ChapterManager extends BaseManager<Chapter> {

    private static ChapterManager chapterManager;
    private static RuntimeExceptionDao<Chapter, Integer> dao;

    private ChapterManager() {
        super(dao);
    }

    public static ChapterManager getInstance() {
        if (chapterManager == null) {
            dao = DBManager.getInstance(null).getHelper().getChapterDao();
            chapterManager = new ChapterManager();
        }
        return chapterManager;
    }

}
