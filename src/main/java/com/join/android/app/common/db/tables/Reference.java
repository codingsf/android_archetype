package com.join.android.app.common.db.tables;

import com.j256.ormlite.field.DatabaseField;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-8
 * Time: 下午12:21
 * 在线课程里的资料
 */
public class Reference {

    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 参考资料ID
     */
    @DatabaseField(index = true)
    private long referenceId;
    /**
     * 参考资料标题
     */
    @DatabaseField(index = true)
    private String title;
    /**
     * 参考资料文件大小，字节
     */
    @DatabaseField(index = true)
    private long fileSize;
    /**
     * 参考资料类型， 1—video,2—doc,3—image,4—flash,5—live,6--unknow
     */
    @DatabaseField(index = true)
    private int type;
    /**
     * 参考资料链接
     */
    @DatabaseField(index = true)
    private String url;

    /**
     * 所属课程
     */
    @DatabaseField(columnName = "localcourse_id", foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private LocalCourse localCourse;

    public long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(long referenceId) {
        this.referenceId = referenceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalCourse getLocalCourse() {
        return localCourse;
    }

    public void setLocalCourse(LocalCourse localCourse) {
        this.localCourse = localCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
