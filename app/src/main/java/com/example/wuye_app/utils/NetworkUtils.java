// com/example/wuye_app/utils/NetworkUtils.java
package com.example.wuye_app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    private static NetworkUtils instance;
    private Context context;

    private NetworkUtils(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized NetworkUtils getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkUtils(context);
        }
        return instance;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    // 可以添加其他网络相关的工具方法，例如检查 Wi-Fi 是否连接等
}
