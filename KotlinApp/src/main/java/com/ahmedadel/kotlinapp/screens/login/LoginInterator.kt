package com.ahmedadel.kotlinapp.screens.login

import com.ahmedadel.kotlinapp.domain.parse
import com.ahmedadel.kotlinapp.entities.LoginRequest
import com.ahmedadel.kotlinapp.entities.ServerResponse
import com.ahmedadel.kotlinapp.mocking.INVALID_USER_ID
import com.ahmedadel.kotlinapp.mocking.requestLogin
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


private const val EMPTY_TEXT = ""

fun LoginViewModel.login(
        observeOnScheduler: Scheduler = AndroidSchedulers.mainThread(),
        callback: ((Long?) -> Unit)? = null):
        (CompositeDisposable) -> Unit {

    val userName: String? = this.userName.get()
    val password: String? = this.password.get()


    val disposable: Disposable? = if (isInvalidData(userName, password)) {
        processValidationError(callback)
    } else {
        processLoginRequest(observeOnScheduler,
                LoginRequest(userName!!, password!!), callback)
    }
    return { if (disposable != null) it.add(disposable) }
}

private fun LoginViewModel.processValidationError(
        callback: ((Long?) -> Unit)?):
        Nothing? {

    handleValidationError()
    callback?.invoke(INVALID_USER_ID)
    return null
}

private fun isInvalidData(userName: String?, password: String?)
        = userName.isNullOrEmpty() || password.isNullOrEmpty()

private fun LoginViewModel.handleValidationError() {
    progress.set(false)
    errorMessage.set("make sure user name and password are valid")
    userIdResponse.set(INVALID_USER_ID)
}

private fun LoginViewModel.processLoginRequest(
        observeOnScheduler: Scheduler,
        loginRequest: LoginRequest,
        callback: ((Long?) -> Unit)?):
        Disposable {

    progress.set(true)
    errorMessage.set(EMPTY_TEXT)
    userIdResponse.set(INVALID_USER_ID)

    return Observable.just(loginRequest)
            .map(::requestLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(observeOnScheduler)
            .flatMapMaybe(ServerResponse<Long>::parse)
            .subscribe(handleLoginResponse(callback), this::handleLoginError)
}

private fun LoginViewModel.handleLoginResponse(
        callback: ((Long?) -> Unit)?):
        (Long) -> Unit {

    return {
        this.progress.set(false)
        this.errorMessage.set(EMPTY_TEXT)
        this.userIdResponse.set(it)
        callback?.invoke(it)
    }
}

private fun LoginViewModel.handleLoginError(error: Throwable) {
    this.progress.set(false)
    this.errorMessage.set(error.message)
    this.userIdResponse.set(INVALID_USER_ID)
}
