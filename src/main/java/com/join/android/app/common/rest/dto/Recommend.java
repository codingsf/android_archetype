package com.join.android.app.common.rest.dto;

/**
 * User: mawanjin@join-cn.com
 * Date: 13-12-6
 * Time: 下午1:54
 */
public class Recommend {
    private String id;
    /**
     * 1:国家 2:城市 3:景点
     */
    private int stype;
    private int vtype;
    private String pid;
    private String pname;
    private String picurl;
    private long addtime;
    private long edittime;
    private int slock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    public int getVtype() {
        return vtype;
    }

    public void setVtype(int vtype) {
        this.vtype = vtype;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public long getAddtime() {
        return addtime;
    }

    public void setAddtime(long addtime) {
        this.addtime = addtime;
    }

    public long getEdittime() {
        return edittime;
    }

    public void setEdittime(long edittime) {
        this.edittime = edittime;
    }

    public int getSlock() {
        return slock;
    }

    public void setSlock(int slock) {
        this.slock = slock;
    }
}
