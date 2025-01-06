// com/example/wuye_app/modules/scan/ScanToOpenActivity.java
package com.example.wuye_app.modules.scan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;

public class ScanToOpenActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, ScanToOpenActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_to_open);

        // 初始化相机预览等
        Toast.makeText(this, "打开扫码界面", Toast.LENGTH_SHORT).show();

        // 可以集成二维码扫描库，例如 ZXing 或 ML Kit
    }
}
