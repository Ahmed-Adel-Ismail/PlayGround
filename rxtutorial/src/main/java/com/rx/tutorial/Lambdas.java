package com.rx.tutorial;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Ahmed Adel Ismail on 9/16/2017.
 */
@SuppressWarnings("all")
public class Lambdas implements Action {

    @Override
    public void run() throws Exception {
        runnableClass().run();
        runnableLambda().run();
        consumerClass().accept(0);
        consumerLambda().accept(1);

    }

    private Runnable runnableClass() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.print("Runnable : anonymous inner class");
            }
        };
    }

    /**
     * same as runnableClass()
     */
    private Runnable runnableLambda() {
        return () -> System.out.print("Runnable : lambda");
    }


    private Consumer<Integer> consumerClass() {
        return new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.print("Consumer class : integer = " + integer);
            }
        };
    }

    /**
     * same as consumerClass()
     */
    private Consumer<Integer> consumerLambda() {
        return integer -> System.out.print("Consumer lambda : integer = " + integer);
    }


}
