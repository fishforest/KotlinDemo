package com.fish.kotlindemo.lifecycleAndCoroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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
}