package com.yxf.kotlin.coroutines.manage_cancellation

import kotlinx.coroutines.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.IOException

fun main() {
    val logger: Logger = LogManager.getLogger()

    runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println(
                "Caught $exception with suppressed " +
                        exception.suppressed?.contentToString()
            )
        }

        val parentJob = GlobalScope.launch(handler) {
            val childJob = launch {
                launch {
                    launch {
                        throw IOException()
                    }
                }
            }

            try {
                childJob.join()
            } catch (e: CancellationException) {
                println(
                    "Rethrowing CancellationException" +
                            " with original cause"
                )
                throw IllegalStateException()
            } catch (e: IOException) {
                println("Exception $e")
            }
        }

        parentJob.join()
        println("ParentJob completed")
    }
}