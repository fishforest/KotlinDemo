package com.fish.kotlindemo.classobject

fun main(array : Array<String>) {
//    //错误
//    var javaContest = JavaConTest();
//    //正确
//    var javaContest = JavaConTest("fish", 18)

    var conTest = ConTest("fish", 18)
    println("name:${conTest.studentName}")
}