package tere.kotlin.app

import org.junit.Test

/**
 * Created by Ahmed Adel Ismail on 5/24/2017.
 */
class ModelKtTest{

    val c = MainActivity::class


    val message by lazy{
        "first character"
    }.apply {
        value printAfter "initialized message 1::"
    }.also{
        it.value printAfter "initialized message 2::"
    }.apply {
        value printAfter "initialized message 3::"
    }

    @Test
    fun main(){
        "Hello Kotlin" charAt 0 printAfter message
    }





}