package com.fish.kotlindemo.channel

import com.fish.kotlindemo.common.CommonDemo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import kotlin.concurrent.thread

class ChannelDemo {

    fun testChannel() {
        //协程1
        var deferred= GlobalScope.async {
            //假装在加工数据
            Thread.sleep(2000)
            "Hello fishforest"
        }
        //协程2
        GlobalScope.launch {
            var result = deferred.await()
            println("get result from coroutine1： $result")
        }
    }

    fun testChannel2() {
        //阻塞队列
        var queue = ArrayBlockingQueue<String>(5)
        //协程1
        GlobalScope.launch {
            var count = 0
            while (true) {
                //假装在加工数据
                Thread.sleep(1000)
                queue.put("fish ${count++}")
            }
        }

        //协程2
        GlobalScope.launch {
            while (true) {
                Thread.sleep(1000)
                println("get result from coroutine1：${queue.take()}")
            }
        }
    }

    //默认类型
    fun testChannel3() {
        //定义Channel
        var channel = Channel<String>()
        //协程1
        GlobalScope.launch {
            var count = 0
            while (true) {
                //假装在加工数据
                Thread.sleep(1000)
                var sendStr = "fish ${count++}"
                println("send $sendStr")
                channel.send("$sendStr")
            }
        }

        //协程2
        GlobalScope.launch {
            while (true) {
                Thread.sleep(1000)
                println("receive：${channel.receive()}")
            }
        }
    }

    //混合类型
    fun testChannel4() {
        //定义Channel
        var channel = Channel<String>(CONFLATED)
        //协程1
        GlobalScope.launch {
            var count = 0
            while (true) {
                var sendStr = "fish ${count++}"
                println("send $sendStr")
                channel.send("$sendStr")
            }
        }

        //协程2
        GlobalScope.launch {
            while (true) {
                println("receive：${channel.receive()}")
            }
        }
    }

    //缓冲类型
    fun testChannel5() {
        //定义Channel
        var channel = Channel<String>(Channel.BUFFERED)
        //协程1
        GlobalScope.launch {
            var count = 0
            while (true) {
                var sendStr = "fish ${count++}"
                println("send $sendStr")
                channel.send("$sendStr")
            }
        }

        //协程2
        GlobalScope.launch {
            while (true) {
                println("receive：${channel.receive()}")
            }
        }
    }

    //无限类型
    fun testChannel6() {
        //定义Channel
        var channel = Channel<String>(Channel.UNLIMITED)
        //协程1
        GlobalScope.launch {
            var count = 0
            while (true) {
                var sendStr = "fish ${count++}"
                println("send $sendStr")
                channel.send("$sendStr")
                break;
            }
        }

        //协程2
        GlobalScope.launch {
            while (true) {
                println("receive：${channel.receive()}")
            }
        }
    }

    //produce
    fun testProduce() {
        //返回接收者
        var receiveChannel = GlobalScope.produce<String> {
            //
            for (x in 1..5) {
                var sendStr = "fish $x"
                println("send $sendStr")
                send("$sendStr")
            }
        }
        //接收数据
        GlobalScope.launch {
            while (true) {
                println("job2 receive:${receiveChannel.receive()}")
            }
            println("job2 end")
        }
        GlobalScope.launch {
            while (true) {
                println("job3 receive:${receiveChannel.receive()}")
            }
            println("job3 end")
        }
    }

    fun testActor() {
        //返回发送者
        var sendChannel = GlobalScope.actor<String> {
            //
            for (x in 1..5) {
                println("job1 receive:${receive()}")
            }
            println("actor end")
        }
        //发送者
        GlobalScope.launch {
            while (true) {
                sendChannel.send("send from job2")
            }
            println("job2 end")
        }
        GlobalScope.launch {
            sendChannel.send("send from job3")
        }
    }
}


fun main(args: Array<String>) {
    var demo = ChannelDemo()
    demo.testActor()
    Thread.sleep(1000000)
}