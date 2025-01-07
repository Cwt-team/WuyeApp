// com/example/wuye_app/data/model/User.java
package com.example.wuye_app.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "user") // You can specify the table name
public class User {
    @PrimaryKey
    @NonNull
    private String userId;
    private String username;
    private String fullName;
    private String email;
    // 可以添加其他用户信息

    public User() {
    }

    @Ignore // 添加 @Ignore 注解，告诉 Room 忽略此构造函数
    public User(String userId, String username, String fullName, String email) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
