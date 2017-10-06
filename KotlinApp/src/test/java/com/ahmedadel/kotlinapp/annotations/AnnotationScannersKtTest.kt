package com.ahmedadel.kotlinapp.annotations

import org.junit.Assert.assertTrue
import org.junit.Test

@TestAnnotation(10)
class AnnotationScannersKtTest {

    @Test
    fun findClassAnnotationThenReturnTestAnnotation() {
        var result = 0
        findClassAnnotation<TestAnnotation> { result = it.value }
        assertTrue(result == 10)
    }

}


@Target(AnnotationTarget.CLASS)
annotation class TestAnnotation(val value : Int)