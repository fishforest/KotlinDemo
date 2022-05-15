package com.fish.kotlindemo.funclass

import com.fish.kotlindemo.varclass.age
import com.fish.kotlindemo.varclass.name

class ClassFun {
}

fun main(args: Array<String>) {
    //匿名调用方式
    testFun2(fun (age: Int, name: String): String {
        println("age :$age name:$name")
        return name
    })

    //函数引用调用方式
    testFun2(::testFun1)

    //变量调用方式
    testFun2(test)

    testFun2(test1)

    //lambda 调用
    testFun2 {
        age, name ->
        println("$age $name")
        "jj"
    }

    test3("sdd") { age:Int, name:String ->
        println("$age $name")
        "jj"
    }

    test3("sdd") { age, name ->
        println("$age $name")
        "jj"
    }

    test4({
        age, name ->
        "jj"
    }, "jj")

    test5 {
        it.toString()
    }
}

//引用声明
var test = ::testFun1

//匿名函数声明
var test1 = fun (age: Int, name: String): String {
    println("age :$age name:$name")
    return name
}

//lamba 声明
var test2 = {age:String->
    "ffff"
    "jjj"
}

//单参数自动类型推断
var test22 = {
    "jjj"}

//lamba 声明
var test3 = { age: Int, name: String ->
    println("$age $name")
     "dsd"
}

//正常声明
fun testFun1(age: Int, name: String): String {
    println("age :$age name:$name")
    return name
}

fun testFun2(fun1: (age:Int, name:String) -> String) {
    var name = fun1(18, "fish")
}


fun testFun3(fun2 : (age:Int)->String) {
    fun2(3)
}

//声明 + 实现
var test333 = { age:Int-> println("$age")
    33}

//声明
var test444:(Int)->Int = {
    it.toString()
    44}


var test445:(Int)->Int = {
    it.toString()
    44}


var test555 = {
    age:Int->
    println("$age")
    55
}

var test556:(Int, String)->Int = {
        age, name->
    println("$age")
    55
}

fun test3(str:String, fun1: (age:Int, name:String) -> String) {

}

fun test4(fun1: (age:Int, name:String) -> String, str:String) {

}

fun test5(fun1:(age:Int)->String) {

}