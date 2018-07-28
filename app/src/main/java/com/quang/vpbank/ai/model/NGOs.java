package com.quang.vpbank.ai.model;

public class NGOs {
    private int avatar;
    private String name;
    private String description;

    public NGOs() {
    }

    public NGOs(int avatar, String name, String description) {
        this.avatar = avatar;
        this.name = name;
        this.description = description;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
