package com.fish.kotlindemo.cancelexception

import kotlinx.coroutines.*

class CancelDemo {
    fun testCancel() {
        runBlocking() {
            var job1 = launch(Dispatchers.IO) {
                println("job1 start")
                Thread.sleep(200)
                var count = 0
                while (count < 1000000000) {
                    count++
                }
                println("job1 end count:$count")
            }
            Thread.sleep(100)
            println("start cancel job1")
            //取消job（取消协程）
            job1.cancel()
            println("end cancel job1")
        }
    }

    fun testCancel2() {
        runBlocking() {
            var job1 = launch(Dispatchers.IO) {
                println("job1 start")
                Thread.sleep(80)
                var count = 0
                //判断协程的状态，若是活跃则继续循环
                //isActive = coroutineContext[Job]?.isActive ?: true
                while (count < 1000000000 && isActive) {
                    count++
                }
                println("job1 end count:$count")
            }
            Thread.sleep(100)
            println("start cancel job1")
            //取消job（取消协程）
            job1.cancel()
            println("end cancel job1")
        }
    }

    fun testCancel3() {
        runBlocking() {
            var job1 = launch(Dispatchers.IO) {
                Thread.sleep(3000)
                println("coroutine isActive:$isActive")//①
            }
            Thread.sleep(100)
            println("start cancel job1")
            //取消job（取消协程）
            job1.cancel()
            println("end cancel job1")
        }
    }

    fun testCancel4() {
        runBlocking() {
            var job1 = launch(Dispatchers.IO) {
                //协程挂起
                println("job1 start")
                try {
                    delay(3000)
                } catch (e : Exception) {
                    println("delay exception:$e")
                }
                println("coroutine isActive:$isActive")//①
            }
            Thread.sleep(100)
            println("start cancel job1")
            //取消job（取消协程）
            job1.cancel()
            println("end cancel job1")
        }
    }

    fun testCancel5() {
        runBlocking() {
            var job1 = launch(Dispatchers.IO) {
                try {
                    //挂起函数
                } catch (e : Exception) {
                    println("delay exception:$e")
                }
                if (!isActive) {
                    println("cancel")
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    var demo = CancelDemo()
    demo.testCancel4()
    Thread.sleep(1000000)
}