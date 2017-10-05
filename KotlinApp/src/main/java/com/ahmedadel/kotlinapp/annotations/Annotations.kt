package com.ahmedadel.kotlinapp.annotations

import android.support.annotation.IdRes

@Retention
@Target(AnnotationTarget.CLASS)
annotation class LayoutId(@IdRes val layoutId : Int)