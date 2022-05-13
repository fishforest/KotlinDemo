package com.fish.kotlindemo.varclass

//kotlin
//变量
@JvmField
var num = 3.4


//常量

val age = 4

////java
////变量
//int num = 3;
////常量
//final int age = 4;

@JvmField
var numInt: Int = 4
var numLong: Long = 5

//kotlin 转换
@JvmName("test2")
fun test() {
    //不被允许
//    numInt = numLong
//    numLong = numInt

    //允许
    //大范围转小范围可能会发生溢出
    numInt = numLong.toInt()
    numLong = numInt.toLong()
}

//Java 转换
//void test() {
//    int a = 5;
//    //小转大  允许
//    long b = a;
//    //大转小 允许
//    a = (int) b;
//}

//初始化
//初始
var myAge = 3

//正常初始化
val myNum = 4

//延迟初始化 常量
val myNum2: Int by lazy { 3 }

class MyBean {
    var age = 3
}

//正常初始化
val myBean = MyBean()

//延迟初始化
val myBean1: MyBean by lazy {
    MyBean()
}

//变量正常初始化
var name: String = "fish"

//变量延迟初始化
lateinit var name1: String

fun useName() {
    name1 = "fish1"
}

////错误，不能修饰基本类型
//lateinit var num3:Int
////错误，不能修饰空类型
//lateinit var name2?:String

//get set 方式

var myName: String = ""
    get() {
        //获取值
        var age = 2
        if (age > 18)
            return field
        return ""
    }
    set(value) {
        //设置值
        if (value == "fish")
            field = value
    }

fun test4() {

}