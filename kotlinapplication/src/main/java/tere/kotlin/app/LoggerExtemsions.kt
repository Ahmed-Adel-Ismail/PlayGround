package tere.kotlin.app

/**
 * Created by Ahmed Adel Ismail on 5/24/2017.
 */

infix fun Any.printAfter(tag: String) {
    System.out.println(tag + " " + this)
}