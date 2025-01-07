// MainActivity.java
package com.example.wuye_app;

import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.wuye_app.modules.login.LoginActivity;
import com.example.wuye_app.utils.NetworkUtils;
import com.example.wuye_app.modules.home.HomeActivity;
import com.example.wuye_app.modules.profile.ProfileActivity;
import com.example.wuye_app.modules.door.DoorControlActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.wuye_app.utils.SharedPreferencesManager;
import com.example.wuye_app.modules.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // 检查Fragment容器是否存在
        if (findViewById(R.id.fragment_container) == null) {
            Toast.makeText(this, "Fragment容器未找到", Toast.LENGTH_SHORT).show();
            return;
        }

        // 检查网络状态
        if (!NetworkUtils.getInstance(this).isNetworkAvailable()) {
            Toast.makeText(this, "网络不可用，请检查网络连接", Toast.LENGTH_SHORT).show();
        }

        // 检查用户登录状态
        if (!SharedPreferencesManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // 设置默认显示的 Fragment
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (id == R.id.navigation_door) {
                // 可以直接启动 Activity 或者加载 Fragment
                startActivity(DoorControlActivity.newIntent(this)); // 假设 DoorControlActivity 有 newIntent 方法
                return true;
            } else if (id == R.id.navigation_profile) {
                startActivity(ProfileActivity.newIntent(this)); // 假设 ProfileActivity 有 newIntent 方法
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment); // fragment_container 是 activity_main.xml 中用于显示 Fragment 的容器
        transaction.commit();
    }
}
