package com.fish.kotlindemo.cancelexception

import kotlinx.coroutines.*

class ExceptionDemo {
    fun testException() {
        runBlocking {
            try {
                var job1 = launch(Dispatchers.IO) {
                    println("job1 start")
                    //异常
                    1 / 0
                    println("job1 end")
                }
            } catch (e: Exception) {
            }
        }
    }

    fun testException2() {
        runBlocking {
            var job1 = launch(Dispatchers.IO) {
                try {
                    println("job1 start")
                    //异常
                    1 / 0
                    println("job1 end")
                } catch (e: Exception) {
                    println("e=$e")
                }
            }
        }
    }

    //创建处理异常对象
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("handle exception:$exception")
    }

    fun testException3() {
        runBlocking {
            //声明协程作用域
            var scope = CoroutineScope(Job())
            var job1 = scope.launch(Dispatchers.IO) {
                println("job1 start")
                //异常
                1 / 0
                println("job1 end")
            }
        }
    }

    fun testException4() {
        runBlocking {
            //声明协程作用域
            var rootJob = Job()
            var scope = CoroutineScope(rootJob)
            var job1 = scope.launch(Dispatchers.IO + exceptionHandler) {
                println("job1 start")
                //异常
                1 / 0
                println("job1 end")
            }

            job1.join()
            //检查父Job 状态
            println("rootJob isActive:${rootJob.isActive}")
        }
    }

    fun testException5() {
        runBlocking {
            //声明协程作用域
            var rootJob = Job()
            var scope = CoroutineScope(rootJob)
            var job1 = scope.launch(Dispatchers.IO + exceptionHandler) {
                println("job1 start")
                Thread.sleep(100)
                //异常
                1 / 0
                println("job1 end")
            }

            var job2 = scope.launch {
                println("job2 start")
                Thread.sleep(200)
                //检查jo2状态
                println("jo2 isActive:$isActive")
            }

            job1.join()
            //检查父Job 状态
            println("rootJob isActive:${rootJob.isActive}")
        }
    }

    fun testException6() {
        runBlocking {
            //声明协程作用域
            var rootJob = SupervisorJob()
            var scope = CoroutineScope(rootJob)
            var job1 = scope.launch(Dispatchers.IO + exceptionHandler) {
                println("job1 start")
                Thread.sleep(100)
                //异常
                1 / 0
                println("job1 end")
            }

            var job2 = scope.launch {
                println("job2 start")
                Thread.sleep(200)
                //检查jo2状态
                println("jo2 isActive:$isActive")
            }

            job1.join()
            //检查父Job 状态
            println("rootJob isActive:${rootJob.isActive}")
        }
    }

    fun testException7() {
        runBlocking {
            //声明协程作用域
            var rootJob = SupervisorJob()
            var scope = CoroutineScope(rootJob)
            var job1 = scope.launch(Dispatchers.IO + exceptionHandler) {
                println("job1 start")
                Thread.sleep(2000)
                println("job1 end")
            }

            var job2 = scope.launch {
                println("job2 start")
                Thread.sleep(1000)
                //检查jo2状态
                println("jo2 isActive:$isActive")
            }

            Thread.sleep(300)
            job1.cancel()
            //检查父Job 状态
            println("rootJob isActive:${rootJob.isActive}")
        }
    }

    fun testException8() {
        runBlocking {
            //声明协程作用域
            var rootJob = SupervisorJob()
            var scope = CoroutineScope(rootJob)
            var job1 = scope.launch(Dispatchers.IO + exceptionHandler) {
                println("job1 start")
                Thread.sleep(2000)
                println("jo1 isActive:$isActive")
            }

            var job2 = scope.launch {
                println("job2 start")
                Thread.sleep(1000)
                //检查jo2状态
                println("jo2 isActive:$isActive")
            }

            Thread.sleep(300)
            rootJob.cancel()
            //检查父Job 状态
            println("rootJob isActive:${rootJob.isActive}")
        }
    }
}

fun main(args: Array<String>) {
    var demo = ExceptionDemo()
    demo.testException8()
    Thread.sleep(1000000)
}