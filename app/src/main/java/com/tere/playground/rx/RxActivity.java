package com.tere.playground.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tere.playground.R;
import com.tere.playground.rx.lifecycles.LifeCycleDisposable;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ahmed Adel Ismail on 9/27/2017.
 */
public class RxActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);

        Observable.create((ObservableOnSubscribe<String>)
                observer -> {
                    try {
                        Thread.sleep(5000);
                    } catch (Throwable e) {

                    }
                    observer.onNext("TEST");
                    observer.onComplete();
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(LifeCycleDisposable.with(this))
                .subscribe(textView::setText);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        textView = null;
    }
}
