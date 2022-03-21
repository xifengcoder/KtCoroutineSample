package com.yxf.kotlin.coroutines.exception_handling

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun main() {
    val logger: Logger = LogManager.getLogger()

    runBlocking {
        val asyncJob = GlobalScope.launch {
            logger.info("1. Exception created via launch coroutine")
            //Will be printed to the console by
            //Thread.defaultUncaughtExceptionHandler
            throw IndexOutOfBoundsException()
        }

        asyncJob.join()
        logger.info("2. Joined failed job")

        val deferred = GlobalScope.async {
            logger.info("Exception created via async coroutine")
            //Nothing is printed, relying on user to call await
            throw ArithmeticException()
        }

        try {
            deferred.await()
            logger.info("4. Unreachable, this statement is never executed")
        } catch (e: Exception) {
            logger.error("5. Caught ${e.javaClass.simpleName}")
        }
    }
}