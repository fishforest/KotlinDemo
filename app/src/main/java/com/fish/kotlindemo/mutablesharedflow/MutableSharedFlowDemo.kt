package com.fish.kotlindemo.mutablesharedflow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MutableSharedFlowDemo {
    fun test1() {
        runBlocking {
            //构造热流
            val flow = MutableSharedFlow<String>()
            //发送数据(生产者)
            flow.emit("hello world")

            //开启协程
            GlobalScope.launch {
                //接收数据(消费者)
                flow.collect {
                    println("collect: $it")
                }
            }
        }
    }

    fun test2() {
        runBlocking {
            //构造热流
            val flow = MutableSharedFlow<String>()

            //开启协程
            GlobalScope.launch {
                //接收数据(消费者)
                flow.collect {
                    println("collect: $it")
                }
            }

            //发送数据(生产者)
            delay(200)//保证消费者已经注册上
            flow.emit("hello world")
        }
    }

    fun test3() {
        runBlocking {
            //构造热流
            val flow = MutableSharedFlow<String>(4)
            //发送数据(生产者)
            flow.emit("hello world1")
            flow.emit("hello world2")
            flow.emit("hello world3")
            flow.emit("hello world4")

            //开启协程
            GlobalScope.launch {
                //接收数据(消费者)
                flow.collect {
                    println("collect: $it")
                }
            }
        }
    }

    fun test4() {
        runBlocking {
            //构造热流
            val flow = MutableSharedFlow<String>()

            //接收数据(消费者)
            flow.collect {
                println("collect: $it")
            }
            println("start emit")//①
            flow.emit("hello world")
        }
    }

    fun test5() {
        runBlocking {
            //构造热流
            val flow = MutableSharedFlow<String>()

            //开启协程
            GlobalScope.launch {
                //接收数据(消费者)
                flow.collect {
                    delay(2000)
                    println("collect: $it")
                }
            }

            //发送数据(生产者)
            delay(200)//保证消费者先执行
            println("emit 1 ${System.currentTimeMillis()}")
            flow.emit("hello world1")
            println("emit 2 ${System.currentTimeMillis()}")
            flow.emit("hello world2")
            println("emit 3 ${System.currentTimeMillis()}")
            flow.emit("hello world3")
            println("emit 4 ${System.currentTimeMillis()}")
            flow.emit("hello world4")
        }
    }

    fun test6() {
        runBlocking {
            //构造热流
            val flow = MutableSharedFlow<String>(2, 2)

            //开启协程
            GlobalScope.launch {
                //接收数据(消费者)
                flow.collect {
                    delay(2000)
                    println("collect: $it")
                }
            }

            //发送数据(生产者)
            delay(200)//保证消费者先执行
            println("emit 1 ${System.currentTimeMillis()}")
            flow.emit("hello world1")
            println("emit 2 ${System.currentTimeMillis()}")
            flow.emit("hello world2")
            println("emit 3 ${System.currentTimeMillis()}")
            flow.emit("hello world3")
            println("emit 4 ${System.currentTimeMillis()}")
            flow.emit("hello world4")
        }
    }

    fun test7() {
        runBlocking {
            //构造热流
            val flow = MutableSharedFlow<String>(4, 3)
            //开启协程
            GlobalScope.launch {
                //接收数据(消费者1)
                flow.collect {
                    println("collect1: $it")
                }
            }
            GlobalScope.launch {
                //接收数据(消费者2)
                flow.collect {
                    //模拟消费慢
                    delay(10000)
                    println("collect2: $it")
                }
            }
            //发送数据(生产者)
            delay(200)//保证消费者先执行
            var count = 0
            while (true) {
                flow.emit("emit:${count++}")
            }
        }
    }

    fun test8() {
        runBlocking {
            //构造热流
            val flow = MutableStateFlow("")
            flow.emit("hello world")
            flow.collect {
                //消费者
                println(it)
            }
        }
    }

    fun test9() {
        runBlocking {
            //构造热流
            val flow = MutableStateFlow("")
            flow.emit("hello world")
            GlobalScope.launch {
                flow.collect {
                    println(it)
                }
            }
            //再发送
            delay(1000)
            flow.emit("hello world")
//            flow.emit("hello world")
        }
    }

    fun test10() {
        runBlocking {
            //构造热流
            val flow = MutableStateFlow("")
            flow.emit("hello world")
            flow.emit("hello world1")
            flow.emit("hello world2")
            flow.emit("hello world3")
            flow.emit("hello world4")
            flow.collect {
                println(it)
            }
        }
    }
}

fun main(args: Array<String>) {
    var demo = MutableSharedFlowDemo()
    demo.test10()
    Thread.sleep(1000000)
}