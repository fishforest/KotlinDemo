package com.fish.kotlindemo.classobject

class ConTest1 constructor(var name: String, var age: Int) {
    var studentScore: Float = 0.0f

    //次构造函数
    constructor(name: String, age: Int, score: Float) : this(name, age) {
        studentScore = score
        println("studentScore:$studentScore")
    }
}

