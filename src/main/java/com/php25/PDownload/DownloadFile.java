package com.php25.PDownload;

import java.io.Serializable;

/**
 * Created with mawanjin
 * User: mawanjin
 * Date: 14-9-5
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
public class DownloadFile implements Serializable {
    private Long id;
    private String tag;
    private String url;
    private String basePath;
    private String name;
    private String showName;
    private String absolutePath;
    private Integer totalSize;
    private boolean downloading;
    private boolean finished;
    private String createTime;
    private String finishTime;
    private String packageName;
    /** APP图片的地址*/
    private String portraitURL;

    /** 1共享资源*/
    private String dtype;
    /** 1 视频 2音乐 3PDF 4照片*/
    private String fileType;
    private boolean isDownloadingNow;


    private String percent;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Boolean getDownloading() {
        return downloading;
    }

    public void setDownloading(Boolean downloading) {
        this.downloading = downloading;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public boolean isDownloadingNow() {
        return isDownloadingNow;
    }

    public void setDownloadingNow(boolean isDownloadingNow) {
        this.isDownloadingNow = isDownloadingNow;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public boolean isDownloading() {
        return downloading;
    }

    public void setDownloading(boolean downloading) {
        this.downloading = downloading;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPortraitURL() {
        return portraitURL;
    }

    public void setPortraitURL(String portraitURL) {
        this.portraitURL = portraitURL;
    }


    @Override
    public String toString() {
        return "DownloadFile{" +
                "url='" + url + '\'' +
                ", basePath='" + basePath + '\'' +
                ", name='" + name + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", packageName='" + packageName + '\'' +
                ", totalSize=" + totalSize +
                '}';
    }


}
