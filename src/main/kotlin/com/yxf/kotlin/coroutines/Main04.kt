package com.yxf.kotlin.coroutines

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager

fun main() {
    val logger = LogManager.getLogger()

    runBlocking {
        val userDeferred = async {
            logger.info("getUserDeferred")
            delay(Long.MAX_VALUE)
        }
        
        val  timeoutJob = launch {
            delay(2000)
            userDeferred.cancel()
        }

        logger.info("awaiting...")
        try {
            val user = userDeferred.await()
            logger.info("Awaited, Get user: " + user)
        } catch (e: Exception) {
            logger.error(e)

        }
        logger.info("cancelling timeoutJob...")
        timeoutJob.cancel()
        logger.info("Done!")
    }
}