package com.ahmedadel.kotlinapp.entities


const val STATUS_CODE_UNKNOWN = -2
const val STATUS_CODE_UNAUTHORIZED = 401

data class ServerResponse<out T>(val success: Boolean, val response: T? = null, val error: ServerError? = null)
data class ServerError(private val statusCode: Int, private val serverMessage: String)
    : RuntimeException("[$statusCode] : $serverMessage")

data class LoginRequest(val userName: String, val password: String)
data class ProfileRequest(val userId: Long)
