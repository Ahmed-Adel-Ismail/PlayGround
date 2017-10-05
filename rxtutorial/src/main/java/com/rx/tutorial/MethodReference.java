package com.rx.tutorial;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Ahmed Adel Ismail on 9/16/2017.
 */
@SuppressWarnings("all")
public class MethodReference implements Action {

    @Override
    public void run() throws Exception {
        consumer().accept(runnable());

        // same as the previous line, this creates A Runnable for you similar to
        // the one in runnable() method, it checks that the referenced method return type
        // and parameters matches the "Runnable.run()" method, if true, it creates
        // the Runnable for you
        consumer().accept(this::invokeRun);
    }

    private Consumer<Runnable> consumer() {
        return new Consumer<Runnable>() {
            @Override
            public void accept(Runnable runnable) {
                runnable.run();
            }
        };
    }

    private Runnable runnable() {
        return new Runnable() {
            @Override
            public void run() {
                invokeRun();
            }
        };
    }

    private void invokeRun() {
        System.out.print("invoke runnable");
    }


}
