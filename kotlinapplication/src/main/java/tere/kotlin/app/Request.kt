package tere.kotlin.app

import android.util.Log
import java.net.URL

/**
 * Created by Ahmed Adel Ismail on 5/27/2017.
 */
class Request(val url : String) {

    fun run(){
        val forecastJson = URL(url).readText()
        Log.d(javaClass.simpleName,forecastJson)
    }
}