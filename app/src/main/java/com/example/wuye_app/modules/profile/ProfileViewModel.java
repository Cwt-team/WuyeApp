// com/example/wuye_app/modules/profile/ProfileViewModel.java
package com.example.wuye_app.modules.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> userPhoneNumber = new MutableLiveData<>();
    private MutableLiveData<String> userPropertyInfo = new MutableLiveData<>();

    public ProfileViewModel() {
        // 初始化数据，例如从 SharedPreferences 或本地数据库加载
        userPhoneNumber.setValue("13800138000");
        userPropertyInfo.setValue("崔氏科技 1区-1栋-1单元-0102");
    }

    public LiveData<String> getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public LiveData<String> getUserPropertyInfo() {
        return userPropertyInfo;
    }

    // 可以添加其他 LiveData 和方法来处理个人资料的数据和业务逻辑
}
