package com.join.android.app.common.db.tables;

import com.j256.ormlite.field.DatabaseField;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-11
 * Time: 上午9:11
 */
public class Live {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(index = true)
    private long liveId;
    @DatabaseField
    private String title;
    @DatabaseField
    private String createTime;
    @DatabaseField
    private String author;
    @DatabaseField
    private String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLiveId() {
        return liveId;
    }

    public void setLiveId(long liveId) {
        this.liveId = liveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
