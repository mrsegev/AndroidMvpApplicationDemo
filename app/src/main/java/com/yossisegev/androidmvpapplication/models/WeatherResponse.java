package com.yossisegev.androidmvpapplication.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Yossi Segev
 */
public class WeatherResponse {

    
    @SerializedName("list")
    private List<WeatherStatus> mWeatherStatusList;

    public List<WeatherStatus> getWeatherStatusList() {
        return mWeatherStatusList;
    }

    public void setWeatherStatusList(List<WeatherStatus> weatherStatusList) {
        mWeatherStatusList = weatherStatusList;
    }
}
