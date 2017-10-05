package com.ahmedadel.kotlinapp.screens.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ahmedadel.kotlinapp.R
import com.ahmedadel.kotlinapp.annotations.LayoutId
import com.ahmedadel.kotlinapp.domain.Model
import com.ahmedadel.kotlinapp.presentation.onCreateImplementation
import io.reactivex.disposables.CompositeDisposable

@LayoutId(R.layout.activity_login)
class LoginActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()
    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateImplementation {
            viewModel = Model.of(this, LoginViewModel::class.java)
            subscribeToViewModel(viewModel!!, disposables)
        }
    }

    override fun onDestroy() {
        disposables.clear()
        viewModel?.clear(this)
        super.onDestroy()
    }
}
