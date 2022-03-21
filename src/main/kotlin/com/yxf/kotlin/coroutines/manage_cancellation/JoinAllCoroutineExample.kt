package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.Logger
import java.util.logging.LogManager

fun main() {
    val logger: Logger = org.apache.logging.log4j.LogManager.getLogger()
    runBlocking {
        val jobOne = launch {
            logger.info("Job 1: Crunching numbers [Beep.Boop.Beep]")
            delay(500L)
        }

        val jobTwo = launch {
            logger.info("Job 2: Crunching numbers [Beep.Boop.Beep]")
            delay(500L)
        }

        joinAll(jobOne, jobTwo)
        logger.info("main: Now I can quit")
    }
}