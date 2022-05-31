package com.fish.kotlindemo.classobject

private val staticName: String? = null
fun printStaticName() {}

class KtOuter {
    private var name: String? = null
    //成员方法
    private fun printName() {
        println("name:$name")
    }

    //嵌套类
    class Inner {
        fun testInner() {
            //访问全局函数
            printStaticName()
        }
    }

    //内部类
    inner class RealInner {
        fun testInner() {
            //访问外部类函数和属性
            printName()
            name = "fish"
        }
    }
}