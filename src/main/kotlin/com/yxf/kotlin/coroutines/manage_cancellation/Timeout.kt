package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.apache.logging.log4j.LogManager

fun main() {
    val logger = LogManager.getLogger()

    runBlocking {
        try {
            withTimeout(1500L) {
                repeat(1000) { i ->
                    println("$i. Crunching numbers [Beep.Boop.Beep]...")
                    delay(500L)
                }
            }
        } catch (e: TimeoutCancellationException) {
            println("Caught ${e.javaClass.simpleName}")
        }
    }
}