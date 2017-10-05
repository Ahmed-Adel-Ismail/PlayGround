package com.tere.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ahmed Adel Ismail on 5/12/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sum
{

    SmapiEvents value();



    enum SmapiEvents{
        STORY_FLOW, SCREEN_SAVER
    }

}
