package com.ahmedadel.kotlinapp.annotations


inline fun <reified A : Annotation> Any.findClassAnnotation(onComplete: (A) -> Unit) {
    val annotation = this::class.annotations.find { it is A } as? A
    annotation?.let(onComplete)
}
