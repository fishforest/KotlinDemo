package com.fish.kotlindemo.coroutine

import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

class CoroutineJJ {
    fun test1(block:(Int)->String) {
        test{
                c:Int ->
            ""
        }

    }

    fun <T>test3(block: (Continuation<T>) -> Any?) {

    }

    fun test(block: (Int) -> String) {
        block.invoke(3)
        block(3)

        suspend {

        }
    }

}