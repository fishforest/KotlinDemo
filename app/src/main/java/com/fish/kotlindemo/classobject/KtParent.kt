package com.fish.kotlindemo.classobject

//基类
open class KtParent(age: Int) {
    open var name: String? = null

    open fun printName() {
        println("name:$name")
    }
}

class KtSon constructor(age: Int) : KtParent(age) {
    override var name: String? = null

    override fun printName() {
        super.printName()
        println("in KtSon")
    }
}

interface KtInter {
    fun printInter();
}

class KotlinClass : KtInter {
    override fun printInter() {
    }
}

class KotlinClassInter : KtInter, KtParent(18) {
    override fun printInter() {
        TODO("Not yet implemented")
    }
}