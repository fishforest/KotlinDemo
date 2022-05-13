package com.fish.kotlindemo.varclass

fun testV2(name: String, age: Int, score: Double) {

}

fun testV3(name: String, age: Int = 5, score: Double){

}

fun testV4(name: String = "fish", age: Int, score: Double = 4.5){

}

fun testV5(vararg name:String) {
    name.forEach {
        println(it.length)
    }
}
