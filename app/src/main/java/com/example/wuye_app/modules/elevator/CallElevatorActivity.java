// com/example/wuye_app/modules/elevator/CallElevatorActivity.java
package com.example.wuye_app.modules.elevator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;

public class CallElevatorActivity extends AppCompatActivity {

    private NumberPicker floorPicker;
    private Button callButton;
    private TextView elevatorStatusTextView;

    public static Intent newIntent(Context context) {
        return new Intent(context, CallElevatorActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_elevator);

        floorPicker = findViewById(R.id.floorPicker);
        callButton = findViewById(R.id.callButton);
        elevatorStatusTextView = findViewById(R.id.elevatorStatusTextView);

        // 设置 NumberPicker 的最小值和最大值
        floorPicker.setMinValue(1);
        floorPicker.setMaxValue(30); // 假设最高 30 层
        floorPicker.setValue(1); // 默认值

        callButton.setOnClickListener(v -> callElevator());
    }

    private void callElevator() {
        int selectedFloor = floorPicker.getValue();
        Toast.makeText(this, "呼叫电梯到 " + selectedFloor + " 层", Toast.LENGTH_SHORT).show();
        // 执行呼叫电梯的逻辑

        // 模拟电梯状态更新
        updateElevatorStatus("正在呼叫...");
        elevatorStatusTextView.postDelayed(() -> updateElevatorStatus("电梯正在上行"), 2000);
        elevatorStatusTextView.postDelayed(() -> updateElevatorStatus("电梯已到达 " + selectedFloor + " 层"), 5000);
    }

    private void updateElevatorStatus(String status) {
        elevatorStatusTextView.setText(status);
    }
}
