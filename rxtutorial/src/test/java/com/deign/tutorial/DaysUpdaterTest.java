package com.deign.tutorial;

import org.junit.Test;

import java.util.Date;

/**
 * Created by Ahmed Adel Ismail on 9/20/2017.
 */
public class DaysUpdaterTest {

    @Test
    public void foo() throws Exception {
        System.out.print(new DaysUpdater().apply(new Date(),1));

    }
}