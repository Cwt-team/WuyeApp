// com/example/wuye_app/modules/login/LoginActivity.java
package com.example.wuye_app.modules.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wuye_app.MainActivity;
import com.example.wuye_app.R;
import com.example.wuye_app.data.remote.ApiService;
import com.example.wuye_app.data.remote.LoginRequest;
import com.example.wuye_app.data.remote.LoginResponse;
import com.example.wuye_app.utils.RetrofitClient;
import com.example.wuye_app.utils.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        // 显示加载对话框
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("登录中...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // 调用API进行登录
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<LoginResponse> call = apiService.login(new LoginRequest(username, password, captcha));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        // 保存登录状态
                        SharedPreferencesManager.getInstance(LoginActivity.this)
                                .setLoggedIn(true)
                                .setUsername(username)
                                .setRememberPassword(rememberPasswordCheckBox.isChecked());

                        if (rememberPasswordCheckBox.isChecked()) {
                            SharedPreferencesManager.getInstance(LoginActivity.this)
                                    .setPassword(password);
                        }

                        // 跳转到主界面
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "网络连接失败，请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
