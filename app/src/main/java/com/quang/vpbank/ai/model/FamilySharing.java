package com.quang.vpbank.ai.model;

public class FamilySharing {
    private String name;
    private String date;
    private String money;

    public FamilySharing() {
    }

    public FamilySharing(String name, String date, String money) {
        this.name = name;
        this.date = date;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
