// com/example/wuye_app/modules/notification/NotificationAdapter.java
package com.example.wuye_app.modules.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<NotificationListActivity.Notification> notificationList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public NotificationAdapter(List<NotificationListActivity.Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationListActivity.Notification notification = notificationList.get(position);
        holder.notificationTitleTextView.setText(notification.getTitle());
        // 可以设置发布时间，这里省略
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView notificationTitleTextView;
        // 可以添加发布时间 TextView 和图标 ImageView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationTitleTextView = itemView.findViewById(R.id.notificationTitle);
        }
    }
}
