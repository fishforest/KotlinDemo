package com.fish.kotlindemo.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.*


fun <T> launchMee(block: suspend () -> T) {
    var coroutine = block.createCoroutine(object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("result:$result")
        }
    })
    coroutine.resume(Unit);
}

suspend fun loadDataFuck1(): String {
    var tt = suspendCoroutine<String> { continuation ->
        thread {
            Thread.sleep(1000)
            continuation.resume("ok3333")
        }
//        Thread.sleep(1000)
//        continuation.resume("ok3333")
        println("after thread2:${Thread.currentThread()}")
    }
    println("switch tt")
    return tt
}

suspend fun loadMe() : String {
    return withContext(Dispatchers.IO) {
        println("hello world")
        "fish"
    }
}

fun main(args: Array<String>) {
    println("1")
    launchMee {
        println("before fuck")
        var result = loadDataFuck1()
        println("after fuck: $result")
    }
//
//    launchMee {
//        var jj = loadMe()
//        println("jj $jj")
//    }
    println("2")

    Thread.sleep(200000)
}