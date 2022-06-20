package com.fish.kotlindemo.coroutinesuspend

import android.util.Log
import com.fish.kotlindemo.coroutinestory.StudentInfo
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.*
class CoroutineSuspend {
}

fun main(array: Array<String>) {
//    GlobalScope.launch() {
//        println("before suspend")
//        //挂起函数
//        var studentInfo = getStuInfo2() //①
//        //挂起函数执行返回
//        println("after suspend student name:${studentInfo?.name}")//②
//    }

    launchFish {
        println("before suspend")
        var studentInfo = getStuInfo3()
        //挂起函数执行返回
        println("after suspend student name:${studentInfo?.name}")
    }

    //防止进程退出
    Thread.sleep(1000000)
}

suspend fun getStuInfo() {
    delay(5000)
    Log.d("fish", "after delay thread:${Thread.currentThread()}")
}

suspend fun getStuInfo1() {
    thread {
        Thread.sleep(2000)
        println("after sleep")
    }
    println("after thread")
}

suspend fun getStuInfo2(): StudentInfo {
    return withContext(Dispatchers.IO) {
        println("start get studentInfo")
        //模拟耗时操作
        Thread.sleep(3000)
        println("get studentInfo successful")
        //返回学生信息
        StudentInfo()
    }
}

suspend fun getStuInfo3(): StudentInfo {
    return suspendCoroutine<StudentInfo> {
        thread {
            //开启线程执行耗时任务
            Thread.sleep(3000)
            var studentInfo = StudentInfo()
            println("resume coroutine")
            //恢复协程,it指代 Continuation
            it.resumeWith(Result.success(studentInfo))
        }
        println("suspendCoroutine end")
    }
}

fun <T> launchFish(block: suspend () -> T) {
    //创建协程，返回值为SafeContinuation(实现了Continuation 接口)
    //入参为Continuation 类型，参数名为completion，顾名思义就是
    //协程结束后(正常返回&抛出异常）将会调用它。
    var coroutine = block.createCoroutine(object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        //协程结束后调用该函数
        override fun resumeWith(result: Result<T>) {
            println("result:$result")
        }
    })
    //开启协程
    coroutine.resume(Unit)
}