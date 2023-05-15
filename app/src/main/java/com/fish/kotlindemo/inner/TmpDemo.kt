package com.fish.kotlindemo.inner

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TmpDemo {
    fun test0() {

    }
    suspend fun test1() {

    }

    suspend fun test2() {
        thread {
            Thread.sleep(2000)
            println("sleep end")
        }
        println("test end")
    }

    suspend fun test3() {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            println("sleep end")
        }
        println("test end")
    }

    suspend fun test4() {
        val ret = suspendCoroutine<String> {
            thread {
                Thread.sleep(2000)
                println("sleep end")
            }
//            delay(2000)
        }
        println("test end $ret")
    }

    suspend fun test5() {
        val ret = suspendCoroutine<String> {
            thread {
                Thread.sleep(2000)
                println("sleep end")
                it.resume("thread finish")
            }
        }
        println("test end $ret")
    }

    suspend fun test6() {
        println("")
        delay(100)
        println("test end ${Thread.currentThread()}")
        delay(200)
        println("test end2 ${Thread.currentThread()}")

    }
}

fun main() {
    runBlocking {
        TmpDemo().test6()
    }
    Thread.sleep(200000)
}