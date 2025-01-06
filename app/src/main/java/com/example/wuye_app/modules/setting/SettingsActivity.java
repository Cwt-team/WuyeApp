// com/example/wuye_app/modules/setting/SettingsActivity.java
package com.example.wuye_app.modules.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView settingsRecyclerView;
    private SettingsAdapter settingsAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsRecyclerView = findViewById(R.id.settingsRecyclerView);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        settingsAdapter = new SettingsAdapter(getSettingOptions());
        settingsRecyclerView.setAdapter(settingsAdapter);

        settingsAdapter.setOnItemClickListener(position -> {
            String settingName = getSettingOptions().get(position).second;
            Toast.makeText(this, "点击了设置项: " + settingName, Toast.LENGTH_SHORT).show();
            // 根据点击的设置项执行相应的操作
            switch (settingName) {
                case "修改密码":
                    // 处理修改密码逻辑
                    break;
                case "推送通知设置":
                    // 跳转到推送通知设置页面
                    break;
                case "关于我们":
                    // 跳转到关于我们页面
                    break;
                // ... 其他设置项
            }
        });
    }

    private List<Pair<Integer, String>> getSettingOptions() {
        List<Pair<Integer, String>> options = new ArrayList<>();
        options.add(new Pair<>(R.drawable.ic_password, "修改密码"));
        options.add(new Pair<>(R.drawable.ic_notifications, "推送通知设置"));
        options.add(new Pair<>(R.drawable.ic_info, "关于我们"));
        // ... 更多设置选项
        return options;
    }

    // 使用简单的 Pair 类来存储图标和文字
    public static class Pair<U, V> {
        public final U first;
        public final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}
