package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val result = runCatching {
            codeThatCanThrowException()
        }

        if(result.isSuccess) {
            println("Result success: " + result)
        } else {
            println("Result failed: " + result)
        }
    }
}

suspend fun codeThatCanThrowException() {
    delay(1000L)
    throw ArithmeticException()
}
