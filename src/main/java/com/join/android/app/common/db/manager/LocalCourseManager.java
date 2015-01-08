package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.LocalCourse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class LocalCourseManager extends BaseManager<LocalCourse> {

    private static LocalCourseManager localCourseManager;
    private static RuntimeExceptionDao<LocalCourse, Integer> dao;

    private LocalCourseManager() {
        super(dao);
    }

    public static LocalCourseManager getInstance() {
        if (localCourseManager == null) {
            dao = DBManager.getInstance(null).getHelper().getLocalCourseDao();
            localCourseManager = new LocalCourseManager();
        }
        return localCourseManager;
    }


    public List<LocalCourse> findByCourseId(String courseId) {

        Map params = new HashMap(0);
        params.put("courseId",courseId);
        return findForParams(params);
    }
}
