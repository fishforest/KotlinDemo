package com.fish.kotlindemo.funclass

import com.fish.kotlindemo.varclass.name
import com.fish.kotlindemo.varclass.test
import kotlinx.coroutines.Delay
import kotlin.concurrent.thread

fun upFun1(name: String, age: Int): Float {
    return 88f
}

//赋值
//var varFun:(name: String, age: Int)->Float = ::upFun1
var varFun = ::upFun1

//testUpFun1 接收的参数为函数类型：(String, Int)->String
fun testUpFun1(getScore: (String, Int) -> Float) {
    var score = getScore("fish", 18)
    println("student score:$score")
}

//匿名函数
//var varFun1:(String, Int)->Float = fun (name: String, age: Int):Float {
//    return 88f
//}
//类型推断
var varFun1 = fun(name: String, age: Int): Float {
    return 88f
}

//Lambda 表达式
//var lambda1:(String, Int)->Float = {
//    name:String,age:Int->
//    88f
//}

var lambda1 = { name: String, age: Int ->
    88f
}

//定义匿名函数
var anoymous1: (String, Int) -> Float = fun(name: String, age: Int): Float {
    println("name:$name age:$age")
    return 88f
}

//自动推导，消除变量类型
var anoymous2 = fun(name: String, age: Int): Float {
    println("name:$name age:$age")
    return 88f
}

//传入匿名函数
fun main0(args: Array<String>) {
    //传入匿名函数
    testUpFun1(fun(name: String, age: Int): Float {
        println("name:$name age:$age")
        return 88f
    })
    testUpFun1(anoymous1)
    testUpFun1(anoymous2)
}

fun testUpFun2(getScore: (String, Int) -> Float, needDelay: Boolean) {
    if (needDelay)
        println("delay...")
    var score = getScore("fish", 18)
    println("student score:$score")
}


fun main1(args: Array<String>) {
    //传入Lambda
    testUpFun1 { name: String, age: Int ->
        88f
    }
    //传入匿名函数
    testUpFun1({ name: String, age: Int ->
        println("name:$name age:$age")
        88f
    }
    )
}

//完整写法
var varLambda1: (String, Int) -> Float = { name: String, age: Int ->
    println("student name:$name age:$age")
    88f
}

//var varLambda1 = { name: String, age: Int ->
//    println("student name:$name age:$age")
//    88f
//}
//
//var varLambda1: (String, Int) -> Float = { name, age ->
//    println("student name:$name age:$age")
//    88f
//}

var varLambda2 = { name: String, int: Int ->
    println()
    test()
    "jj"
}

fun testUpFun3(needDelay: Boolean, getScore: (String, Int) -> Float) {
    if (needDelay)
        println("delay...")
    var score = getScore("fish", 18)
    println("student score:$score")
}

fun testUpFun4(getScore: (String, Int) -> Float) {
    var score = getScore("fish", 18)
    println("student score:$score")
}

fun testUpFun5(getScore: (String) -> Float) {
    var score = getScore("fish")
    println("student score:$score")
}

fun testUpFun6(getScore: (String) -> Unit): (Boolean, Int) -> String {
    //调用函数
    var score = getScore("fish")
    println("student score:$score")

    //返回函数，Lambda表示
    return { need: Boolean, age: Int ->
        println("need:$need  age:$age")
        "fish"
    }
}

fun main2(args: Array<String>) {
    testUpFun2({ name: String, age: Int ->
        println("name:$name age:$age")
        88f
    }, true
    )
}

fun main3(args: Array<String>) {
    testUpFun3(true
    ) { name: String, age: Int ->
        println("name:$name age:$age")
        88f
    }
}

fun main4(args: Array<String>) {
    ////省略"()"
    testUpFun4 { name: String, age: Int ->
        println("name:$name age:$age")
        88f
    }
}

//fun main5(args: Array<String>) {
//    ////省略"()"
//    testUpFun5  { name: String ->
//        println("name:$name")
//        88f
//    }
//}

fun main5(args: Array<String>) {
    ////省略"()"
    testUpFun5 {
        println("name:$it")
        88f
    }
}

fun main6(args: Array<String>) {
    ////省略"()"
    var testReturn = testUpFun6 {
        println("name:$it")
    }
    testReturn(true, 5)
}
