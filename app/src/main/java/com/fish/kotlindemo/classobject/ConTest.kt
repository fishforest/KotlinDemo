package com.fish.kotlindemo.classobject

import com.fish.kotlindemo.varclass.name

//构造函数测试
//主构造函数
class ConTest constructor(name: String, age: Int) {
    var studentName: String? = null
    var studentAge: Int = 0
    var studentScore: Float = 0.0f

    //初始化
    init {
        studentName = name
        studentAge = age
        println("studentName:$studentName")
    }
    //次构造函数
    constructor(name: String, age: Int, score: Float) : this(name, age) {
        studentScore = score
        println("studentScore:$studentScore")
    }
}