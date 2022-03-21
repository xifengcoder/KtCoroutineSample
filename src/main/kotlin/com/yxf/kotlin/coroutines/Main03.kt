package com.yxf.kotlin.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {
        delay(200)
        println("Pong")
        delay(200)
    }

    GlobalScope.launch {
        delay(200)
        println("Ping 1")
        job1.join()
        println("Ping 2")
        delay(200)
    }

    Thread.sleep(1000)
}