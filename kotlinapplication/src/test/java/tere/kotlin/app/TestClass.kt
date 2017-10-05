package tere.kotlin.app

import org.junit.Test

/**
 * Created by Ahmed Adel Ismail on 5/26/2017.
 */
class TestClass {

    val sumReference = this::sum

    fun sum(x: Int, y: Int): Int = x + y


    @Test
    fun run(){
        assert(asString(this::sum) == "3")
    }


    fun asString(sum: (Int,Int) -> Int) : String{
        return sum(1,2).toString()
    }

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }


}