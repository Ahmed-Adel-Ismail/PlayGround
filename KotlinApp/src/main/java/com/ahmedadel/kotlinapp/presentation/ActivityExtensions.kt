package com.ahmedadel.kotlinapp.presentation

import android.app.Activity
import com.ahmedadel.kotlinapp.annotations.LayoutId
import com.ahmedadel.kotlinapp.annotations.findClassAnnotation


fun Activity.onCreateImplementation(implementation: (() -> Unit)?) {
    findClassAnnotation<LayoutId> { setContentView(it.layoutId) }
    implementation?.invoke()
}
