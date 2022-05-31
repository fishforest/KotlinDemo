package com.fish.kotlindemo.classobject

interface KotlinInterA {
    fun test() {
        println("interface A")
    }
}
interface KotlinInterB {
    fun test() {
        println("interface B")
    }
}
class TestInter() : KotlinInterA, KotlinInterB {
    override fun test() {
        //选择调用父类函数
        super<KotlinInterA>.test()
        super<KotlinInterB>.test()
    }
}