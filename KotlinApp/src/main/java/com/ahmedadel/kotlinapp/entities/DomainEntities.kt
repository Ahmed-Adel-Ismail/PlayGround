package com.ahmedadel.kotlinapp.entities


data class Profile(val userId: Long, val name: String, val age: Int, val genre: Genre)
enum class Genre {MALE, FEMALE }



