package com.ahmedadel.kotlinapp.domain

import com.ahmedadel.kotlinapp.entities.STATUS_CODE_UNKNOWN
import com.ahmedadel.kotlinapp.entities.ServerError
import com.ahmedadel.kotlinapp.entities.ServerResponse
import io.reactivex.Maybe

fun <R> ServerResponse<R>.parse(): Maybe<R> {

    return if (success) {
        if (response != null) Maybe.just(response) else Maybe.empty()
    } else {
        if (error != null) Maybe.error(error)
        else Maybe.error(ServerError(STATUS_CODE_UNKNOWN, "request failed"))
    }
}