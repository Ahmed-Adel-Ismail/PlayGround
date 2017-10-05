package com.tere.playground.apt;

import com.annotations.Command;
import com.annotations.CommandsMapFactory;
import com.mapper.CommandsMap;

/**
 * Created by Ahmed Adel Ismail on 9/9/2017.
 */
@CommandsMapFactory
class Printer {

    private CommandsMap map = CommandsMap.of(this);

    public void takeAction(int key){
        map.execute(key);
    }

    @Command(1)
    void printOne() {
        System.out.println("ONE RECEIVED");
    }

    @Command(2)
    void printTwo(String s1, String s2) {
        System.out.println("TWO RECEIVED");
    }
}
