package com.yxf.kotlin.coroutines.exception_handling

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun main() {
    val logger: Logger = LogManager.getLogger()

    runBlocking {
        val handler = CoroutineExceptionHandler { context, exception ->
            //捕捉到的是Job2的IllegalStateException异常，Job1的ArithmeticException异常被屏蔽掉了，
            //通过Throwable的getSuppressed()方法可以查看。
            logger.info(
                "Caught $exception with suppressed " +
                        exception.suppressed?.contentToString()
            )
        }

        //Parent Job
        val parentJob = GlobalScope.launch(handler) {
            //Child Job 1
            launch {
                try {
                    delay(Long.MAX_VALUE)
                } catch (e: Exception) {
                    //JobCancellationException.
                    logger.error("${e.javaClass.simpleName} in Child Job 1")
                } finally {
                    throw ArithmeticException()
                }
            }

            launch {
                delay(100)
                throw IllegalStateException()
            }

            delay(Long.MAX_VALUE)
        }

        //Wait until parentJob completes
        parentJob.join()
        logger.info("All Jobs completed")
    }
}