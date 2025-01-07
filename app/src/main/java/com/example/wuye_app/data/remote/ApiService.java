// com/example/wuye_app/data/remote/ApiService.java
package com.example.wuye_app.data.remote;

import com.example.wuye_app.data.model.Notification;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ApiService {
    @GET("community/notifications")
    Call<List<Notification>> getNotifications();

    // 定义其他 API 接口
}
