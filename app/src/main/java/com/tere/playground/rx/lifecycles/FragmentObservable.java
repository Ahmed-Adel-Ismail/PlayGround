package com.tere.playground.rx.lifecycles;

import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * a class that can be used with {@link Observable#compose(ObservableTransformer)} to
 * automatically dispose the {@link Disposable} if the {@link Fragment} is destroyed
 * <p>
 * notice that this class wont help with long running operations ... like server requests for
 * example, in every call to {@link Observer#onNext(Object)} or
 * {@link Observer#onError(Throwable)} or  {@link Observer#onComplete()}, it will check
 * if the {@link Fragment} {@link WeakReference} is not de-referenced, and that
 * {@link Fragment#onDestroy()} is not invoked yet, and that the {@link Fragment#isAdded()}
 * is {@code true}
 * <p>
 * this class does not listen on the life cycle of the activity, how ever, it will not hold
 * reference to the activity if it is destroyed, and it will dispose the {@link Disposable}
 * if any {@link Observer} method attempts to get invoked after the call to
 * {@link Fragment#onDestroy()}
 * <p>
 * this class will not cause memory leaks, but using instance anonymous classes in the
 * {@link Observable#subscribe(Consumer, Consumer, Action)} method will cause the {@link Fragment}
 * to stay in the heap until the {@link Disposable} is disposed, then it will be released
 * <p>
 * this is the case with any usage to Rx-Java in general, and this class wont help
 * avoiding this behavior
 * <p>
 * to avoid holding reference to {@link Fragment} so long, there is a timeout parameter, which
 * forces the {@link Disposable} to dispose if exceeded ... the default value is
 * {@link #DEFAULT_TIME_OUT_MILLIS}
 * <p>
 * Created by Ahmed Adel Ismail on 9/27/2017.
 */
class FragmentObservable<T> implements ObservableTransformer<T, T> {

    /**
     * the default timeout is 10 seconds
     */
    private static final long DEFAULT_TIME_OUT_MILLIS = 10000;
    private final WeakReference<Fragment> fragmentReference;
    private final long timeoutMillis;

    FragmentObservable(Fragment fragment) {
        this(fragment, DEFAULT_TIME_OUT_MILLIS);
    }

    FragmentObservable(Fragment fragment, long timeoutMillis) {
        this.fragmentReference = new WeakReference<>(fragment);
        this.timeoutMillis = timeoutMillis;
    }

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.timeout(timeoutMillis, TimeUnit.MILLISECONDS)
                .lift((ObservableOperator<T, T>) this::attachOnLifeCycle);
    }

    private Observer<T> attachOnLifeCycle(@NonNull final Observer<? super T> observer) {
        return new Observer<T>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull T t) {
                if (isAlive(fragmentReference.get())) {
                    observer.onNext(t);
                } else if (!disposable.isDisposed()) {
                    disposable.dispose();
                }
            }

            private boolean isAlive(@Nullable Fragment fragment) {
                return !(fragment == null
                        || fragment.isRemoving()
                        || fragment.getActivity() == null
                        || fragment.isDetached()
                        || !fragment.isAdded()
                        || fragment.getView() == null);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (isAlive(fragmentReference.get())) {
                    invokeOnError(e);
                } else if (!disposable.isDisposed()) {
                    disposable.dispose();
                }

            }

            private void invokeOnError(@NonNull Throwable e) {
                if (e instanceof TimeoutException) {
                    disposeOnTimeOutException(e);
                } else {
                    observer.onError(e);
                }
            }

            private void disposeOnTimeOutException(@NonNull Throwable e) {
                e.printStackTrace();
                if (!disposable.isDisposed()) disposable.dispose();
            }

            @Override
            public void onComplete() {
                if (isAlive(fragmentReference.get())) {
                    observer.onComplete();
                } else if (!disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
        };
    }


}