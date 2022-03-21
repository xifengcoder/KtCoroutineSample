package com.yxf.kotlin.coroutines.suspending_functions

import kotlinx.coroutines.delay
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

val logger: Logger = LogManager.getLogger()

fun main() {
    getUserIdFromNetworkCallback("123") { user ->
        logger.info("---success to getUser 123: $user")
    }
    logger.info("Main end")
}

suspend fun getUserSuspend(userId: String): User {
    delay(100)
    return User(userId, "Filip")
}

suspend fun readFileSuspend(path: String): File =
    suspendCoroutine {
        readFile(path) { file ->
            it.resume(file)
        }
    }

fun readFile(path: String, onReady: (File) -> Unit) {
    Thread.sleep(1000)
// some heavy operation
    onReady(File(path))
}

fun getUserIdFromNetworkCallback(
    userId: String,
    onUserReady: (User) -> Unit
) {
    thread {
        Thread.sleep(1200)
        val user = User(userId, "Filip")
        onUserReady(user)
    }

    logger.info("---end")
}