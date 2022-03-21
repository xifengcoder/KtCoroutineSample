package com.yxf.kotlin.coroutines

import kotlinx.coroutines.*
fun main(args: Array<String>) = runBlocking {
    val job = GlobalScope.launch {
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException()
    }
    job.join()

    println("Joined failed job")

    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException()
    }


    deferred.await()
    println("Unreached")
}


fun printlnWithTime(message: String) {
    println("${System.currentTimeMillis()} : " + message)
}

suspend fun printInDelayed(message: String) {
    delay(1000)
    printlnWithTime(message)
}
