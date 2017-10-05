package com.vodafone;

import android.app.Application;

import java.lang.ref.WeakReference;

/**
 * Created by Ahmed Adel Ismail on 5/12/2017.
 */

public class Aspects
{

    private static WeakReference<Application> application;

    public static void initialize(Application application) {
        Aspects.application = new WeakReference<>(application);
    }

    public static Application getApplication() {
        return application.get();
    }
}
