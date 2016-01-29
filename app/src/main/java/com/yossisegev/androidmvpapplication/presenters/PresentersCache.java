package com.yossisegev.androidmvpapplication.presenters;


import java.util.HashMap;

/**
 * Created by Yossi Segev
 */
public class PresentersCache {

    private static PresentersCache mInstance;
    private HashMap<String, BasePresenter> mCache;

    public static PresentersCache getInstance() {

        if (mInstance == null) {
            mInstance = new PresentersCache();
        }

        return mInstance;
    }

    private PresentersCache() {
        mCache = new HashMap<>();
    }

    /**
     * Add presenter instance to cache.
     * @param key Cache key
     * @param presenter Presenter instance
     */
    public void add(String key, BasePresenter presenter) {

        if (mCache != null) {
            mCache.put(key, presenter);
        }
    }

    /**
     * Check if key is stored in cache.
     * @param key Cache key
     * @return boolean
     */
    public boolean contains(String key) {
        return mCache != null && mCache.containsKey(key);
    }

    /**
     * Retrieve presenter instance from cache based on cache key.
     * @param key Cache key
     * @return Presenter instance or null if no instance is found.
     */
    public BasePresenter get(String key) {
        if (mCache != null && mCache.containsKey(key)) {
            return mCache.get(key);
        } else {
            return null;
        }
    }

    /**
     * Remove presenter instance from cache.
     * @param key presenter cache key
     */
    public void remove(String key) {
        if (mCache != null && mCache.containsKey(key)) {
            mCache.remove(key);
        }
    }
}
