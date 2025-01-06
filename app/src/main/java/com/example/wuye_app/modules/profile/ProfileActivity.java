// com/example/wuye_app/modules/profile/ProfileActivity.java
package com.example.wuye_app.modules.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;
import com.example.wuye_app.modules.setting.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel;
    private RecyclerView profileOptionsRecyclerView;
    private ProfileOptionsAdapter profileOptionsAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, ProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        // 初始化 RecyclerView
        profileOptionsRecyclerView = findViewById(R.id.profileOptionsRecyclerView);
        profileOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileOptionsAdapter = new ProfileOptionsAdapter(getProfileOptionsData());
        profileOptionsRecyclerView.setAdapter(profileOptionsAdapter);

        // 观察 ViewModel 中的数据变化 (示例)
        profileViewModel.getUserPhoneNumber().observe(this, phoneNumber -> {
            // 更新 UI，例如显示手机号码
            // TextView phoneNumberTextView = findViewById(R.id.phoneNumberTextView);
            // phoneNumberTextView.setText(phoneNumber);
        });

        profileViewModel.getUserPropertyInfo().observe(this, propertyInfo -> {
            // 更新 UI，例如显示物业信息
            // TextView propertyInfoTextView = findViewById(R.id.propertyInfoTextView);
            // propertyInfoTextView.setText(propertyInfo);
        });

        // 设置 Item 的点击事件
        profileOptionsAdapter.setOnItemClickListener(position -> {
            String optionName = getProfileOptionsData().get(position).second;
            Toast.makeText(this, "点击了: " + optionName, Toast.LENGTH_SHORT).show();
            // 根据点击的 position 或数据跳转到相应的功能页面
            switch (optionName) {
                case "切换业主":
                    // 处理切换业主逻辑
                    break;
                case "人脸录制":
                    // 处理人脸录制逻辑
                    break;
                case "设置":
                    startActivity(new Intent(this, SettingsActivity.class));
                    break;
                // ... 其他选项
            }
        });
    }

    // 模拟个人资料选项数据
    private List<Pair<Integer, String>> getProfileOptionsData() {
        List<Pair<Integer, String>> data = new ArrayList<>();
        data.add(new Pair<>(R.drawable.ic_switch_owner, "切换业主"));
        data.add(new Pair<>(R.drawable.ic_face_record, "人脸录制"));
        data.add(new Pair<>(R.drawable.ic_settings, "设置"));
        // ... 更多个人资料选项
        return data;
    }

    // 使用简单的 Pair 类来存储图标和文字，实际项目中可以创建数据模型
    public static class Pair<U, V> {
        public final U first;
        public final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}
