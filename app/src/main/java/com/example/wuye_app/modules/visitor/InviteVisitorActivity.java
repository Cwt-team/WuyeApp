// com/example/wuye_app/modules/visitor/InviteVisitorActivity.java
package com.example.wuye_app.modules.visitor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Calendar;

public class InviteVisitorActivity extends AppCompatActivity {

    private EditText visitorNameEditText;
    private EditText visitorPhoneEditText;
    private EditText visitTimeEditText;
    private EditText notesEditText;
    private Button generateCodeButton;
    private TextView invitationCodeTextView;
    private ImageView qrCodeImageView;

    public static Intent newIntent(Context context) {
        return new Intent(context, InviteVisitorActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_visitor);

        visitorNameEditText = findViewById(R.id.visitorNameEditText);
        visitorPhoneEditText = findViewById(R.id.visitorPhoneEditText);
        visitTimeEditText = findViewById(R.id.visitTimeEditText);
        notesEditText = findViewById(R.id.notesEditText);
        generateCodeButton = findViewById(R.id.generateCodeButton);
        invitationCodeTextView = findViewById(R.id.invitationCodeTextView);
        qrCodeImageView = findViewById(R.id.qrCodeImageView);

        visitTimeEditText.setOnClickListener(v -> showDateTimePicker());

        generateCodeButton.setOnClickListener(v -> generateInvitationCode());
    }

    private void showDateTimePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, yearOfYear, monthOfYear, dayOfMonth) -> {
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(InviteVisitorActivity.this,
                            (view1, hourOfDay, minuteOfHour) -> {
                                visitTimeEditText.setText(String.format("%d-%02d-%02d %02d:%02d", yearOfYear, monthOfYear + 1, dayOfMonth, hourOfDay, minuteOfHour));
                            }, hour, minute, true);
                    timePickerDialog.show();
                }, year, month, day);
        datePickerDialog.show();
    }

    private void generateInvitationCode() {
        String visitorName = visitorNameEditText.getText().toString();
        String visitorPhone = visitorPhoneEditText.getText().toString();
        String visitTime = visitTimeEditText.getText().toString();
        String notes = notesEditText.getText().toString();

        if (visitorName.isEmpty() || visitorPhone.isEmpty() || visitTime.isEmpty()) {
            Toast.makeText(this, "请填写访客姓名、电话和到访时间", Toast.LENGTH_SHORT).show();
            return;
        }

        // 生成邀请码的逻辑 (这里只是一个示例)
        String invitationCode = "INV-" + System.currentTimeMillis();
        invitationCodeTextView.setText(invitationCode);

        // 生成二维码
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(invitationCode, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? getResources().getColor(android.R.color.black) : getResources().getColor(android.R.color.white));
                }
            }
            qrCodeImageView.setImageBitmap(bmp);
            qrCodeImageView.setVisibility(View.VISIBLE);
        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(this, "生成二维码失败", Toast.LENGTH_SHORT).show();
        }
    }
}
