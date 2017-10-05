package com.ahmedadel.kotlinapp

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Ahmed Adel Ismail on 10/3/2017.
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }

    }
}
