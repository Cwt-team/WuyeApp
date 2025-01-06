// com/example/wuye_app/modules/intercom/IntercomActivity.java
package com.example.wuye_app.modules.intercom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;

public class IntercomActivity extends AppCompatActivity {

    private ImageButton hangUpButton;
    private ImageButton muteButton;
    private ImageButton callButton;
    private TextView statusTextView;
    private boolean isMuted = false;

    public static Intent newIntent(Context context) {
        return new Intent(context, IntercomActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercom);

        hangUpButton = findViewById(R.id.hangUpButton);
        muteButton = findViewById(R.id.muteButton);
        callButton = findViewById(R.id.callButton);
        statusTextView = findViewById(R.id.statusTextView);

        hangUpButton.setOnClickListener(v -> {
            Toast.makeText(this, "挂断", Toast.LENGTH_SHORT).show();
            // 执行挂断逻辑
            finish();
        });

        muteButton.setOnClickListener(v -> {
            isMuted = !isMuted;
            if (isMuted) {
                muteButton.setImageResource(R.drawable.ic_mic_off); // 需要 mic_off 图标
                statusTextView.setText("已静音");
            } else {
                muteButton.setImageResource(R.drawable.ic_mic); // 需要 mic 图标
                statusTextView.setText("通话中");
            }
        });

        callButton.setOnClickListener(v -> {
            Toast.makeText(this, "通话", Toast.LENGTH_SHORT).show();
            // 执行通话逻辑
        });

        // 模拟对讲开始
        startIntercom();
    }

    private void startIntercom() {
        statusTextView.setText("正在连接...");
        // 模拟连接成功后更新状态
        statusTextView.postDelayed(() -> {
            statusTextView.setText("通话中");
        }, 2000);
    }
}
