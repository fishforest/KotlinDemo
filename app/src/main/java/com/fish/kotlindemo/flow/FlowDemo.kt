package com.fish.kotlindemo.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

class FlowDemo() {
    fun test() {
        GlobalScope.launch(Dispatchers.Default) {
            testFlow6()
        }
    }

    fun testList() {
        //构造集合
        fun list(): List<Int> = listOf(1, 2, 3)
        list().forEach {
            //获取多个值
            println("value = $it")
        }
    }

    fun testSequence() {
        fun sequence(): Sequence<Int> = sequence {
            for (i in 1..3) {
                Thread.sleep(1000)
                yield(i)
            }
        }

        sequence().forEach {
            println("value = $it")
        }
    }

    suspend fun testListDelay() {
        suspend fun list(): List<Int> {
            delay(1000)
            return listOf(1, 2, 3)
        }

        list().forEach {
            println("value = $it")
        }
    }

    suspend fun testFlow1() {
        //生产者
        var flow = flow {
            println("emit data")
            emit(5)
        }

        //消费者
        flow.collect {
            println("value=$it")
        }
    }

    suspend fun testFlow2() {
        //生产者
        flow {
            println("emit data")
            emit(5)
        }.collect {
            println("value=$it")
        }
    }

    suspend fun testFlow3() {
        //生产者
        var flow = flow {
            for (i in 1..1000) {
                emit(i)
            }
        }.filter { it > 500 && it % 2 == 0 }
            .map { it - 500 }
            .catch {
                //异常处理
            }

        //消费者
        flow.collect {
            println("value=$it")
        }
    }

    suspend fun testFlow4() {
        //生产者
        var flow = flow {
            for (i in 1..1000) {
                delay(1000)
                emit(i)
            }
        }.flowOn(Dispatchers.IO)//切换到io线程执行

        //消费者
        flow.collect {
            delay(1000)
            println("value=$it")
        }
    }

    suspend fun testFlow5() {
        //生产者
        var flow = flow {
            println("111")
            for (i in 1..1000) {
                emit(i)
            }
        }.filter {
            println("222")
            it > 500 && it % 2 == 0
        }.map {
            println("333")
            it - 500
        }.catch {
            println("444")
            //异常处理
        }

        flow.collect {
            println("value：$it")
        }
    }

    suspend fun testFlow6() {
        //生产者
        var flow = flow {
            println("111")
            for (i in 1..2) {
                emit(i)
            }
        }.filter {
            println("222")
            true
        }

        flow.collect {
            println("value：$it")
        }
    }
}

fun main(args: Array<String>) {
    var demo = FlowDemo()
    demo.test()
    Thread.sleep(1000000)
}
