package com.quang.vpbank.ai.model;

public class DailySeed {
    private String name;
    private String promote;
    private String detail;

    public DailySeed(String name, String promote, String detail) {
        this.name = name;
        this.promote = promote;
        this.detail = detail;
    }

    public DailySeed() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPromote() {
        return promote;
    }

    public void setPromote(String promote) {
        this.promote = promote;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}