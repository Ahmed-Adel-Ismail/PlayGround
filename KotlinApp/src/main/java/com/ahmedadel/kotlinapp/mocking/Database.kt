package com.ahmedadel.kotlinapp.mocking

import com.ahmedadel.kotlinapp.entities.Genre
import com.ahmedadel.kotlinapp.entities.Profile


const val INVALID_USER_ID = -1L

val USERS: Map<String, Long> = mapOf(
        Pair("Ahmed", 1L),
        Pair("Mohamed", 2L),
        Pair("Jenny", 3L))


val PASSWORDS: Map<Long, String> = mapOf(
        Pair(1L, "blue"),
        Pair(2L, "red"),
        Pair(3L, "green"))

val PROFILES: Map<Long, Profile> = mapOf(
        Pair(1L, Profile(1L, "Ahmed A.", 30, Genre.MALE)),
        Pair(1L, Profile(1L, "M. Abdo", 24, Genre.MALE)),
        Pair(1L, Profile(1L, "J. Green", 34, Genre.FEMALE)))