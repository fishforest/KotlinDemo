package com.fish.kotlindemo.common

import com.fish.kotlindemo.cancelexception.CancelDemo
import kotlinx.coroutines.*

class CommonDemo {
    fun testBlock() {
        println("before runBlocking thread:${Thread.currentThread()}")
        //①
        runBlocking() {

            println("I'm runBlocking start thread:${Thread.currentThread()}")
            Thread.sleep(2000)
            println("I'm runBlocking end")
        }
        //②
        println("after runBlocking:${Thread.currentThread()}")
    }

    fun testBlock2() {
        var name = runBlocking {
            "fish"
        }
        println("name $name")
    }

    fun testBlock3() {
        println("before runBlocking thread:${Thread.currentThread()}")
        //①
        runBlocking(Dispatchers.IO) {
            println("I'm runBlocking start thread:${Thread.currentThread()}")
            Thread.sleep(2000)
            println("I'm runBlocking end")
        }
        //②
        println("after runBlocking:${Thread.currentThread()}")
    }

    fun testLaunch() {
        var job = GlobalScope.launch {

            println("hello job1 start")//①
            Thread.sleep(2000)
            println("hello job1 end")//②

        }
        println("continue...")//③
    }

    fun testLaunch2() {
        runBlocking {
            var job = GlobalScope.launch {

                println("hello job1 start")//①
                Thread.sleep(10000)
                println("hello job1 end")//②
            }
            //等待协程执行完成
            job.join()

            println("continue...")//③

        }
    }

    fun testAsync() {
        runBlocking {
            //启动协程
            var job = GlobalScope.async {

                println("job1 start")
                Thread.sleep(10000)
                //返回值
                "fish"
            }
            //等待协程执行结束，并返回协程结果
            var result = job.await()
            println("result:$result")
        }
    }

    fun testAsyncEx() {
        runBlocking {
            //启动协程
            var job = GlobalScope.async {

                println("job1 start")
                Thread.sleep(5000)
                //返回值
                1/0
            }
            //等待协程执行结束，并返回协程结果
            var result = job.await()
            println("result:$result")
        }
    }

    fun testDelay() {
        GlobalScope.launch {
            println("before getName")
            var name = getUserName()
            println("after getName name:$name")
        }
    }

    suspend fun getUserName():String {
        return withContext(Dispatchers.IO) {
            //模拟网络获取
            Thread.sleep(2000)
            "fish"
        }
    }

    fun testDelay2() {
        GlobalScope.launch {

            println("before delay")
            //协程挂起20s
            delay(20000)

            println("after delay")
        }
    }
}

fun main(args: Array<String>) {
    var demo = CommonDemo()
    demo.testDelay2()
    Thread.sleep(1000000)
}