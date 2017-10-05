package com.ahmedadel.kotlinapp.presentation

import android.app.Activity
import com.ahmedadel.kotlinapp.annotations.LayoutId
import com.ahmedadel.kotlinapp.annotations.findAnnotation


fun Activity.onCreateImplementation(implementation: (() -> Unit)?) {
    findAnnotation(LayoutId::class.java) { setContentView(it.layoutId) }
    implementation?.invoke()
}
