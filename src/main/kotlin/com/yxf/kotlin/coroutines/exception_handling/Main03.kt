package com.yxf.kotlin.coroutines.exception_handling

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun main() {
    val logger: Logger = LogManager.getLogger()

    runBlocking {
        //1
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            logger.error("Caught $exception")
        }

        //2
        val job = GlobalScope.launch(exceptionHandler) {
            throw AssertionError("My Custom Assertion Error!")
        }

        //3
        val deferred = GlobalScope.async(exceptionHandler) {
            //Nothing will be printed
            // relying on user to call deferred.await()
            throw ArithmeticException()
        }

        //4
        //This suspends current coroutine until all given jobs are completed
        joinAll(job, deferred)
        logger.info("All jobs completed")
    }
}