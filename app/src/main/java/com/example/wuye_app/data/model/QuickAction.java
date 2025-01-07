package com.example.wuye_app.data.model;

public class QuickAction {
    private String name;
    private String icon; // 假设你的 icon 是一个字符串，代表 drawable 的名称

    public QuickAction() {
    }

    public QuickAction(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
