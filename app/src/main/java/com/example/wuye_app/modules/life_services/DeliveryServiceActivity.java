// com/example/wuye_app/modules/life_services/DeliveryServiceActivity.java
package com.example.wuye_app.modules.life_services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;

public class DeliveryServiceActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, DeliveryServiceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_service);

        findViewById(R.id.queryDeliveryButton).setOnClickListener(v -> {
            Toast.makeText(this, "查询快递", Toast.LENGTH_SHORT).show();
            // 处理查询快递逻辑
        });

        findViewById(R.id.deliveryPointInfoButton).setOnClickListener(v -> {
            Toast.makeText(this, "代收点信息", Toast.LENGTH_SHORT).show();
            // 跳转到代收点信息页面或显示相关信息
        });
    }
}
