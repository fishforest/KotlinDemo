package com.fish.kotlindemo.classobject

fun main(array : Array<String>) {
//    //错误
//    var javaContest = JavaConTest();
//    //正确
//    var javaContest = JavaConTest("fish", 18)

    var conTest = ConTest("fish", 18)
    println("name:${conTest.studentName}")

    //次构造函数调用
    var conTest2 = ConTest("fish", 18, 88f)

    //主构造函数声明成员变量
    var conTest1 = ConTest1("fish", 18)
    println("name:${conTest1.name} age:${conTest1.age}")
}