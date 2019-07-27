package com.sajorahasan.demoapp.model;

import com.google.gson.annotations.SerializedName;

public class DemoItem {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String desc;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
