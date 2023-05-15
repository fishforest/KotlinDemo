package com.fish.kotlindemo.inner

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.concurrent.thread

class TestInner {

    data class StuInfo(val stuId: Long, val teachId:Long)
    data class TeachInfo(val teachId: Long, val name:String)

    //同步
    fun test0() {
        //模拟网络耗时
        val name = getStuInfoSync()
        println("stu name:${name.teachId}")
    }

    //异步
    fun test1() {
        getStuInfoAsync {
            println("stu name:${it.teachId}")
        }
    }

    fun getStuInfoSync(stuId:Long = 12):StuInfo {
        Thread.sleep(2000)
        return StuInfo(stuId, 99)
    }

    fun getStuInfoAsync(stuId:Long = 12, callback:((StuInfo)->Unit)?) {
        thread {
            //模拟网络耗时
            Thread.sleep(2000)
            callback?.invoke(StuInfo(stuId, 99))
        }
    }

    //异步
    fun test2() {
        getStuInfoAsync {
            getTeachInfoAsync {
                println("teach info:${it.name}")
            }
        }
    }

    fun getTeachInfoAsync(teachId:Long = 99, callback:((TeachInfo)->Unit)?) {
        thread {
            //模拟网络耗时
            Thread.sleep(2000)
            callback?.invoke(TeachInfo(teachId, "Tom"))
        }
    }

    //挂起函数获取学生信息
    suspend fun getStuInfoAsyncForCoroutine(stuId:Long = 12):StuInfo {
        return withContext(Dispatchers.IO) {
            //模拟网络耗时
            Thread.sleep(2000)
            StuInfo(stuId, 99)
        }
    }

    //挂起函数获取教师信息
    suspend fun getTeachInfoAsyncForCoroutine(teachId:Long = 99):TeachInfo {
        return withContext(Dispatchers.IO) {
            //模拟网络耗时
            Thread.sleep(2000)
            TeachInfo(teachId, "Tom")
        }
    }

    fun test3() {
        runBlocking {
            val teachId = getStuInfoAsyncForCoroutine().teachId
            val teachName = getTeachInfoAsyncForCoroutine(teachId).name
            println("teachName:$teachName")
        }
    }


    fun test4() {

        val flow = flow {
            emit("hello")
        }.shareIn(CoroutineScope(Job()), SharingStarted.Eagerly, 20)

        GlobalScope.launch {
            flow.collect {
                println("hello")
            }
        }

        GlobalScope.launch {
        }
    }
}


fun main() {
    val demo = TestInner()
    demo.test3()

    Thread.sleep(1000000)
}
