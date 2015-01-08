package com.join.android.app.common.db.manager;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.join.android.app.common.db.tables.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 上午11:01
 */

public class CourseManager extends BaseManager<Course> {

    private static CourseManager courseManager;
    private static RuntimeExceptionDao<Course, Integer> dao;

    private CourseManager() {
        super(dao);
    }

    public static CourseManager getInstance() {
        if (courseManager == null) {
            dao = DBManager.getInstance(null).getHelper().getCourseDao();
            courseManager = new CourseManager();
        }
        return courseManager;
    }


    public Course getByCourseId(long courseId){

        Map params = new HashMap(0);
        params.put("courseId",courseId);
        List<Course> courseList = findForParams(params);
        if(courseList!=null&&courseList.size()>0)return courseList.get(0);
        return null;
    }
}
