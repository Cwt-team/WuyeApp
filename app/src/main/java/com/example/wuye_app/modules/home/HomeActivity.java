// com/example/wuye_app/modules/home/HomeActivity.java
package com.example.wuye_app.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;
import com.example.wuye_app.modules.home.adapters.LifeServicesAdapter;
import com.example.wuye_app.modules.home.adapters.QuickActionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    private RecyclerView quickActionsRecyclerView;
    private QuickActionsAdapter quickActionsAdapter;
    private RecyclerView lifeServicesRecyclerView;
    private LifeServicesAdapter lifeServicesAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // 初始化快捷操作 RecyclerView
        quickActionsRecyclerView = findViewById(R.id.quickActionsRecyclerView);
        GridLayoutManager quickActionsLayoutManager = new GridLayoutManager(this, 4); // 假设每行 4 个
        quickActionsRecyclerView.setLayoutManager(quickActionsLayoutManager);
        quickActionsAdapter = new QuickActionsAdapter(getQuickActionsData());
        quickActionsRecyclerView.setAdapter(quickActionsAdapter);

        // 初始化生活服务 RecyclerView
        lifeServicesRecyclerView = findViewById(R.id.lifeServicesRecyclerView);
        GridLayoutManager lifeServicesLayoutManager = new GridLayoutManager(this, 3); // 假设每行 3 个
        lifeServicesRecyclerView.setLayoutManager(lifeServicesLayoutManager);
        lifeServicesAdapter = new LifeServicesAdapter(getLifeServicesData());
        lifeServicesRecyclerView.setAdapter(lifeServicesAdapter);

        // 观察 ViewModel 中的数据变化 (示例)
        homeViewModel.getCommunityName().observe(this, communityName -> {
            // 更新 UI
            // 例如: TextView communityNameTextView = findViewById(R.id.communityNameTextView);
            // communityNameTextView.setText(communityName);
        });

        // 设置快捷操作 Item 的点击事件
        quickActionsAdapter.setOnItemClickListener(position -> {
            String actionName = getQuickActionsData().get(position).second;
            Toast.makeText(this, "点击了快捷操作: " + actionName, Toast.LENGTH_SHORT).show();
            // 根据点击的 position 或数据跳转到相应的功能页面
            switch (actionName) {
                case "用户通":
                    // startActivity(new Intent(this, IntercomActivity.class));
                    break;
                case "监控":
                    // startActivity(new Intent(this, SurveillanceActivity.class));
                    break;
                // ... 其他快捷操作
            }
        });

        // 设置生活服务 Item 的点击事件
        lifeServicesAdapter.setOnItemClickListener(position -> {
            String serviceName = getLifeServicesData().get(position).second;
            Toast.makeText(this, "点击了生活服务: " + serviceName, Toast.LENGTH_SHORT).show();
            // 根据点击的 position 或数据跳转到相应的服务详情页面
            switch (serviceName) {
                case "家政服务":
                    // startActivity(new Intent(this, HousekeepingServiceActivity.class));
                    break;
                case "快递服务":
                    // startActivity(new Intent(this, DeliveryServiceActivity.class));
                    break;
                // ... 其他生活服务
            }
        });
    }

    // 模拟快捷操作数据
    private List<Pair<Integer, String>> getQuickActionsData() {
        List<Pair<Integer, String>> data = new ArrayList<>();
        data.add(new Pair<>(R.drawable.ic_quick_action_intercom, "用户通"));
        data.add(new Pair<>(R.drawable.ic_quick_action_surveillance, "监控"));
        data.add(new Pair<>(R.drawable.ic_quick_action_visitor, "邀请访客"));
        data.add(new Pair<>(R.drawable.ic_quick_action_elevator, "呼叫电梯"));
        data.add(new Pair<>(R.drawable.ic_quick_action_scan, "扫码开门"));
        data.add(new Pair<>(R.drawable.ic_quick_action_alarm, "报警记录"));
        return data;
    }

    // 模拟生活服务数据
    private List<Pair<Integer, String>> getLifeServicesData() {
        List<Pair<Integer, String>> data = new ArrayList<>();
        data.add(new Pair<>(R.drawable.ic_life_service_housekeeping, "家政服务"));
        data.add(new Pair<>(R.drawable.ic_life_service_delivery, "快递服务"));
        // ... 更多生活服务
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
