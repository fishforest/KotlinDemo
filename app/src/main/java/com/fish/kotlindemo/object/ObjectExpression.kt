package com.fish.kotlindemo.`object`

fun main(args: Array<String>) {
    var testJava = TestJava()
    testJava.getStuInfo(object : JavaInterface {
        override fun getStuName(): String {
            return "fish"
        }

        override fun getStuAge(): Int {
            return 18
        }
    })

    //调用
    testJava.getStuInfo(object : JavaAbClass() {
        override fun getStuAge(): Int {
            return 18
        }

        override fun getStuName(): String {
            return "fish"
        }
    })
}

class ObjectExpression {
}
