package com.tere.playground.rx.lifecycles;

import android.app.Activity;
import android.support.v4.app.Fragment;

import io.reactivex.ObservableTransformer;

/**
 * a factory for all the types of Auto-disposing classes that are used through
 * {@link io.reactivex.Observable#compose(ObservableTransformer)}
 * <p>
 * the available classes are :
 * <p>
 * {@link ActivityObservable}<br>
 * {@link FragmentObservable}<br>
 * <p>
 * Created by Ahmed Adel Ismail on 9/28/2017.
 */
public class LifeCycleDisposable {

    public static <T> ActivityObservable<T> with(Activity activity) {
        return new ActivityObservable<>(activity);
    }

    public static <T> ActivityObservable<T> with(Activity activity, long timeoutMillis) {
        return new ActivityObservable<>(activity, timeoutMillis);
    }

    public static <T> FragmentObservable<T> with(Fragment fragment) {
        return new FragmentObservable<>(fragment);
    }

    public static <T> FragmentObservable<T> with(Fragment fragment, long timeoutMillis) {
        return new FragmentObservable<>(fragment, timeoutMillis);
    }

}
