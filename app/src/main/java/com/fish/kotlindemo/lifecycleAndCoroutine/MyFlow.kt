package com.fish.kotlindemo.lifecycleAndCoroutine

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.thread
import kotlin.coroutines.resume

class MyFlow {
    val flow = flow {
        var count = 0
        while (true) {
            kotlinx.coroutines.delay(1000)
            println("emit hello world $count")
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
        if (!isActive) {
            println("流被关闭了")
        }
        println("after try send")
        awaitClose {
            println("end")
        }
    }
}