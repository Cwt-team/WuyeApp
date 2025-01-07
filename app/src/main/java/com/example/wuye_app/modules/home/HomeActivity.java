// com/example/wuye_app/modules/home/HomeActivity.java
package com.example.wuye_app.modules.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wuye_app.R;
import com.example.wuye_app.data.model.HomeDataResponse; // 确保导入了这个类
import com.example.wuye_app.data.model.LifeService;
import com.example.wuye_app.data.model.QuickAction;
import com.example.wuye_app.data.remote.ApiService;
import com.example.wuye_app.data.remote.RetrofitClient;
import com.example.wuye_app.modules.notifications.NotificationListActivity;
import com.example.wuye_app.modules.profile.ProfileActivity;
import com.example.wuye_app.modules.home.adapters.LifeServicesAdapter;
import com.example.wuye_app.modules.home.adapters.QuickActionsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private BottomNavigationView bottomNavigationView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView userNameTextView;
    private TextView communityNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // 初始化用户信息
        userNameTextView = findViewById(R.id.userNameTextView);
        communityNameTextView = findViewById(R.id.communityNameTextView);

        // 设置底部导航
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // 已经在首页，无需处理
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(this, NotificationListActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(this, ProfileActivity.class));
                    return true;
            }
            return false;
        });

        // 初始化下拉刷新
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);

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

        loadData(); // 在 onCreate 中首次加载数据
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
    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);

        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<HomeDataResponse> call = apiService.getHomeData();
        call.enqueue(new Callback<HomeDataResponse>() {
            @Override
            public void onResponse(Call<HomeDataResponse> call, Response<HomeDataResponse> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    HomeDataResponse homeData = response.body();
                    updateUI(homeData);
                } else {
                    Toast.makeText(HomeActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HomeDataResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(HomeActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(HomeDataResponse homeData) {
        // 更新用户信息
        userNameTextView.setText(homeData.getUserName());
        communityNameTextView.setText(homeData.getCommunityName());

        // 更新快捷操作
        List<Pair<Integer, String>> quickActions = new ArrayList<>();
        if (homeData.getQuickActions() != null) { // 添加判空处理
            for (QuickAction action : homeData.getQuickActions()) {
                quickActions.add(new Pair<>(getDrawableRes(action.getIcon()), action.getName()));
            }
        }
        quickActionsAdapter.updateData(quickActions);

        // 更新生活服务
        List<Pair<Integer, String>> lifeServices = new ArrayList<>();
        if (homeData.getLifeServices() != null) { // 添加判空处理
            for (LifeService service : homeData.getLifeServices()) {
                lifeServices.add(new Pair<>(getDrawableRes(service.getIcon()), service.getName()));
            }
        }
        lifeServicesAdapter.updateData(lifeServices);
    }

    private int getDrawableRes(String iconName) {
        int resourceId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        if (resourceId == 0) {
            // 处理找不到资源的情况，例如返回一个默认的资源 ID 或者抛出异常
            return R.drawable.ic_launcher_foreground; // 使用一个默认
        }
        return resourceId;
    }
}
