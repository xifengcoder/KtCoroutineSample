package com.yxf.kotlin.coroutines.exception_handling

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.lang.IndexOutOfBoundsException


fun main() {
    val logger: Logger = LogManager.getLogger()

    runBlocking {
        val job = GlobalScope.launch {
            logger.info("1. Exception created via launch coroutine")
            delay(5000)

            //Will NOT be handled by
            //Thread.defaultUncaughtExceptionHandler
            //since it is being handled later by `invokeOnCompletion`
            throw IndexOutOfBoundsException()
        }

        job.invokeOnCompletion {exception ->
            logger.error("2. Caught $exception")
        }

        logger.info("Job joining...")
        job.join()
        logger.info("Job completed")
    }
}