// com/example/wuye_app/modules/login/LoginActivity.java
package com.example.wuye_app.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.MainActivity;
import com.example.wuye_app.R;
import com.example.wuye_app.utils.SharedPreferencesManager;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("密码不能为空");
            return;
        }

        // 模拟登录验证
        if (username.equals("test") && password.equals("123456")) {
            // 保存登录状态
            SharedPreferencesManager.getInstance(this).setLoggedIn(true);
            // 跳转到主界面
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
