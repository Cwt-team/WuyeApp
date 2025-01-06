// com/example/wuye_app/modules/alarm/AlarmRecordAdapter.java
package com.example.wuye_app.modules.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.List;

public class AlarmRecordAdapter extends RecyclerView.Adapter<AlarmRecordAdapter.ViewHolder> {

    private List<AlarmRecordActivity.Alarm> alarmRecordList;

    public AlarmRecordAdapter(List<AlarmRecordActivity.Alarm> alarmRecordList) {
        this.alarmRecordList = alarmRecordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlarmRecordActivity.Alarm alarm = alarmRecordList.get(position);
        holder.alarmTimeTextView.setText(alarm.getTime());
        holder.alarmTypeTextView.setText(alarm.getType());
        // 可以设置报警状态图标
    }

    @Override
    public int getItemCount() {
        return alarmRecordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView alarmTimeTextView;
        TextView alarmTypeTextView;
        // 可以添加报警状态 ImageView

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmTimeTextView = itemView.findViewById(R.id.alarmTime);
            alarmTypeTextView = itemView.findViewById(R.id.alarmType);
        }
    }
}
