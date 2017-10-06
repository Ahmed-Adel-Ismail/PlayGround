package com.ahmedadel.kotlinapp.screens.login

import com.ahmedadel.kotlinapp.mocking.INVALID_USER_ID
import com.ahmedadel.kotlinapp.mocking.PASSWORDS
import com.ahmedadel.kotlinapp.mocking.USERS
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
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
        viewModel.login(Schedulers.computation())
        assertTrue(viewModel.progress.isTrue)
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

    @Test
    fun main() {
        val startTime = System.currentTimeMillis()
        runBlocking { printEvenNumbersAneThereTotal(1..1_000_000) }
        val endTime = System.currentTimeMillis()
        System.err.println("Millis to print and sum : " + (endTime - startTime))
    }

    suspend private fun printEvenNumbersAneThereTotal(range: IntRange) {
        (range).filter { it % 2 == 0 }
                .map { async { it * 2 } }
                .sumBy { it.await() }
                .let { System.out.println("Result : $it") }
    }

    private fun jobOne() {
        Thread.sleep(1000)
        System.out.println("jobOne : " + Date())
    }

    private fun jobTwo() {
        Thread.sleep(2000)
        System.out.println("jobTwo : " + Date())
    }


}




