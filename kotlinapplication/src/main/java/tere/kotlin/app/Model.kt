package tere.kotlin.app

import java.util.*


fun forecastItems(): List<String> {
    return listOf("Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHER-STATION - 23/18",
            "Sun 6/29 - Sunny - 20/7")
}

infix fun String.charAt(index: Int): Char {
    return this.toCharArray()[index]
}


class Person(val name: String)
data class Forecast(val data: Date, val temperature: Float, val details: String)