package com.ahmedadel.kotlinapp.annotations


@Suppress("UNCHECKED_CAST")
fun <T : Any, A : Annotation> T.findAnnotation(annotationClass: Class<A>, onComplete: (A) -> Unit) {
    val annotation = this::class
            .annotations
            .find { it.annotationClass.qualifiedName == annotationClass.name } as? A

    annotation?.let(onComplete)
}