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

fun inlineFun4(block: (Int) -> String): String {
    println("execute fun4")
    //编译错误
    return inlineFun5(block)
}

inline fun inlineFun5(block: (Int) -> String): String {
    return block(3)
}

inline fun inlineFun6(block: (Int) -> String): String {
    println("execute fun6")
    return block(3)
}

fun testReturn(): String {
    var str = inlineFun6 {
        if (it > 3) {
            ">3"
        } else {
            "<=3"
        }
        //直接return
        return "fish"
    }
    println("execute inlineFun6 str:$str")
    return "fish"
}


fun main(args: Array<String>) {
//    normalFun1()
//    inlineFun2()

//    var str = inlineFun3 {
//        if (it > 3) {
//            ">3"
//        } else {
//            "<=3"
//        }
//    }

//    var str = inlineFun4 {
//        if (it > 3) {
//            ">3"
//        } else {
//            "<=3"
//        }
//    }
//    println("str $str")

    testReturn()
}

var block = { it: Int ->
    if (it > 3) {
        ">3"
    } else {
        "<=3"
    }
}
