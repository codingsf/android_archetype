package com.join.android.app.mugo.rpc;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * 统计
 * Created by wuw on 2014/11/26.
 */
public class StatisticsService {

    private static StatisticsService statistics;

    private StatisticsService() {
    }

    public static StatisticsService getInstance() {
        if (statistics == null) statistics = new StatisticsService();
        return statistics;
    }

    /**
     * APP构建发送激活数据
     *
     * @param uuid 设备号
     */
    public void activation(String uuid,String appid,String resource) {
        try {
            SDKCounter.send(SDKCounter.activation(uuid, appid, resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * SDK构建发送激活数据
     *
     * @param uuid 设备号
     */
    public void sdkActivation(String uuid,String appid,String resource) {
        try {
            SDKCounter.send(SDKCounter.loadingCompleted(uuid,appid,resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * 构建登陆数据
     *
     * @param uid 用户编号
     */
    public void login(final String uid,String appid) {
        try {
            SDKCounter.send(SDKCounter.login(uid,appid));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 加速器激活统计
     */
    public void acceleratorActive(String uuid,String appid,String resource){
        try {
            uuid = uuid.replaceAll(":","_");
            SDKCounter.send(SDKCounter.acceleratorActive(URLEncoder.encode(uuid,"UTF8"),appid,resource));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 加速器下载完成统计
     */
    public void acceleratorDownload(String uuid,String appid,String resource){
        try {
            uuid = uuid.replaceAll(":","_");
            SDKCounter.send(SDKCounter.acceleratorDownloadActive(URLEncoder.encode(uuid,"UTF8"),appid,resource));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
