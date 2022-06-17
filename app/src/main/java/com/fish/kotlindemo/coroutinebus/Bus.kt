package com.fish.kotlindemo.coroutinebus

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Bus {

}

fun drive() {
    println("我坐车，我快乐")
    //车要停一会
    Thread.sleep(3000)
    println("车继续开")
}



fun drive2() {
    GlobalScope.launch(Dispatchers.Main) {
        println("我坐车，我快乐")//①
        withContext(Dispatchers.IO) {
            println("导游去订酒店")//②
        }
        println("车继续开，剩下团友在车上")//③④
    }
}

fun drive3() {
    GlobalScope.launch(Dispatchers.IO) {
        println("我坐车，我快乐")//①
        withContext(Dispatchers.Default) {//②
            println("导游去订酒店")//③
        }
        println("车继续开，剩下团友在车上")//④
    }
}