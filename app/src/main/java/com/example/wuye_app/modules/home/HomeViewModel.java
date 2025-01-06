// com/example/wuye_app/modules/home/HomeViewModel.java
package com.example.wuye_app.modules.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> communityName = new MutableLiveData<>();

    public HomeViewModel() {
        // 初始化数据，例如从网络或本地数据库加载
        communityName.setValue("崔氏科技");
    }

    public LiveData<String> getCommunityName() {
        return communityName;
    }

    // 可以添加其他 LiveData 和方法来处理首页的数据和业务逻辑
}
