package com.join.android.app.common.data;

import java.util.ArrayList;

public class ImageInfo {

    private int id;
    private String displayName;
    private ArrayList<String> tag;
    private String firstPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    public String getFirstPath() {
        return firstPath;
    }

    public void setFirstPath(String firstPath) {
        this.firstPath = firstPath;
    }
}
