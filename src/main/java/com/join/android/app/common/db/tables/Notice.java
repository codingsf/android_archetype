package com.join.android.app.common.db.tables;

import com.j256.ormlite.field.DatabaseField;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-8
 * Time: 下午9:17
 */
public class Notice {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String content;
    @DatabaseField
    private String createTime;
    @DatabaseField
    private String title;
    @DatabaseField
    private long noticeId;
    @DatabaseField
    private String validUntil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }
}
