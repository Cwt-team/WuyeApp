// com/example/wuye_app/data/model/HomeDataResponse.java
package com.example.wuye_app.data.model;

import java.util.List;

public class HomeDataResponse {
    private String userName;
    private String communityName;
    private List<QuickAction> quickActions;
    private List<LifeService> lifeServices;

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public List<QuickAction> getQuickActions() {
        return quickActions;
    }

    public void setQuickActions(List<QuickAction> quickActions) {
        this.quickActions = quickActions;
    }

    public List<LifeService> getLifeServices() {
        return lifeServices;
    }

    public void setLifeServices(List<LifeService> lifeServices) {
        this.lifeServices = lifeServices;
    }
}
