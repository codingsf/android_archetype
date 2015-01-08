package com.join.android.app.common.db.tables;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-8
 * Time: 下午12:17
 */
public class Chapter {
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 章节ID
     */
    @DatabaseField
    private long chapterId;

    /**
     * 章节标题
     */
    @DatabaseField
    private String title;

    /**
     * 章节时长
     */
    @DatabaseField
    private long chapterDuration;

    /**
     * 章节学习时间
     */
    @DatabaseField
    private long learnedTime;

    /**
     * 书签
     */
    @DatabaseField
    private String bookmark;

    /**
     * 章节关联资源的文件大小
     */
    @DatabaseField
    private long filesize;

    /**
     * 章节资源播放链接
     */
    @DatabaseField
    private String playUrl;

    /**
     * 章节资源下载链接
     */
    @DatabaseField
    private String downloadUrl;

    /**
     * 有效期
     */
    @DatabaseField
    private String validUntil;

    /**
     * 剩余天数
     */
    @DatabaseField
    private String leftDays;


    @DatabaseField(columnName = "localcourse_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private LocalCourse localCourse;

    @DatabaseField(columnName = "parent_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Chapter parent;


    private List<Chapter> children;

    private boolean playing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getChapterDuration() {
        return chapterDuration;
    }

    public void setChapterDuration(long chapterDuration) {
        this.chapterDuration = chapterDuration;
    }

    public long getLearnedTime() {
        return learnedTime;
    }

    public void setLearnedTime(long learnedTime) {
        this.learnedTime = learnedTime;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public LocalCourse getLocalCourse() {
        return localCourse;
    }

    public void setLocalCourse(LocalCourse localCourse) {
        this.localCourse = localCourse;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public String getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(String leftDays) {
        this.leftDays = leftDays;
    }

    public Chapter getParent() {
        return parent;
    }

    public void setParent(Chapter parent) {
        this.parent = parent;
    }

    public List<Chapter> getChildren() {
        return children;
    }

    public void setChildren(List<Chapter> children) {
        this.children = children;
    }
}
