package com.ahmedadel.kotlinapp.screens.login

import com.ahmedadel.kotlinapp.mocking.INVALID_USER_ID
import com.ahmedadel.kotlinapp.mocking.PASSWORDS
import com.ahmedadel.kotlinapp.mocking.USERS
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.CountDownLatch


/**
 * Created by Ahmed Adel Ismail on 10/4/2017.
 */
class LoginViewModelTest {

    @Test
    fun loginWithCorrectLoginRequestThenReturnValidUserId() {
        val countdownLatch = CountDownLatch(1)
        val viewModel = LoginViewModel()
        viewModel.userName.set(USERS.keys.first())
        viewModel.password.set(PASSWORDS[USERS[viewModel.userName.get()]])
        viewModel.login(Schedulers.computation()) {
            assertTrue(it != null && it != INVALID_USER_ID)
            countdownLatch.countDown()
        }
        countdownLatch.await()
    }

    @Test
    fun loginWithInvalidLoginRequestThenReturnErrorMessage() {
        val countdownLatch = CountDownLatch(1)
        val viewModel = LoginViewModel()
        viewModel.userName.set("")
        viewModel.password.set("")
        viewModel.login(Schedulers.computation()) {
            assertTrue(it == INVALID_USER_ID && !viewModel.errorMessage.get().isNullOrBlank())
            countdownLatch.countDown()
        }
        countdownLatch.await()
    }

    @Test
    fun loginWithValidCredentialsStartsThenShowProgress() {
        val viewModel = LoginViewModel()
        viewModel.userName.set(USERS.keys.first())
        viewModel.password.set(PASSWORDS[USERS[viewModel.userName.get()]])
        viewModel.login(Schedulers.computation()) {
            assertTrue(viewModel.progress.isTrue)
        }
    }

    @Test
    fun loginWithValidCredentialsFinishesThenHideProgress() {
        val countdownLatch = CountDownLatch(1)
        val viewModel = LoginViewModel()
        viewModel.userName.set(USERS.keys.first())
        viewModel.password.set(PASSWORDS[USERS[viewModel.userName.get()]])
        viewModel.login(Schedulers.computation()) {
            assertFalse(viewModel.progress.isTrue)
            countdownLatch.countDown()
        }
        countdownLatch.await()
    }
}


// short hand in if/else conditions :

fun addPositiveNumbers(valueOne: Int, valueTwo: Int): Int =
        if (valueOne >= 0 && valueTwo >= 0) valueOne + valueTwo
        else -1

fun addPositiveNumbersThenMultiply(valueOne: Int, valueTwo: Int): (Int) -> Int {
    return if (valueOne >= 0 && valueTwo >= 0) {
        { it * (valueOne + valueTwo) }
    } else {
        { -1 }
    }
}


// callbacks for testing

fun doStuffInBackground() = Thread(Runnable { Thread.sleep(3000) }).start()


fun doStuffInBackgroundTest(testCallback: (() -> Unit)? = null) {
    Thread(Runnable {
        Thread.sleep(3000)
        testCallback?.invoke()
    }).start()
}

