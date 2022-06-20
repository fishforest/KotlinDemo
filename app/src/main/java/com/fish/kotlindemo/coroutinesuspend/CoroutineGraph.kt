package com.fish.kotlindemo.coroutinesuspend

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineGraph {
}

fun startLaunch() {
    GlobalScope.launch {
        println("parent coroutine running")

        getStuInfoV1()

        println("after suspend")
    }
}

suspend fun getStuInfoV1() {
    withContext(Dispatchers.IO) {
        println("son coroutine running")
    }
}