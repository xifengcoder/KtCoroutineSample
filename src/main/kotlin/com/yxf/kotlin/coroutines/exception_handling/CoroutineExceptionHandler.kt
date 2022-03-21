package com.yxf.kotlin.coroutines.exception_handling

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        val scope = CoroutineScope(Job())
        val job = scope.launch {
            launch(handler) {
                throw Exception("Failed coroutine")
            }
        }

        job.join()
        println("Done!")
    }
}