package com.yossisegev.androidmvpapplication.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yossisegev.androidmvpapplication.R;
import com.yossisegev.androidmvpapplication.adapters.WeatherAdapter;
import com.yossisegev.androidmvpapplication.models.WeatherStatus;
import com.yossisegev.androidmvpapplication.mvpviews.MainMvpView;
import com.yossisegev.androidmvpapplication.presenters.MainPresenterImpl;

import java.util.List;

public class MainActivity extends BaseMvpActivity<MainPresenterImpl> implements MainMvpView {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button refreshButton = (Button) findViewById(R.id.refreshBtn);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getWeatherList();
            }
        });

        loadPresenter(MainPresenterImpl.CACHE_KEY);
    }

    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void onPresenterLoaded(MainPresenterImpl presenter) {
        presenter.attachView(this);
    }


    @Override
    public void showWeatherList(List<WeatherStatus> weatherStatusList) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        WeatherAdapter adapter = new WeatherAdapter(weatherStatusList);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showLoading() {
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, R.string.error_msg, Toast.LENGTH_LONG).show();
    }
}
