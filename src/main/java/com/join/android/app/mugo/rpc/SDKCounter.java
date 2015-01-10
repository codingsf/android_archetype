package com.join.android.app.mugo.rpc;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by icemo on 14-11-20.
 */
public class SDKCounter {

//    final static String REQUEST_URL = "http://ad.android.xy.com/api.php";
    final static String REQUEST_URL = "http://ad.mg3721.com/api.php";

    final static String REQUEST_KEY = "41459fd594d9ff2fa10b2608a143df4";

    /**
     * 统计中可以添加的属性
     */
    public enum Props {

        REQUEST_KEY("key"),
        AD("ad"),
        APP("app"),
        UID("uid"),
        SERVER("server"),
        ROLE_NAME("role_name"),
        MONEY("money"),
        OS_NAME("os_name"),
        OS_VERSION("os_version"),
        SCREEN_SIZE("screen_size"),
        DEVICE_UUID("device_uuid"),
        UUID("uuid"),
        DEVICE_NAME("device_name");

        private String name;

        public String getName() {
            return name;
        }

        private Props(String name) {
            this.name = name;
        }

    }

    /**
     * 请求的类型
     */
    public enum RequestType {

        ACTIVATION("activation"),
        ACTIVATION_ACCELERATOR("accelerator_active"),
        ACTIVATION_ACCELERATOR_DOWNLOAD("accelerator_download"),
        REGISTER("register"),
        LOGIN("login"),
        LOADINGCOMPL("dupgrade");

        private String key;

        private String getKey() {
            return key;
        }

        RequestType(String key) {
            this.key = key;
        }
    }

    /**
     * md5工具类
     */
    private static class Md5 {
        private static MessageDigest securityMd5;

        static {
            try {
                securityMd5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                //
            }
        }

        private static byte[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        public static synchronized String encode(String str) {
            byte[] strBytes = str.getBytes();
            securityMd5.update(strBytes);
            byte[] strDigest = securityMd5.digest();
            byte[] result = new byte[32];
            for (int i = 0, k = 0; i < 16; i++) {
                result[k] = hexDigits[(strDigest[i] >>> 4 & 0xf)];
                result[k + 1] = hexDigits[strDigest[i] & 0xf];
                k += 2;
            }
            return new String(result);
        }
    }

    /**
     * 统计数据集合
     */
    public static class DataMap {

        private SortedMap<String, String> props = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        public DataMap setProp(Props prop, String value) {
            props.put(prop.getName(), value);
            return this;
        }

        public DataMap setProp(Map<Props, String> kvs) {
            for (Props key : kvs.keySet()) {
                setProp(key, kvs.get(key));
            }
            return this;
        }

        public boolean send() throws IOException {
            return SDKCounter.send(this);
        }

        public Map<String, String> toParams() {
            StringBuilder requestParamStr = new StringBuilder();
            for (String key : props.keySet()) {
                requestParamStr.append("&" + key + "=" + props.get(key));
            }
            requestParamStr.append(REQUEST_KEY);
            String requestSign = md5(requestParamStr.toString().substring(1));
            Map<String, String> params = new HashMap<String, String>();
            params.putAll(props);
            params.put("sign", requestSign);
            return params;
        }

    }

    /**
     * APP激活(构建发送激活数据)
     *
     * @param uuid 设备唯一ID
     * @return DataMap
     */
    public static DataMap activation(String uuid,String appid,String resource) {
        return (new DataMap()).setProp(Props.UUID, uuid).setProp(SDKCounter.Props.APP, appid)
                .setProp(SDKCounter.Props.AD, resource + "").setProp(Props.REQUEST_KEY, RequestType.ACTIVATION.getKey());
    }

    /**
     * 构建登陆数据
     *
     * @param uid 用户ID
     * @return DataMap
     */
    public static DataMap login(String uid,String appid) {
        return (new DataMap()).setProp(Props.UID, "" + uid)
                .setProp(SDKCounter.Props.APP, appid)
                .setProp(Props.REQUEST_KEY, RequestType.LOGIN.getKey());
    }

    /**
     * sdk激活(游戏加载完成)
     *
     * @param uuid 用户ID
     * @return DataMap
     */
    public static DataMap loadingCompleted(String uuid,String appid,String source) {
//                                countData.setProp(SDKCounter.Props.APP, appid)
//                                .setProp(SDKCounter.Props.AD, source+"");

        return (new DataMap()).setProp(Props.UUID, "" + uuid)
                .setProp(SDKCounter.Props.APP, appid)
                .setProp(SDKCounter.Props.AD, source + "")
                .setProp(Props.REQUEST_KEY, RequestType.LOADINGCOMPL.getKey()
                );
    }

    /**
     * 加速器激活
     *
     * @return DataMap
     */
    public static DataMap acceleratorActive(String uuid,String appid,String resource) {
        return (new DataMap()).setProp(Props.UUID, uuid).setProp(SDKCounter.Props.APP, appid)
                .setProp(SDKCounter.Props.AD, resource + "").setProp(Props.REQUEST_KEY, RequestType.ACTIVATION_ACCELERATOR.getKey());
    }

    /**
     * 加速器下载完成
     *
     * @return DataMap
     */
    public static DataMap acceleratorDownloadActive(String uuid,String appid,String resource) {
        return (new DataMap()).setProp(Props.UUID, uuid).setProp(SDKCounter.Props.APP, appid)
                .setProp(SDKCounter.Props.AD, resource + "").setProp(Props.REQUEST_KEY, RequestType.ACTIVATION_ACCELERATOR_DOWNLOAD.getKey());
    }

    /**
     * 发送统计数据
     *
     * @param data
     * @return
     * @throws java.io.IOException
     */
    public static boolean send(DataMap data) throws IOException {
        Map<String, String> params = data.toParams();
        StringBuilder paramSb = new StringBuilder();
        for (String key : params.keySet()) {
            paramSb.append("&" + key + "=" + params.get(key));
        }
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(REQUEST_URL + "?" + paramSb.toString().substring(1));
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            int result = response.getEntity().getContent().read();
            if (result == 2) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * md5加密函数
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        return Md5.encode(str);
    }


}