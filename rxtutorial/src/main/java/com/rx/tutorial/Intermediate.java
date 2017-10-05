package com.rx.tutorial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * Created by Ahmed Adel Ismail on 9/16/2017.
 */

public class Intermediate implements Action {

    public static void main(String... args) throws Exception {
        new Intermediate().run();
    }

    @Override
    public void run() throws Exception {


        List<Integer> list = Arrays.asList(1, 2, 3, 4);

//        Observable.fromIterable(list)
//                .flatMap(integer -> {
//                    switch (integer) {
//                        case 1:
//                            return Observable.just("First Odd Number : " + integer);
//                        case 2:
//                            return Observable.just("First Even Number : " + integer);
//                        default:
//                            return Observable.empty();
//                    }
//                })
//                .firstElement()
//                .subscribe(System.out::println);


        Map<Integer, Function<Integer, String>> caseBlocks = new HashMap<>(2);
        caseBlocks.put(1, (integer) -> "First Odd Number : " + integer);
        caseBlocks.put(2, (integer) -> "First Even Number : " + integer);

        Observable.fromIterable(list)
                .lift(new SwitchCaseBreak<>(caseBlocks))
                .subscribe(System.out::println);


    }
}


/**
 * An {@link ObservableOperator} that enables the switch-case behavior in Rx-Java, it uses
 * a table look up technique to simulate a switch-case behavior
 * <p>
 * you must supply a {@link Map} that holds functions that will be executed if the emitted
 * item matches the key they are mapped to
 * <p>
 * if multiple emitted items matches several keys, all there functions will be executed and
 * will emit there results, if you want the first match to disable the rest of the checks,
 * you can use {@link SwitchCaseBreak} instead
 * <p>
 * sample code :
 * <p>
 * {@code List<Integer> list = Arrays.asList(1, 2, 3, 4);}<br>
 * {@code Map<Integer, Function<Integer, String>> caseBlocks = new HashMap<>(2);}<br>
 * {@code caseBlocks.put(2, (i) -> "TWO SELECTED");}<br>
 * {@code caseBlocks.put(3, (i) -> "THREE SELECTED");}<br>
 * <p>
 * {@code Observable.fromIterable(list)}<br>
 * {@code .lift(new SwitchCase<>(caseBlocks))}<br>
 * {@code .subscribe(System.out::println);}<br>
 * <p>
 * // result :<br>
 * TWO SELECTED<br>
 * THREE SELECTED<br>
 *
 * @param <T> the type of the items that will be passed to the {@code switch} statement
 * @param <R> the type of the items that will be returned from the {@code case} block execution
 */
class SwitchCase<T, R> implements ObservableOperator<R, T> {

    final Map<T, Function<T, R>> caseBlocks = new LinkedHashMap<>();

    /**
     * create a {@link SwitchCase} operator, if any {@link Function} returned {@code null}, the
     * whole operation will crash
     *
     * @param caseBlocks the map that holds {@link Function} that are the case-blocks in this
     *                   switch-case
     */
    public SwitchCase(@NonNull Map<T, Function<T, R>> caseBlocks) {
        if (caseBlocks != null) {
            this.caseBlocks.putAll(caseBlocks);
        }
    }

    @Override
    public Observer<? super T> apply(final Observer<? super R> observer) throws Exception {
        return createResultObserver(observer);
    }

    private Observer<T> createResultObserver(final Observer<? super R> observer) {
        return new Observer<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull T switchOn) {

                Function<T, R> block = Maybe.just(caseBlocks)
                        .filter(map -> map.containsKey(switchOn))
                        .map(map -> map.get(switchOn))
                        .blockingGet();

                if (block == null) {
                    return;
                }

                try {
                    invokeOnNext(observer, block.apply(switchOn));
                } catch (Throwable e) {
                    onError(e);
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                observer.onComplete();
            }
        };
    }

    void invokeOnNext(Observer<? super R> observer, R onNextValue) {
        observer.onNext(onNextValue);
    }


}

/**
 * same as {@link SwitchCase} but it emits only one item which is the result of the
 * {@link Function} mapped to the key matching the first emitted value
 * <p>
 * sample code :
 * <p>
 * {@code List<Integer> list = Arrays.asList(1, 2, 3, 4);}<br>
 * {@code Map<Integer, Function<Integer, String>> caseBlocks = new HashMap<>(2);}<br>
 * {@code caseBlocks.put(2, (i) -> "TWO SELECTED");}<br>
 * {@code caseBlocks.put(3, (i) -> "THREE SELECTED");}<br>
 * <p>
 * {@code Observable.fromIterable(list)}<br>
 * {@code .lift(new SwitchCaseBreak<>(caseBlocks))}<br>
 * {@code .subscribe(System.out::println);}<br>
 * <p>
 * // result :<br>
 * TWO SELECTED<br>
 */
class SwitchCaseBreak<T, R> extends SwitchCase<T, R> {

    public SwitchCaseBreak(Map<T, Function<T, R>> caseBlocks) {
        super(caseBlocks);
    }

    @Override
    void invokeOnNext(Observer<? super R> observer, R onNextValue) {
        super.invokeOnNext(observer, onNextValue);
        caseBlocks.clear();
    }
}