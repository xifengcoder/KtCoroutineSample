package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.LogManager

fun main() {
    val logger = LogManager.getLogger()

    runBlocking {
        val job = async(Dispatchers.Default) {
            repeat(5) {
                logger.info("Hello $it")
                delay(500)
            }
        }

        delay(1000L)
        logger.info("Cancelling...")
        job.cancel()
        logger.info("Cancelled")
        val result = job.await()
        logger.info("Awaited, result: $result")
    }
}