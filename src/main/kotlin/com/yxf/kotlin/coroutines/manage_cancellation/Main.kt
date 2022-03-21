package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager

fun main() {
    val logger = LogManager.getLogger()

    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0;

            while(i < 5 /* 1. && isActive */) {
                //2. ensureActive()
                //3. yield()
                if(System.currentTimeMillis() - nextPrintTime > 500) {
                    logger.info("Hello $i, isActive: " + isActive)
                    nextPrintTime += 500
                    i++
                }
            }
        }

        delay(1000L)
        logger.info("Cancelling...")
        job.cancel()
        logger.info("Cancelled")
        job.join()
        logger.info("Joined")
    }
}