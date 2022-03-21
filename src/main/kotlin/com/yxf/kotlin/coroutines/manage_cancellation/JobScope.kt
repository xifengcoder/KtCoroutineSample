package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager

fun main() {
    val logger = LogManager.getLogger()

    runBlocking {
        val scope = CoroutineScope(SupervisorJob())
        val job = scope.launch {
            launch {
                logger.info("Child1 launching...")
                delay(200L)
                logger.info("Child1 failed. Wawowo(╯□╰)o")
                throw ArithmeticException()
            }

            launch {
                logger.info("Child2 launching...")
                delay(1000L)
                logger.info("Child2 finish.")
            }
        }

        logger.info("Waiting for join()")
        job.join()
        logger.info("Parent completed!")
    }
}