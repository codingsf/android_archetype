package com.join.android.app.common.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkManager {

    private static NetworkManager networkManager;
    private static Context context;
    private static ConnectivityManager connMgr;
    public static int CMNET = 0;
    public static int CMWAP = 1;
    public static int WIFI = 2;

    private NetworkManager() {
    }

    private NetworkManager(Context _context) {
        context = _context;
    }

    public static NetworkManager getInstance(Context _context) {
        if (networkManager == null) {
            networkManager = new NetworkManager(_context);
            connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return networkManager;
    }


    public int getAPNType(Context context) {
        int netType = -1;

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
                netType = CMNET;
            } else {
                netType = CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = WIFI;
        }
        return netType;
    }

    public boolean checkNet() {

        try {

            if (connMgr != null) {

                NetworkInfo info = connMgr.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {

                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
