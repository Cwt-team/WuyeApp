// com/example/wuye_app/modules/notification/NotificationListActivity.java
package com.example.wuye_app.modules.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationListActivity extends AppCompatActivity {

    private RecyclerView notificationRecyclerView;
    private NotificationAdapter notificationAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, NotificationListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        notificationRecyclerView = findViewById(R.id.notificationRecyclerView);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notificationAdapter = new NotificationAdapter(getNotificationList());
        notificationRecyclerView.setAdapter(notificationAdapter);

        notificationAdapter.setOnItemClickListener(position -> {
            Notification notification = getNotificationList().get(position);
            Intent intent = new Intent(this, NotificationDetailActivity.class);
            intent.putExtra("notification_title", notification.getTitle());
            intent.putExtra("notification_content", notification.getContent());
            startActivity(intent);
        });
    }

    private List<Notification> getNotificationList() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("关于小区绿化维护的通知", "尊敬的业主，为了提升小区环境质量，我们计划于下周进行绿化维护..."));
        notifications.add(new Notification("停水通知", "各位业主，因供水管道检修，将于明日上午 9:00 至下午 5:00 停水..."));
        // ... 更多通知
        return notifications;
    }

    // 简单的通知数据模型
    public static class Notification {
        private String title;
        private String content;

        public Notification(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
}
