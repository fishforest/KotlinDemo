package com.fish.kotlindemo.flow


class Test {
}

fun main(str: Array<String>) {
    var i = 0
    var list = mutableListOf<Int>()
    while (i < 1000) {
        if (i > 500 && i % 2 == 0)
            list.add(i)
        i++
    }
}