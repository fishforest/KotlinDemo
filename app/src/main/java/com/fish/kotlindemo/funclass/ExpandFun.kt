package com.fish.kotlindemo.funclass

class ExpandFun {
}

class Student {
    //来自省份
    var province:String?= null
    //学生名字
    var name:String? = null
    init {
        name = "fish"
        province = "beijing"
    }
    fun printStudent() {
        println("$name")
    }
}

fun main(args: Array<String>) {
    var student = Student()
    student.printStudent1()

    var b1 = "Fish".isFirstUpper()
    var b2 = "1Fish".isFirstUpper();
    println("$b1 $b2")
}


fun Student.printStudent1() {
    println("name:$name province:$province")
}

fun String.isFirstUpper():Boolean {
    if (isNotEmpty()) {
        return get(0).code in 65..97
    }
    return false
}