package com.fish.kotlindemo.datastore

sealed class NewsIntents {
    object TopHeadlinesIntent : NewsIntents()
}

sealed class NewsStates {
    data class Success(val news: String) : NewsStates()
    data class Error(val errorMessage: String) : NewsStates()
    object Loading : NewsStates()
}

open class Stu {
    val name: String? = null
    val age: Int? = null
    fun jj() {
    }
}

class C {
    object jj : Stu() {
    }
}

// 私有函数，所以其返回类型是匿名对象类型
private fun foo() = object {
    val x: String = "x"

    fun lishi() {

    }
}

// 公有函数，所以其返回类型是 Any
fun publicFoo() = object {

}

fun ts() {
    var str : String? = null
}