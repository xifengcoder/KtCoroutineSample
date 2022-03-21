package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager

fun main() {
    val logger = LogManager.getLogger()

    runBlocking {
        val scope = CoroutineScope(Job())
        val supervisorJob = SupervisorJob()

        val job1 = scope.launch(supervisorJob) {
            logger.info("Launching child1")
            delay(500L)
            throw ArithmeticException()
        }

        val job2 = scope.launch(supervisorJob) {
            logger.info("Launching child2")
            delay(5000L)
            logger.info("Child2 completed")
        }

        logger.info("Waiting for jobs finish...")
        job1.join()
        job2.join()
        logger.info("Jobs Done...")
    }
}
