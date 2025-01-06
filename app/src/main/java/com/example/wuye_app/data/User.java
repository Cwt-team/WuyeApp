// com/example/wuye_app/data/User.java
package com.example.wuye_app.data;

public class User {
    private String userId;
    private String username;
    private String fullName;
    private String email;
    // 可以添加其他用户信息

    public User() {
    }

    public User(String userId, String username, String fullName, String email) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
