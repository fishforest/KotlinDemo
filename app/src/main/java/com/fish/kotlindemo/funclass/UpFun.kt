package com.fish.kotlindemo.funclass

fun upFun1(name: String, age: Int): Float {
    return 88f
}

//testUpFun1 接收的参数为函数类型：(String, Int)->String
fun testUpFun1(getScore : (String, Int)->Float) {
    var score = getScore("fish", 18)
    println("student score:$score")
}

fun main(args: Array<String>) {
    testUpFun1(::upFun1)
}
