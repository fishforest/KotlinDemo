package com.fish.kotlindemo.`object`

import com.fish.kotlindemo.varclass.age

class ObjectDeclaration {
    fun test() {
        KtSingleton.getStuName()
        KtSingleton.age = 18
    }

    fun test1() {
        mySingleton.getStuName()
    }
}

object KtSingleton {
    var name: String? = null
    var age: Int = 0
    fun getStuName(): String {
        return "name:$name"
    }
}

var mySingleton = KtSingleton
