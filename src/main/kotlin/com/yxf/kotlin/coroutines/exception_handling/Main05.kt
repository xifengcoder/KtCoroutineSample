package com.yxf.kotlin.coroutines.exception_handling

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun main() {
    val logger: Logger = LogManager.getLogger()

    runBlocking {
        coroutineScope {
            launch {
                // Child 1
                logger.info("Launching child1")
                delay(500L)
                throw ArithmeticException()
            }
            launch {
                // Child 2
                logger.info("Launching child2")
                delay(5000L)
                logger.info("Child2 completed")
            }
        }
        logger.info("Jobs Done...")
    }
}