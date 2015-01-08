package com.join.android.app.common.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-9-8
 * Time: 下午9:14
 */
@DatabaseTable(tableName = "resource_share")
public class ResourceShare {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private long fileSize;
    @DatabaseField
    private long rsId;
    @DatabaseField
    private String name;
    @DatabaseField
    private int type;
    @DatabaseField
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public long getRsId() {
        return rsId;
    }

    public void setRsId(long rsId) {
        this.rsId = rsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
