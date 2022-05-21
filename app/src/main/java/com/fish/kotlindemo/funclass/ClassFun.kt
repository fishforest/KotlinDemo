package com.fish.kotlindemo.funclass

//泛型类
class A<T> {}

//泛型接口
interface B<T> {}

//泛型方法
fun <T> pick(a: T) {
}

class Fruit<T> {
    var quality: T? = null
        get() {
            println("$field")
            return field
        }

    fun setValue(t: T) {
        this.quality = t
    }
}

fun test1() {
    println("normal func")
}

inline fun testInline() {
    println("inline func")
}

fun main(args: Array<String>) {
    var fruit: Fruit<String> = Fruit()
    fruit.setValue("jj")
    //编译不通过
//    fruit.setValue(33)
    fruit.quality
}
