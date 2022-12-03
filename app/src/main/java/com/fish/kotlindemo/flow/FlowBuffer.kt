package com.fish.kotlindemo.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class FlowBuffer {
    fun testBuffer() {
        runBlocking {
            testBuffer4()
        }
    }
    suspend fun testBuffer1() {
        var flow = flow {
            //生产者
            (1..3).forEach {
                println("emit $it")
                emit(it)
            }
        }

        flow.collect {
            //消费者
            println("collect:$it")
        }
    }

    suspend fun testBuffer2() {
        var flow = flow {
            (1..3).forEach {
                println("emit $it")
                emit(it)
            }
        }

        var time = measureTimeMillis {
            flow.collect {
                println("collect:$it")
            }
        }
        println("use time:${time} ms")
    }

    suspend fun testBuffer3() {
        var flow = flow {
            (1..3).forEach {
                delay(1000)
                println("emit $it")
                emit(it)
            }
        }

        var time = measureTimeMillis {
            flow.collect {
                delay(2000)
                println("collect:$it")
            }
        }
        println("use time:${time} ms")
    }

    suspend fun testBuffer4() {
        var flow = flow {
            (1..3).forEach {
                delay(1000)
                println("emit $it in thread:${Thread.currentThread()}")
                emit(it)
            }
        }.flowOn(Dispatchers.IO)

        var time = measureTimeMillis {
            flow.collect {
                delay(2000)
                println("collect:$it in thread:${Thread.currentThread()}")
            }
        }
        println("use time:${time} ms")
    }

    suspend fun testBuffer5() {
        var flow = flow {
            (1..3).forEach {
                delay(1000)
                println("emit $it in thread:${Thread.currentThread()} ${System.currentTimeMillis()}")
                emit(it)
            }
        }.buffer(5)

        var time = measureTimeMillis {
            flow.collect {
                delay(2000)
                println("collect:$it in thread:${Thread.currentThread()} ${System.currentTimeMillis()}")
            }
        }
        println("use time:${time} ms")
    }

    suspend fun testBuffer6() {
        var flow = flow {
            (1..3).forEach {
                println("emit $it")
                emit(it)
            }
        }

        var time = measureTimeMillis {
            flow.collect {
                delay(2000)
                println("collect:$it")
            }
        }
        println("use time:${time} ms")
    }
}

fun main(args: Array<String>) {
    var flowBuffer = FlowBuffer()
    flowBuffer.testBuffer()
    Thread.sleep(1000000)
}