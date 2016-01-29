package com.yossisegev.androidmvpapplication.presenters;

import com.yossisegev.androidmvpapplication.models.WeatherResponse;
import com.yossisegev.androidmvpapplication.mvpviews.MainMvpView;
import com.yossisegev.androidmvpapplication.services.WeatherService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Yossi Segev
 */
public class MainPresenterImpl extends BasePresenter<MainMvpView> implements MainPresenter {

    public static final String CACHE_KEY = "main_presenter";
    private static final String CITIES_IDS = "2643743,6455259,2950159,2759794,5128638"; // OpenWeatherMap ids

    private WeatherResponse mWeatherResponse;

    /**
     * Fetch weather status from selected cities around the world.
     */
    @Override
    public void getWeatherList() {

        if (isViewAttached()) {
            getView().showLoading();
        }

        WeatherService weatherService = new WeatherService();
        Observable<WeatherResponse> observable = weatherService.getWeatherApi().getWeatherById(CITIES_IDS);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().onError(e);
                        }
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        mWeatherResponse = weatherResponse;
                        if (isViewAttached()) {
                            getView().showWeatherList(mWeatherResponse.getWeatherStatusList());
                        }
                    }
                });
    }

    @Override
    protected void onViewAttached(MainMvpView view) {
        super.onViewAttached(view);

        // Display existing response if we have one.
        if (mWeatherResponse != null) {
            getView().showWeatherList(mWeatherResponse.getWeatherStatusList());
        }
    }
}
