package com.yxf.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) {
    runBlocking {
//        val startTime = System.currentTimeMillis()
//        val job = launch(Dispatchers.Default) {
//            var nextPrintTime = startTime
//            var i = 0
//            while (isActive) { // computation loop, just wastes CPU
//                // print a message twice a second
//                if (System.currentTimeMillis() >= nextPrintTime) {
//                    println("job: I'm sleeping ${i++} ...")
//                    nextPrintTime += 500L
//                }
//            }
//        }
//        delay(1300L) // delay a bit
//        println("main: I'm tired of waiting!")
//        job.cancelAndJoin() // cancels the job and waits for its completion
//        println("main: Now I can quit.")
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }
}