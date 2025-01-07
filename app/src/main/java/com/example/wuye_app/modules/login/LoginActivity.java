package com.example.wuye_app.modules.login;

import android.app.ProgressDialog;
import android.content.Intent;  // 确保引入 Intent
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.MainActivity;
import com.example.wuye_app.R;
import com.example.wuye_app.utils.SharedPreferencesManager;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText captchaEditText;
    private Button loginButton;
    private Button showPasswordButton;
    private CheckBox rememberPasswordCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        captchaEditText = findViewById(R.id.captchaEditText);
        loginButton = findViewById(R.id.loginButton);
        showPasswordButton = findViewById(R.id.showPasswordButton);
        rememberPasswordCheckBox = findViewById(R.id.rememberPasswordCheckBox);

        // 初始化记住密码状态
        boolean rememberPassword = SharedPreferencesManager.getInstance(this).getRememberPassword();
        rememberPasswordCheckBox.setChecked(rememberPassword);
        if (rememberPassword) {
            usernameEditText.setText(SharedPreferencesManager.getInstance(this).getUsername());
            passwordEditText.setText(SharedPreferencesManager.getInstance(this).getPassword());
        }

        showPasswordButton.setOnClickListener(v -> {
            if (passwordEditText.getTransformationMethod() == null) {
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                showPasswordButton.setText("显示密码");
            } else {
                passwordEditText.setTransformationMethod(null);
                showPasswordButton.setText("隐藏密码");
            }
        });

        loginButton.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String captcha = captchaEditText.getText().toString();

        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("密码不能为空");
            return;
        }

        if (TextUtils.isEmpty(captcha)) {
            captchaEditText.setError("验证码不能为空");
            return;
        }

        // 模拟验证码校验
        if (!captcha.equalsIgnoreCase("1234")) { // 示例验证码
            captchaEditText.setError("验证码错误");
            return;
        }

        // 显示加载对话框
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("登录中...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // 模拟登录验证 (使用示例数据)
        new Handler().postDelayed(() -> {
            progressDialog.dismiss();
            if ((username.equals("test") && password.equals("123456")) ||
                    (username.equals("admin") && password.equals("password"))) {
                // 登录成功
                SharedPreferencesManager sp = SharedPreferencesManager.getInstance(LoginActivity.this);
                sp.setLoggedIn(true);
                sp.setUsername(username);
                sp.setRememberPassword(rememberPasswordCheckBox.isChecked());

                if (rememberPasswordCheckBox.isChecked()) {
                    sp.setPassword(password);
                }

                // 跳转到主界面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // 登录失败
                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            }
        }, 2000); // 模拟网络延迟
    }
}
