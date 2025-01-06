// com/example/wuye_app/modules/notification/NotificationDetailActivity.java
package com.example.wuye_app.modules.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;

public class NotificationDetailActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, NotificationDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        TextView titleTextView = findViewById(R.id.notificationDetailTitle);
        TextView contentTextView = findViewById(R.id.notificationDetailContent);

        String title = getIntent().getStringExtra("notification_title");
        String content = getIntent().getStringExtra("notification_content");

        titleTextView.setText(title);
        contentTextView.setText(content);
    }
}
