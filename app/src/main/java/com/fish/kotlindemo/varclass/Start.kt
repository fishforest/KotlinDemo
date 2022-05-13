package com.fish.kotlindemo.varclass


fun main(args:Array<String>) {
    //赋值
    num = 3.5
    //取值
    var numLong2 = num
    test()
}

fun main1() {
    var varTestClass = VarTestClass()
    //赋值
    varTestClass.num = 3
    //获取值
    var num = varTestClass.num
}

fun main2() {
}