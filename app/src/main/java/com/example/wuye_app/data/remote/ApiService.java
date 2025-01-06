package com.example.wuye_app.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("community/notifications")
    Call<List<Notification>> getNotifications();

    // 定义其他 API 接口
}
