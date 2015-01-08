package com.join.android.app.common.db.tables;

import com.j256.ormlite.field.DatabaseField;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-11
 * Time: 上午9:11
 */
public class Course {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String lastLearn;
    @DatabaseField
    private int learningTimes;
    @DatabaseField
    private String title;
    @DatabaseField
    private String totalDuration;
    @DatabaseField
    private long courseId;
    @DatabaseField
    private long courseHour;
    @DatabaseField
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastLearn() {
        return lastLearn;
    }

    public void setLastLearn(String lastLearn) {
        this.lastLearn = lastLearn;
    }

    public int getLearningTimes() {
        return learningTimes;
    }

    public void setLearningTimes(int learningTimes) {
        this.learningTimes = learningTimes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(long courseHour) {
        this.courseHour = courseHour;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
