package com.yossisegev.androidmvpapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yossisegev.androidmvpapplication.presenters.PresentersCache;
import com.yossisegev.androidmvpapplication.presenters.BasePresenter;

/**
 * Created by Yossi Segev
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends AppCompatActivity {

    private static final String TAG = "MvpActivity";
    private T mPresenter;
    private PresentersCache mPresentersCache;
    private String mCacheKey;
    private boolean mStillRelevant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStillRelevant = false;
        mPresentersCache = PresentersCache.getInstance();
    }

    /**
     * Create a new presenter instance.
     * @return Presenter instance.
     */
    protected abstract T createPresenter();

    /**
     * Presenter is loaded.
     * @param presenter The loaded presenter.
     */
    protected abstract void onPresenterLoaded(T presenter);

    /**
     * Loads a presenter instance based on cache key.<br>
     * Flow:<br>
     * 1. Checks if presenter instance already exists - doing nothing.<br>
     * 2. Checks the PresentersCache for cached instance - attempts to recover instance from cache.<br>
     * 3. Finally, If all failed - creates a new presenter instance.<br>
     * @param cacheKey Cache key.
     */
    @SuppressWarnings("unchecked")
    protected void loadPresenter(String cacheKey) {

        mCacheKey = cacheKey;

        // 1. Check if we have one
        if (mPresenter != null) {
            Log.i(TAG, "loadPresenter: Presenter already loaded. Doing nothing.");
        }

        // 2. Maybe we have one in the cache?
        else if (mPresentersCache.contains(cacheKey)) {

            try {
                mPresenter = (T) mPresentersCache.get(cacheKey);
                Log.i(TAG, "loadPresenter: Loaded from cached.");
                onPresenterLoaded(mPresenter);

            } catch (ClassCastException ex) {
                createNewPresenter(mCacheKey);
            }
        }

        // 3. Nope, create new.
        else {
            createNewPresenter(cacheKey);
        }

    }

    /**
     * Creates a new presenter instance by invoking getPresenter() method.<br>
     * Stores the new instance in the cache.
     * @param cacheKey Cache key.
     */
    private void createNewPresenter(String cacheKey) {
        Log.i(TAG, "createNewPresenter: " + cacheKey);
        setPresenter(cacheKey, createPresenter());
        onPresenterLoaded(getPresenter());
    }


    /**
     * Sets presenter as the current instance and save to cache.
     * @param cacheKey Cache key
     * @param presenter Presenter instance
     */
    private void setPresenter(String cacheKey, T presenter) {

        mPresenter = presenter;
        mPresentersCache.add(cacheKey, presenter);
    }

    /**
     * Returns the presenter instance.
     * @return current presenter instance.
     * @throws IllegalStateException if the presenter instance = null;
     */
    protected T getPresenter() {
        if (mPresenter == null) throw new IllegalStateException("Presenter is null. Call loadPresenter()?");
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {

            // Release the view.
            mPresenter.releaseView();

            // Checking if this Activity is still relevant, If not - remove the presenter from the cache.
            if (!mStillRelevant) {
                mPresentersCache.remove(mCacheKey);
                Log.i(TAG, "onDestroy: Not relevant, removed presenter.");
            }

            mPresenter = null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //todo: Is this enough?
        mStillRelevant = true;
    }
}
