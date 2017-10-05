package com.ahmedadel.kotlinapp.mocking

import com.ahmedadel.kotlinapp.entities.*
import io.reactivex.Observable
import io.reactivex.Single

fun requestLogin(loginRequest: LoginRequest): ServerResponse<Long> {

    Thread.sleep(1000)
    return if (isValidLoginRequest(loginRequest)) {
        ServerResponse(true, response = USERS[loginRequest.userName])
    } else {
        ServerResponse(false, error = ServerError(STATUS_CODE_UNAUTHORIZED, "invalid credentials"))
    }
}

private fun isValidLoginRequest(loginRequest: LoginRequest):
        Boolean = Single.just(loginRequest)
        .filter(::isValidUserName)
        .map(::isValidPassword)
        .defaultIfEmpty(false)
        .blockingGet()

private fun isValidUserName(loginRequest: LoginRequest):
        Boolean = USERS.containsKey(loginRequest.userName)

private fun isValidPassword(loginRequest: LoginRequest):
        Boolean = Observable.just(USERS[loginRequest.userName])
        .map { PASSWORDS[it] }
        .map(loginRequest.password::equals)
        .blockingFirst()


fun requestProfile(profileRequest: ProfileRequest): ServerResponse<Profile> {
    Thread.sleep(1000)
    return ServerResponse(true, response = PROFILES[profileRequest.userId])
}