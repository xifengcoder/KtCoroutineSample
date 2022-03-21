package com.yxf.kotlin.coroutines.suspending_functions

import org.apache.logging.log4j.LogManager


data class User(val id: String, val name: String) {
    companion object {
        val log = LogManager.getLogger(this::class.java.simpleName)
    }
}