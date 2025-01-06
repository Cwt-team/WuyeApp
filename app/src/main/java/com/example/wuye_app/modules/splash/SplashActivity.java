// com/example/wuye_app/modules/splash/SplashActivity.java
package com.example.wuye_app.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.MainActivity;
import com.example.wuye_app.R;
import com.example.wuye_app.modules.login.LoginActivity;
import com.example.wuye_app.utils.SharedPreferencesManager;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000; // 2秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            // 判断用户是否已登录
            if (SharedPreferencesManager.getInstance(this).isLoggedIn()) {
                // 如果已登录，跳转到主界面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // 如果未登录，跳转到登录界面
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }, SPLASH_DELAY);
    }
}

