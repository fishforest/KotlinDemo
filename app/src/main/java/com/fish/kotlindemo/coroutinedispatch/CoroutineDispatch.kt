package com.fish.kotlindemo.coroutinedispatch

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import com.fish.kotlindemo.coroutineraw.launchFish
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.*

//协程分发
class CoroutineDispatch(val context: Context) {

    fun startCoroutine() {
        GlobalScope.launch(Dispatchers.Main) {
            //协程体
        }
    }

    private inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            //主线程弹出toast
            Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    //获取学生信息
    fun showStuInfo() {
        thread {
            //模拟网络请求
            Thread.sleep(3000)
            var handler = MyHandler()
            var msg = Message.obtain()
            msg.obj = "我是小鱼人"
            //发送到主线程执行
            handler.sendMessage(msg)
        }
    }

    fun showStuInfoV2() {
        GlobalScope.launch(Dispatchers.Main) {
            var stuInfo = withContext(Dispatchers.IO) {
                //模拟网络请求
                Thread.sleep(3000)
                "我是小鱼人"
            }

            Toast.makeText(context, stuInfo, Toast.LENGTH_SHORT).show()
        }
    }

    fun switchThread() {
        println("我在某个线程，准备切换到主线程")
        GlobalScope.launch(Dispatchers.Main) {
            println("我在主线程，准备切换到子线程")
            withContext(Dispatchers.IO) {
                println("我在子线程，准备切换到子线程")
                withContext(Dispatchers.Default) {
                    println("我在子线程，准备切换到主线程")
                    withContext(Dispatchers.Main) {
                        println("我在主线程")
                    }
                }
            }
        }
    }

    fun launch1() {
        GlobalScope.launch(Dispatchers.Main) {

            println("我在主线程执行")

        }
    }

    fun launch2() {
        GlobalScope.launch(Dispatchers.Main) {

            println("我在主线程执行")

            withContext(Dispatchers.IO) {
                println("我在子线程执行")
            }

            println("我在哪个线程执行？")//③
        }
    }

    fun launch3() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Default) {
                println("我在哪个线程运行")
                delay(2000)
                println("delay 后我在哪个线程运行")
            }
            println("我又在哪个线程运行")
        }
    }
}

fun main(array: Array<String>) {
    launchFish {
        println("I am coroutine")
    }
}
