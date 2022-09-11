package com.fish.kotlindemo.channel

import kotlinx.coroutines.channels.*

class TestChannel {
    fun test(blcok:(String)->Int) {
        blcok("hello")
    }
}

fun main(args: Array<String>) {
    var demo = TestChannel()
    demo.test {
        13
    }
    Thread.sleep(1000000)
}