package com.fish.kotlindemo.varclass


fun main(args: Array<String>) {
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
    testV2("fish", 5, 4.5)
    testV2(score = 4.5, name = "fish", age = 5)
}

fun  main3() {
    testV4("fish", 4)
}

fun  main4() {
    testV5("fish", "fish2", "fish3")
    var myStart = MyStart()
    myStart.start()
}

class MyStart {
    fun start() {
        fun end() {
        }
        //调用
        end()
    }
}
