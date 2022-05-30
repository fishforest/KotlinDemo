package com.fish.kotlindemo.classobject

import com.fish.kotlindemo.varclass.name

//构造函数测试
class ConTest constructor(name: String, age: Int) {
    var studentName: String? = null
    var studentAge:Int = 0

    //初始化
    init {
        studentName = name
        studentAge = age
    }
}