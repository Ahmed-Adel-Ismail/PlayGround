package com.tere.aspects;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Ahmed Adel Ismail on 5/12/2017.
 */
@Aspect
public class MainPresenterAspect
{

    @Before("@annotation(com.tere.aspects.Sum)")
    void onClickEventDetected(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object value = methodSignature.getMethod().getAnnotation(Sum.class).value();
        // send to memory cache
    }

    @Around("@annotation(com.tere.aspects.Sum)")
    public Object doSumForAnnotatedMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        Log.e("aspects", "aspect:::" + joinPoint.getSignature());
        int result = (int) joinPoint.proceed();
        if (result < 0) {
            int valueOne = (int) joinPoint.getArgs()[0];
            int valueTwo = (int) joinPoint.getArgs()[1];
            result = valueOne + valueTwo;
        }
        return result;
    }

    @Around("@annotation(com.tere.aspects.Load)")
    public Object loadValue(ProceedingJoinPoint joinPoint) throws Throwable{
        Log.e("aspects", "aspect:::" + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        if (result == null) {
            result = new Object();
        }
        return result;
    }
}
