// com/example/wuye_app/modules/surveillance/SurveillanceActivity.java
package com.example.wuye_app.modules.surveillance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;

public class SurveillanceActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView titleTextView;
    private ImageButton switchCameraButton;
    private ImageButton fullScreenButton;
    private ImageButton captureButton;
    private ImageButton recordButton;
    private boolean isRecording = false;
    private boolean isFullScreen = false;

    public static Intent newIntent(Context context) {
        return new Intent(context, SurveillanceActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveillance);

        backButton = findViewById(R.id.backButton);
        titleTextView = findViewById(R.id.titleTextView);
        switchCameraButton = findViewById(R.id.switchCameraButton);
        fullScreenButton = findViewById(R.id.fullScreenButton);
        captureButton = findViewById(R.id.captureButton);
        recordButton = findViewById(R.id.recordButton);

        backButton.setOnClickListener(v -> finish());

        switchCameraButton.setOnClickListener(v -> {
            Toast.makeText(this, "切换摄像头", Toast.LENGTH_SHORT).show();
            // 执行切换摄像头逻辑
        });

        fullScreenButton.setOnClickListener(v -> {
            isFullScreen = !isFullScreen;
            if (isFullScreen) {
                fullScreenButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel); // 使用系统图标
                Toast.makeText(this, "全屏", Toast.LENGTH_SHORT).show();
                // 执行全屏显示逻辑
            } else {
                fullScreenButton.setImageResource(android.R.drawable.ic_menu_zoom); // 使用系统图标
                Toast.makeText(this, "取消全屏", Toast.LENGTH_SHORT).show();
                // 执行取消全屏逻辑
            }
        });

        captureButton.setOnClickListener(v -> {
            Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show();
            // 执行拍照逻辑
        });

        recordButton.setOnClickListener(v -> {
            isRecording = !isRecording;
            if (isRecording) {
                recordButton.setImageResource(android.R.drawable.presence_video_busy); // 使用系统图标
                Toast.makeText(this, "开始录像", Toast.LENGTH_SHORT).show();
                // 执行开始录像逻辑
            } else {
                recordButton.setImageResource(android.R.drawable.presence_video_online); // 使用系统图标
                Toast.makeText(this, "停止录像", Toast.LENGTH_SHORT).show();
                // 执行停止录像逻辑
            }
        });
    }
}
