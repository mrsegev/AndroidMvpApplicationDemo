package com.yossisegev.androidmvpapplication.services;

import com.yossisegev.androidmvpapplication.models.WeatherResponse;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Yossi Segev
 */
public class WeatherService {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    // I know I know.. but it's just a test app so... yeah.
    private static final String API_KEY = "3b1beb045a72855aedad04594a94753b";

    private WeatherApi mApi;

    public WeatherService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = retrofit.create(WeatherApi.class);

    }

    public WeatherApi getWeatherApi() {
        return mApi;
    }

    public interface WeatherApi {
        @GET("group?units=metric&appId=" + API_KEY)
        Observable<WeatherResponse> getWeatherById(@Query("id") String idList);

    }
}
