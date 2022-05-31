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

    //普通使用
    testJava.getStuInfo(object : EasyJavaInterface {
        override fun getStuName(): String {
            return "fish"
        }
    })
    //Lambda 代替
    testJava.getStuInfo { "fish" }

    //匿名类
    Thread(object : Runnable {
        override fun run() {
            Thread.sleep(100)
            Thread.sleep(200)
        }
    })
    //Lambda
    Thread {
        Thread.sleep(100)
        Thread.sleep(200)
    }

    //外部变量
    var height = 0
    var javaInterface = object : JavaInterface {
        override fun getStuName(): String {
            //编译正确
            height = 99
            return "name"
        }

        override fun getStuAge(): Int {
            return 18
        }
    }
    //扩展属性
    var javaInterface1 = object : JavaInterface {
        var score = 0f
        override fun getStuName(): String {
            return "fish"
        }

        override fun getStuAge(): Int {
            return 18
        }
    }
    javaInterface1.score = 88f
    //赋值
//    javaInterface.score = 99f

    //多接口
    var multiple = object : JavaInterface, JavaAbClass2() {
        override fun getStuName(): String {
            return "fish"
        }
        override fun getStuAge(): Int {
            return 18
        }
        override fun getScore(): Float {
            return 88f
        }
    }

    //对象
    var tempObject = object {
        var name : String ? = null
        var age = 0
    }
    //调用
    tempObject.age = 18
    tempObject.name = "fish"

    fun tempFun() = object  {
        var name : String ? = null
        var age = 0
    }
    tempFun().age = 18
    tempFun().name = "fish"
}

class ObjectExpression {
    private fun tempFun() = object  {
        var name : String ? = null
        var age = 0
    }
    fun tempFun2() = object  {
        var name : String ? = null
        var age = 0
    }
    fun test() {
        //ok
        tempFun().age = 5
        //报错
//        tempFun2().age = 6
    }
}
