package com.yxf.kotlin.coroutines.exception_handling

import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.IOException
import kotlin.coroutines.suspendCoroutine

val logger: Logger = LogManager.getLogger()

fun main() {
    runBlocking {
        try {
            val data = getDataAsync();
            logger.info("Data received:$data")
        } catch (e: Exception) {
            logger.info("Caught ${e.javaClass.simpleName}")
        }
    }
}

suspend fun getDataAsync(): String {
    return suspendCoroutine { continuation ->
        getData(object : AsyncCallback {
            override fun onError(e: Exception) {
                continuation.resumeWith(Result.failure(e))
            }

            override fun onSuccess(result: String) {
                continuation.resumeWith(Result.success(result))
            }
        });
    }
}

fun getData(callback: AsyncCallback) {
    val triggerError = true
    try {
        Thread.sleep(3000)
        if (triggerError) {
            throw IOException()
        } else {
            callback.onSuccess("[Beep.Boop.Beep]")
        }
    } catch (e: Exception) {
        callback.onError(e)
    }
}

interface AsyncCallback {
    fun onSuccess(result: String)
    fun onError(e: Exception)
}