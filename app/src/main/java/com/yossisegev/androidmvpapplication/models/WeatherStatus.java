
package com.yossisegev.androidmvpapplication.models;

import com.google.gson.annotations.SerializedName;

public class WeatherStatus {

    @SerializedName("main")
    private MainData mMainData;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;


    public MainData getMainData() {
        return mMainData;
    }

    public void setMainData(MainData mainData) {
        this.mMainData = mainData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
