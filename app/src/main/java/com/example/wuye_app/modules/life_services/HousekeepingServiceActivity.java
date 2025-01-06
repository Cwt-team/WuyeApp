// com/example/wuye_app/modules/life_services/HousekeepingServiceActivity.java
package com.example.wuye_app.modules.life_services;

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

public class HousekeepingServiceActivity extends AppCompatActivity {

    private RecyclerView serviceRecyclerView;
    private HousekeepingServiceAdapter serviceAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, HousekeepingServiceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping_service);

        serviceRecyclerView = findViewById(R.id.housekeepingRecyclerView);
        serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        serviceAdapter = new HousekeepingServiceAdapter(getServiceItems());
        serviceRecyclerView.setAdapter(serviceAdapter);

        serviceAdapter.setOnItemClickListener(position -> {
            HousekeepingService service = getServiceItems().get(position);
            Toast.makeText(this, "预约 " + service.getName(), Toast.LENGTH_SHORT).show();
            // 处理预约逻辑
        });
    }

    private List<HousekeepingService> getServiceItems() {
        List<HousekeepingService> services = new ArrayList<>();
        services.add(new HousekeepingService("日常保洁", "50元/次"));
        services.add(new HousekeepingService("深度清洁", "100元/次"));
        // ... 更多服务项目
        return services;
    }

    // 简单的家政服务数据模型
    public static class HousekeepingService {
        private String name;
        private String price;

        public HousekeepingService(String name, String price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
    }
}
