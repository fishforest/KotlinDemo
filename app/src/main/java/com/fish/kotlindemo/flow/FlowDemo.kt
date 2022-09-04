package com.fish.kotlindemo.flow

import com.fish.kotlindemo.common.CommonDemo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FlowDemo {
    fun testFlow() {
        runBlocking {
            var channel = Channel<String>()
            GlobalScope.launch {
                Thread.sleep(5000)
                channel.send("hello fish")
            }

//            println(channel.receive())
        }
    }
}


fun main(args: Array<String>) {
    var demo = FlowDemo()
    demo.testFlow()
    Thread.sleep(1000000)
}