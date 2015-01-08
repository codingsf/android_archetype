package com.join.android.app.common.db.tables;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-11
 * Time: 上午9:11
 * 下载课程总表
 */
public class LocalCourse {
    @DatabaseField(generatedId = true)
    private int id;

    //下载课程主显示页面内容
    /**
     * 学习总时长
     */
    @DatabaseField
    private int learningTimes;
    /**
     * 课程名称
     */
    @DatabaseField
    private String title;

    @DatabaseField
    private long courseId;

    @DatabaseField
    private String url;


    /**
     * 章节
     */
    @ForeignCollectionField(eager = true)
    ForeignCollection<Chapter> chapters;

    /**
     * 资料
     */
    @ForeignCollectionField(eager = true)
    ForeignCollection<Reference> references;

    //课程详情
    /**
     * 开课机构名称
     */
    @DatabaseField
    private String branch;
    /**
     * 课程创建时间
     */
    @DatabaseField
    private String createTime;

    /**
     * 课程时长
     */
    @DatabaseField
    private long courseHour;
    /**
     * 课件有效时间
     */
    @DatabaseField
    private long validUntil;
    /**
     * 课程描述
     */
    @DatabaseField
    private String description;

    /**
     * 章节个数
     */
    private int chapterNum;

    /**
     * 资料个数
     */
    private int refNum;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(long courseHour) {
        this.courseHour = courseHour;
    }

    public long getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(long validUntil) {
        this.validUntil = validUntil;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ForeignCollection<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ForeignCollection<Chapter> chapters) {
        this.chapters = chapters;
    }

    public ForeignCollection<Reference> getReferences() {
        return references;
    }

    public void setReferences(ForeignCollection<Reference> references) {
        this.references = references;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum) {
        this.chapterNum = chapterNum;
    }

    public int getRefNum() {
        return refNum;
    }

    public void setRefNum(int refNum) {
        this.refNum = refNum;
    }
}
