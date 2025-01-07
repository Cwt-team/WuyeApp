package com.example.wuye_app.data.remote;

import com.example.wuye_app.data.model.HomeDataResponse;
import com.example.wuye_app.data.model.Notification;
import com.example.wuye_app.modules.login.LoginRequest;
import com.example.wuye_app.modules.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @GET("community/notifications")
    Call<List<Notification>> getNotifications();

    @GET("home") // 替换为你的实际获取首页数据的 API 端点
    Call<HomeDataResponse> getHomeData();

    @POST("login") // 替换为你的实际登录接口地址
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // 定义其他 API 接口
}
