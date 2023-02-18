package com.fish.kotlindemo.lifecycleAndCoroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.concurrent.thread

class MyFlow {
    val flow = flow {
        var count = 0
        while (true) {
//            kotlinx.coroutines.delay(10000)
            Thread.sleep(1000)
            val str = "jj"
            println(str)
            emit(count++)
        }
    }.flowOn(Dispatchers.IO)

    val flow2 = flow {
        kotlinx.coroutines.delay(5000)
        println("before emit")
        emit("jj")
    }

    fun getInfo(callback:()->Unit) {
        thread {
            println("send flow in thread")
            Thread.sleep(3000)
            callback.invoke()
        }
    }

    val flow3 = callbackFlow {
        getInfo {
            println("send flow in thread")
            trySend("send flow")
        }
        Thread.sleep(5000)
        if (isClosedForSend) {
            println("流被关闭了")
        }
        println("after try send")
        awaitClose {
            println("end")
        }
    }
}