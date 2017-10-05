package com.rx.tutorial;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Action;

/**
 * Created by Ahmed Adel Ismail on 9/16/2017.
 */
@SuppressWarnings("all")
public class Basics implements Action {

    @Override
    public void run() {

    }

    private void printNumber() {
        Observable.just(1)
                .subscribe(System.out::println);

        // result :
        // 1
    }

    private void printCollectionOfNumbers() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Observable.fromIterable(integers)
                .subscribe(System.out::println);

        // result :
        // 1
        // 2
        // 3
        // 4
        // 5
        // 6
    }

    private void printEvenNumbersInCollectionOfNumbers() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Observable.fromIterable(integers)
                .filter((integer) -> integer % 2 == 0) // only accept even numbers
                .subscribe(System.out::println);

        // result :
        // 2
        // 4
        // 6
    }

    private void printEvenNumbersWichAreLessThanTenInCollectionOfNumbers() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 120, 130, 140, 150);
        Observable.fromIterable(integers)
                .filter((integer) -> integer % 2 == 0) // only accept even numbers
                .filter((integer) -> integer < 10) // only accept less than 10
                .subscribe(System.out::println);

        // result :
        // 2
        // 4
        // 6
    }

    private void printCollectionOfNumbersMultipliedByTen() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Observable.fromIterable(integers)
                .map((integer) -> integer * 10) // convert to the number multiplied by 10
                .subscribe(System.out::println);

        // result :
        // 10
        // 20
        // 30
        // 40
        // 50
        // 60
    }

    private void printCollectionOfNumbersAsStrings() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Observable.fromIterable(integers)
                .map((integer) -> "value : " + integer) // convert to String
                .subscribe(System.out::println);

        // result :
        // value : 1
        // value : 2
        // value : 3
        // value : 4
        // value : 5
        // value : 6
    }

    private void printCollectionOfEvenNumbersMultipliedByTen() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Observable.fromIterable(integers)
                .filter((integer) -> integer % 2 == 0) // only accept even numbers
                .map((integer) -> integer * 10) // convert to the number multiplied by 10
                .subscribe(System.out::println);

        // result :
        // 20
        // 40
        // 60
    }


    private void printValueFromOperation() {
        Callable<Integer> operation = () -> {
            Thread.sleep(1000);
            return 1;
        };

        Observable.fromCallable(operation)
                .subscribe(System.out::println);

        // result :
        // 1
    }


    private void printResultsFromMultipleOperationsInOrder() {

        Callable<Integer> operationOne = () -> {
            Thread.sleep(1000);
            return 1;
        };

        Callable<Integer> operationTwo = () -> {
            Thread.sleep(2000);
            return 2;
        };

        Observable<Integer> observableOne = Observable.fromCallable(operationOne);
        Observable<Integer> observableTwo = Observable.fromCallable(operationTwo);

        Observable.concat(observableOne, observableTwo)
                .subscribe(System.out::println);

        // result :
        // 1
        // 2

    }

    private void printResultsFromMultipleOperationsWithoutOrdering() {

        Callable<Integer> operationOne = () -> {
            Thread.sleep(1000);
            return 1;
        };

        Callable<Integer> operationTwo = () -> {
            Thread.sleep(2000);
            return 2;
        };

        Observable<Integer> observableOne = Observable.fromCallable(operationOne);
        Observable<Integer> observableTwo = Observable.fromCallable(operationTwo);

        Observable.merge(observableOne, observableTwo)
                .subscribe(System.out::println);

        // result : Order not guaranteed
        // 1
        // 2

    }

    private void convertResultsFromMultipleOperationsToList() {

        Callable<Integer> operationOne = () -> {
            Thread.sleep(1000);
            return 1;
        };

        Callable<Integer> operationTwo = () -> {
            Thread.sleep(2000);
            return 2;
        };

        Observable<Integer> observableOne = Observable.fromCallable(operationOne);
        Observable<Integer> observableTwo = Observable.fromCallable(operationTwo);

        List<Integer> list = Observable.concat(observableOne, observableTwo)
                .toList().blockingGet();

        // result :
        // [1,2]

    }

    private void convertResultsFromMultipleOperationsOfSameTypeIntoOneResult() {

        Callable<Integer> operationOne = () -> {
            Thread.sleep(1000);
            return 1;
        };

        Callable<Integer> operationTwo = () -> {
            Thread.sleep(2000);
            return 2;
        };

        Observable<Integer> observableOne = Observable.fromCallable(operationOne);
        Observable<Integer> observableTwo = Observable.fromCallable(operationTwo);

        Observable.zip(observableOne, observableTwo,
                (resultOne, resultTwo) -> resultOne + resultTwo)
                .subscribe(System.out::println);

        // result :
        // 3

    }

    private void convertResultsFromMultipleOperationsOfDifferentTypesIntoOneResult() {

        Callable<String> operationOne = () -> {
            return "values ";
        };

        Callable<Integer> operationTwo = () -> {
            Thread.sleep(1000);
            return 1;
        };

        Callable<Integer> operationThree = () -> {
            Thread.sleep(1000);
            return 2;
        };

        Observable<String> observableOne = Observable.fromCallable(operationOne);
        Observable<Integer> observableTwo = Observable.fromCallable(operationTwo);
        Observable<Integer> observableThree = Observable.fromCallable(operationTwo);

        Observable.zip(observableOne, observableTwo, observableThree,
                (resultOne, resultTwo, resultThree) -> resultOne + ":" + resultTwo + "," + resultThree)
                .subscribe(System.out::println);

        // result :
        // values :1,2
    }

    private void iterateOverTwoCollectionsAndPrintItemsInParallel() {
        List<String> alphabets = Arrays.asList("A", "B", "C", "D");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        Observable<String> alphabetsObservable = Observable.fromIterable(alphabets);
        Observable<Integer> numbersObservable = Observable.fromIterable(numbers);

        Observable.zip(alphabetsObservable, numbersObservable,
                (letter, number) -> letter + " = " + number)
                .subscribe(System.out::println);

        // result :
        // A = 1
        // B = 2
        // C = 3
        // D = 4
    }


}
