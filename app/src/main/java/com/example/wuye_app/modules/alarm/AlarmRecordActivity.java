// com/example/wuye_app/modules/alarm/AlarmRecordActivity.java
package com.example.wuye_app.modules.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.ArrayList;
import java.util.List;

public class AlarmRecordActivity extends AppCompatActivity {

    private RecyclerView alarmRecordRecyclerView;
    private AlarmRecordAdapter alarmRecordAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, AlarmRecordActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_record);

        alarmRecordRecyclerView = findViewById(R.id.alarmRecordRecyclerView);
        alarmRecordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmRecordAdapter = new AlarmRecordAdapter(getAlarmRecords());
        alarmRecordRecyclerView.setAdapter(alarmRecordAdapter);
    }

    private List<Alarm> getAlarmRecords() {
        List<Alarm> alarms = new ArrayList<>();
        alarms.add(new Alarm("2023-10-27 10:00", "烟雾报警"));
        alarms.add(new Alarm("2023-10-26 14:30", "非法入侵"));
        // ... 更多报警记录
        return alarms;
    }

    // 简单的报警记录数据模型
    public static class Alarm {
        private String time;
        private String type;

        public Alarm(String time, String type) {
            this.time = time;
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public String getType() {
            return type;
        }
    }
}
