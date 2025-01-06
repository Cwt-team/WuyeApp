// com/example/wuye_app/utils/SharedPreferencesManager.java
package com.example.wuye_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static SharedPreferencesManager instance;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "wuye_app_prefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private SharedPreferencesManager(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // 可以添加其他需要使用 SharedPreferences 管理的数据
}
