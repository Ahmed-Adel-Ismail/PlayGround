package com.ahmedadel.kotlinapp

import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit



/**
 * Created by Ahmed Adel Ismail on 10/4/2017.
 */
fun currentThreadExecutorService(): ExecutorService {
    val callerRunsPolicy = ThreadPoolExecutor.CallerRunsPolicy()
    return object : ThreadPoolExecutor(0, 1, 0L, TimeUnit.SECONDS, SynchronousQueue<Runnable>(), callerRunsPolicy) {
        override fun execute(command: Runnable) {
            callerRunsPolicy.rejectedExecution(command, this)
        }
    }
}