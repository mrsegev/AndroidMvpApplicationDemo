package com.yossisegev.androidmvpapplication.presenters;

import com.yossisegev.androidmvpapplication.mvpviews.BaseMvpView;

import java.lang.ref.WeakReference;

/**
 * Created by Yossi Segev
 */
public abstract class BasePresenter<V extends BaseMvpView> {

    private WeakReference<V> mViewWeakReference;


    public BasePresenter() {

    }

    /**
     * Attach a view to the presenter.
     * @param view View to attach.
     */
    public void attachView(V view) {
        mViewWeakReference = new WeakReference<>(view);
        onViewAttached(mViewWeakReference.get());
    }

    /**
     * Remove currently attached view from the presenter.
     */
    public void releaseView() {
        if (mViewWeakReference != null) {
            mViewWeakReference.clear();
            onViewReleased();
        }
    }

    /**
     * Get the currently attached view.
     * @return The attached view or null if no view is attached.
     */
    protected V getView() {
        if (mViewWeakReference != null) {
            return mViewWeakReference.get();
        } else {
            return null;
        }
    }

    /**
     * Check if a view is currently attached to the presenter.
     * @return boolean
     */
    public boolean isViewAttached() {
        return mViewWeakReference != null &&
                mViewWeakReference.get() != null;
    }

    /**
     * View is now attached to the presenter.
     * @param view The attached view
     */
    protected void onViewAttached(V view) {
    }


    /**
     * View was removed from the presenter
     */
    protected void onViewReleased() {
    }


}
