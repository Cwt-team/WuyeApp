// MainActivity.java
package com.example.wuye_app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wuye_app.modules.home.HomeActivity;
import com.example.wuye_app.modules.profile.ProfileActivity;
import com.example.wuye_app.modules.door.DoorControlActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // 设置默认显示的 Fragment
        loadFragment(new HomeActivity()); // 假设 HomeActivity 可以作为 Fragment 的容器

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home) {
                loadFragment(new HomeActivity());
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
