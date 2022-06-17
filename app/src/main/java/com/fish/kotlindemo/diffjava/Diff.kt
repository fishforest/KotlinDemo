package com.fish.kotlindemo.diffjava

import java.lang.Exception

class Diff {
    fun getNum() = 3
}

fun test() {
    var list = arrayListOf("jj", "mme", "fuck")
    var result = list?.let {
        var result = false
        for (name in list) {
            if(name == "mme") {
                println("hello")
                result = true
                break
            }
        }
        result
    }
    println("wo haizai")
}

fun test2(diff: Diff?):Int? {
    return diff!!.getNum()
}

fun main(args : Array<String>) {
    var result = test()
    println("result:$result")
    var diff :Diff? = null
    try {
        var jj = test2(diff)
    } catch (e : Exception) {
        println("ddd")
    }
    println("shit")
}

