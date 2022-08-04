package com.fish.kotlindemo.cancelexception

import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.RuntimeException

class CancelException {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("exception:$exception")
    }
    lateinit var rootJob : Job
    fun launch() {
        runBlocking() {
            rootJob = Job()
            var scope = CoroutineScope(rootJob + handler)
            var job1 = scope.launch {
                println("launch1")
                Thread.sleep(3000)
                println("launch1 after isActive:$isActive")
                throw RuntimeException("me suck")
            }

            var job2 = scope.launch {
                try {
                    delay(50000)
                } catch (e : Exception) {
                    println("job2 exception:$e")
                }
                println("launch2 is $isActive")
            }

            var job3 = scope.launch {
                delay(50000)
                println("launch3")
            }

            Thread.sleep(2000)
//            scope.cancel()
            job1.join()
            job2.join()
            job3.join()
            Thread.sleep(50)
//            job1.cancel("cancel")
            Thread.sleep(30000)
        }
    }

    fun launch2() {
    }

    fun launch3() {
        GlobalScope.launch(Dispatchers.IO + handler) {
            throw RuntimeException("fuck some")
        }
    }
}




fun main(args: Array<String>) {
    var cancelE = CancelException()
    cancelE.launch()

    Thread.sleep(1000000)
}

fun testSon1():String = synchronized("dd") {
    "hello"
}

