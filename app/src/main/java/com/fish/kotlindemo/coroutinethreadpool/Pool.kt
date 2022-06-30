package com.fish.kotlindemo.coroutinethreadpool

import android.content.Context
import android.widget.Toast
import com.fish.kotlindemo.coroutineraw.launchFish
import kotlinx.coroutines.*

class Pool(val context: Context?) {
    fun showStuName() {
        GlobalScope.launch(Dispatchers.Main) {
            var stuInfo = withContext(Dispatchers.IO) {
                //模拟网络请求
                Thread.sleep(3000)
                "我是小鱼人"
            }
            //展示
            Toast.makeText(context, stuInfo, Toast.LENGTH_SHORT).show()
        }
    }

    fun dealCpuTask() {
        GlobalScope.launch(Dispatchers.Main) {
            //切换到子线程
            withContext(Dispatchers.Default) {
                var i = 0
                val count = 100000
                while(i < count) {
                    Thread.sleep(1)
                }
            }
        }
    }

    fun launch() {
        GlobalScope.launch(Dispatchers.Default) {

        }
    }

    fun launchIO() {
        GlobalScope.launch(Dispatchers.IO) {
            println("")
        }
    }

    fun launch3() {
        GlobalScope.launch(Dispatchers.IO) {
            println("1>>>${Thread.currentThread()}")
            withContext(Dispatchers.Default) {
                println("2>>>${Thread.currentThread()}")
                delay(2000)
                println("3>>>${Thread.currentThread()}")
            }
            println("4>>>${Thread.currentThread()}")
        }
    }
}

fun main(array: Array<String>) {
    var pool = Pool(null)
    pool.launchIO()

    Thread.sleep(300000)
}

