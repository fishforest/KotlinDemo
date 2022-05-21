package com.fish.kotlindemo.funclass

class InlineFun {
}

//普通函数
fun normalFun1() {
    println("normal fun")
}

//内联函数
inline fun inlineFun2() {
    println("inline fun")
}

inline fun inlineFun3(block: (Int) -> String): String {
    println("execute fun3")
    return block(3)
}

fun main(args: Array<String>) {
//    normalFun1()
//    inlineFun2()

    var str = inlineFun3 {
        if (it > 3) {
            ">3"
        } else {
            "<=3"
        }
    }
    println("str $str")
}

var block = { it: Int ->
    if (it > 3) {
        ">3"
    } else {
        "<=3"
    }
}
