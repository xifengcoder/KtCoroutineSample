package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.apache.logging.log4j.LogManager

fun main() {
    val logger = LogManager.getLogger()

    runBlocking {
        val result = withTimeoutOrNull(1300L) {
            repeat(2) {
                logger.info("$it. Crunching numbers [Beep.Boop.Beep]...")
                delay(500L)
            }
            "Done"
        }
        logger.info("Result: $result")
    }
}