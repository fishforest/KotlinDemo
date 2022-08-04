package com.fish.kotlindemo.cancelexception

import kotlinx.coroutines.*
import java.lang.RuntimeException

class JobDemo {
    fun testJob() {
        //父Job
        var rootJob: Job? = null
        runBlocking {
            //启动子Job
            var job1 = launch {
                println("job1")
            }
            //启动子Job
            var job2 = launch {
                println("job2")
            }
            rootJob = coroutineContext[Job]
            job1.join()
            job2.join()
        }
    }

    fun testJob2() {
        runBlocking {//父Job==rootJob
            //启动子Job
            var job1 = launch {
                println("job1")
                Thread.sleep(200000)
            }
//            //启动子Job
            var job2 = launch {
                println("job2")
                Thread.sleep(200000)
            }
            Thread.sleep(200)
           job1.cancel("")
        }
    }
}

fun main(args: Array<String>) {
    var jobDemo = JobDemo()
    jobDemo.testJob2()

    Thread.sleep(200000)
}