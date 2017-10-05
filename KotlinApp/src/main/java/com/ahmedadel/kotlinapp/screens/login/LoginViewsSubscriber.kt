package com.ahmedadel.kotlinapp.screens.login

import android.view.View
import android.widget.*
import com.ahmedadel.kotlinapp.R
import com.ahmedadel.kotlinapp.mocking.INVALID_USER_ID
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


fun LoginActivity.subscribeToViewModel(viewModel: LoginViewModel, disposables: CompositeDisposable) {
    disposables.add(progressView(viewModel))
    disposables.add(userNameView(viewModel))
    disposables.add(passwordView(viewModel))
    disposables.add(loginButton(viewModel, disposables))
    disposables.add(errorTextView(viewModel))
    disposables.add(loginResponse(viewModel))
}


private fun LoginActivity.progressView(viewModel: LoginViewModel): Disposable {
    val progressView = findViewById<ProgressBar>(R.id.activity_login_progress)
    return viewModel.progress.asObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .map { if (it) View.VISIBLE else View.GONE }
            .subscribe(progressView::setVisibility)
}


private fun LoginActivity.errorTextView(viewModel: LoginViewModel): Disposable {
    val errorTextView = findViewById<TextView>(R.id.activity_login_error_textView)
    return viewModel.errorMessage.asObservable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(errorTextView::setText)
}

private fun LoginActivity.loginButton(viewModel: LoginViewModel, disposables: CompositeDisposable): Disposable {
    val loginButton = findViewById<Button>(R.id.activity_login_login_button)
    return RxView.clicks(loginButton)
            .filter { !viewModel.progress.isTrue }
            .throttleFirst(2, TimeUnit.SECONDS)
            .subscribe { viewModel.login()(disposables) }
}

private fun LoginActivity.passwordView(viewModel: LoginViewModel): Disposable {
    val passwordEditText = findViewById<EditText>(R.id.activity_login_password_edit_text)
    return RxTextView.textChanges(passwordEditText)
            .map(CharSequence::toString)
            .subscribe(viewModel.password)
}

private fun LoginActivity.userNameView(viewModel: LoginViewModel): Disposable {
    val userNameEditText = findViewById<EditText>(R.id.activity_login_username_edit_text)
    return RxTextView.textChanges(userNameEditText)
            .map(CharSequence::toString)
            .subscribe(viewModel.userName)
}


private fun LoginActivity.loginResponse(viewModel: LoginViewModel): Disposable {
    return viewModel.userIdResponse.asObservable()
            .filter { it != INVALID_USER_ID }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ Toast.makeText(this, "user id : $it", Toast.LENGTH_SHORT).show() },
                    Throwable::printStackTrace)
}

