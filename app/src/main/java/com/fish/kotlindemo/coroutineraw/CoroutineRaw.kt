package com.fish.kotlindemo.coroutineraw

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.intrinsics.startCoroutineCancellable
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.*

fun launchEmpty(block: () -> Unit) {
    block()//与block.invoke()等价
}

fun launchEmpty1(block:  () -> Unit) {
}

fun <T> launchFish(block: suspend () -> T) {
    //创建协程，返回值为SafeContinuation(实现了Continuation 接口)
    //入参为Continuation 类型，参数名为completion，顾名思义就是
    //协程结束后(正常返回&抛出异常）将会调用它。
    var coroutine = block.createCoroutine(object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        //协程结束后调用该函数
        override fun resumeWith(result: Result<T>) {
            println("result:$result")
        }
    })
    //开启协程
    coroutine.resume(Unit)
}

fun main(array: Array<String>) {
    launchFish {
        println("I am coroutine")
    }
}