package com.fish.kotlindemo.flowoperand

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.flow.internal.FusibleFlow
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class FlowOperand() {
    fun test0() {
        runBlocking {
            //构造flow
            val flow = flow {
                //下游
                emit("hello world ${Thread.currentThread()}")
            }
            //收集flow
            flow.collect {
                //下游
                println("collect:$it ${Thread.currentThread()}")
            }

        }
    }

    fun test1() {
        runBlocking {
            val flow = StudentInfo().getInfoFlow()
            flow.collect {
                println("studentInfo:$it")
            }
        }
    }

    fun test2() {
        //提前建立通道/管道
        val channel = Channel<String>()
        GlobalScope.launch {
            //上游放数据（放水）
            delay(200)
            val data = "放水啦"
            println("上游:data=$data ${Thread.currentThread()}")
            channel.send(data)
        }

        GlobalScope.launch {
            val data = channel.receive()
            println("下游收到=$data ${Thread.currentThread()}")
        }
    }

    fun test3() {
        runBlocking {
            //构造flow
//            val flow = flow {
//                //下游
//                //模拟耗时
//                thread {
//                    Thread.sleep(3000)
////                    emit("hello world ${Thread.currentThread()}")
//                }
//            }
        }
    }

    fun test4() {
        runBlocking {
            //构造flow
            val flow = flow {
                //下游
                val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
                coroutineScope.launch {
                    //模拟耗时，在子线程执行
                    Thread.sleep(3000)
                    emit("hello world ${Thread.currentThread()}")
                }
            }
            flow.collect {
                println("collect:$it")
            }
        }
    }

    fun test5() {
        runBlocking {
            //构造flow
            val myFlow = MyFlow {
                send("hello world emit 线程: ${Thread.currentThread()}")
            }

            myFlow.collect {
                println("下游收到=$it collect 线程: ${Thread.currentThread()}")
            }
        }
    }

    fun test6() {
        GlobalScope.launch {
            //构造flow
            val channelFlow = channelFlow<String> {
                send("hello world emit 线程: ${Thread.currentThread()}")
            }

            channelFlow.collect {
                println("下游收到=$it collect 线程: ${Thread.currentThread()}")
            }
            println("real end2")

        }
    }

    fun test7() {
        runBlocking {
            //构造flow
            val channelFlow = channelFlow {
                getName(object : NetResult<String> {
                    override fun onSuc(t: String) {
                        println("begin send")
                        trySend("hello world emit 线程: ${Thread.currentThread()}")
                        println("stop send")
                        //关闭channel，触发awaitClose闭包执行
                        close()
                    }
                    override fun onFail(err: String) {
                    }
                })

                //挂起函数
                awaitClose {
                    //走到此，channel关闭
                    println("awaitClose")
                }
            }

            channelFlow.collect {
                println("下游收到=$it collect 线程: ${Thread.currentThread()}")
            }
        }
    }

    fun test8() {
        runBlocking {
            //构造flow
            val channelFlow = callbackFlow {
                getName(object : NetResult<String> {
                    override fun onSuc(t: String) {
                        println("begin send")
                        trySend("hello world emit 线程: ${Thread.currentThread()}")
                        println("stop send")
                        //关闭channel，触发awaitClose闭包执行
//                        close()
                    }
                    override fun onFail(err: String) {
                    }
                })

                //挂起函数
                awaitClose {
                    //走到此，channel关闭
                    println("awaitClose")
                }
            }

            channelFlow.collect {
                println("下游收到=$it collect 线程: ${Thread.currentThread()}")
            }
        }
    }

    fun test9() {
        runBlocking {
            val channel = Channel<String>()
            val flow = channel.receiveAsFlow()
            GlobalScope.launch {
                flow.collect {
                    println("collect:$it")
                }
            }
            delay(200)
            channel.send("hello fish")
        }
    }

    fun test10() {
        runBlocking {
            val flow = flow {
                emit("hello fish")
            }
            val channel = flow.produceIn(this)
            val data = channel.receive()
            println("data:$data")
        }
    }
}

fun getName(callback:NetResult<String>) {
    thread {
        //假装从网络获取
        Thread.sleep(2000)
        callback.onSuc("I'm fish")
    }
}

interface NetResult<T> {
    fun onSuc(t:T)
    fun onFail(err:String)
}

//参数为SendChannel扩展函数
class MyFlow(private val block: suspend SendChannel<String>.() -> Unit) : Flow<String> {
    //构造Channel
    private val channel = Channel<String>()
    override suspend fun collect(collector: FlowCollector<String>) {
        val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
        coroutineScope.launch {
            //启动协程
            //模拟耗时，在子线程执行
            Thread.sleep(3000)
            //把Channel对象传递出去
            block(channel)
        }

        //获取数据
        val data = channel.receive()
        //发射
        collector.emit(data)
    }
}

class StudentInfo {
    fun getInfoFlow(): Flow<String> {
        return flow {
            //假装构造数据
            Thread.sleep(2000)
            emit("name=fish age=18")
        }
    }
}

fun main() = runBlocking<Unit> {
    FlowOperand().test10()
    Thread.sleep(200000)
}