package com.yossisegev.androidmvpapplication.mvpviews;

import com.yossisegev.androidmvpapplication.models.WeatherStatus;

import java.util.List;

/**
 * Created by Yossi Segev
 */
public interface MainMvpView extends BaseMvpView {

    void showWeatherList(List<WeatherStatus> weatherStatusList);
    void showLoading();
    void onError(Throwable e);
}
