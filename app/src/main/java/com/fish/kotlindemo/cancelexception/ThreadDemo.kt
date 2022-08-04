package com.fish.kotlindemo.cancelexception

import java.lang.Exception
import kotlin.concurrent.thread

class ThreadDemo {
    fun testStop() {
        //构造线程
        var t1 = thread {
            var count = 0
            println("thread start")
            //检测是否被中断
            while (count < 100000000 && !Thread.interrupted()) {
                count++
            }
            println("thread end count:$count")
        }
        //等待线程运行
        Thread.sleep(10)
        println("interrupt t1 start")
        t1.interrupt()
        println("interrupt t1 end")
    }

    fun testException() {
        try {
            //开启线程
            thread {
                1/0
            }
        } catch (e : Exception) {
            println("e:$e")
        }
    }

    fun testException2() {
        thread {
            try {
                1/0
            } catch (e : Exception) {
                println("e:$e")
            }
        }
    }

    fun testException3() {
        try {
            //开启线程
            var t1 = thread(false){
                1/0
            }
            t1.name = "myThread"
            //设置
            t1.setUncaughtExceptionHandler { t, e ->
                println("${t.name} exception:$e")
            }
            t1.start()
        } catch (e : Exception) {
            println("e:$e")
        }
    }


}

fun main(args : Array<String>) {
    var threadDemo = ThreadDemo()
//    threadDemo.testStop()
    threadDemo.testException3()
}