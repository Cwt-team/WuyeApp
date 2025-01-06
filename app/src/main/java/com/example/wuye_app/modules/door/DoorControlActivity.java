// com/example/wuye_app/modules/door/DoorControlActivity.java
package com.example.wuye_app.modules.door;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;

public class DoorControlActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, DoorControlActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_control);

        findViewById(R.id.clickToOpenButton).setOnClickListener(v -> {
            Toast.makeText(this, "模拟点击开门", Toast.LENGTH_SHORT).show();
            // 执行点击开门逻辑
        });

        findViewById(R.id.scanToOpenLayout).setOnClickListener(v -> {
            Toast.makeText(this, "跳转到扫码开门", Toast.LENGTH_SHORT).show();
            // 启动扫码开门 Activity
            // startActivity(new Intent(this, ScanToOpenActivity.class));
        });

        // 可以添加其他开门方式的点击事件处理
    }
}
