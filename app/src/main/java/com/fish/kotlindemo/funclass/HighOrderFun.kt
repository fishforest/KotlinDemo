package com.fish.kotlindemo.funclass

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class StudentInfo {
    //姓名
    var name: String? = "Fish"
    var alias: String? = "小鱼人"

    //省份
    var province: String? = "北京"

    //年龄
    var age: Int? = 18

    //性别
    var isBoy: Boolean = true

    //分数
    var score: Float = 88f
}

//let 使用
fun testLet1(studentInfo: StudentInfo) {
    studentInfo?.isBoy = false
    studentInfo?.name = "小鱼人"
    studentInfo?.age = 14
}

fun testLet2(studentInfo: StudentInfo) {
    var letRet = studentInfo?.let {
        it.isBoy = false
        it.name = "小鱼人"
        it.age = 14
        //Lambda结果作为let 返回值
        "Fish"
    }
    println("letRet:$letRet")
}

//also 使用
fun testAlso1(studentInfo: StudentInfo) {
    studentInfo?.isBoy = false
    studentInfo?.name = "小鱼人"
    studentInfo?.age = 14
}

fun testAlso2(studentInfo: StudentInfo) {
    var letRet = studentInfo?.also {
        it.isBoy = false
        it.name = "小鱼人"
        it.age = 14
        //Lambda结果作为let 返回值
        "Fish"
    }
    println("letRet:${letRet.name}")
}

fun testAlso3(studentInfo: StudentInfo) {
    studentInfo?.also {
        it.isBoy = false
        it.name = "小鱼人"
        it.age = 14
        //Lambda结果作为let 返回值
        "Fish"
    }.let {
        it.score = 99f
    }
}

//with 使用
fun testWith1(studentInfo: StudentInfo) {
    studentInfo?.isBoy = false
    studentInfo?.name = "小鱼人"
    studentInfo?.age = 14
}

fun testWith2(studentInfo: StudentInfo) {
    var withRet = with(studentInfo) {
        isBoy = false
        name = "小鱼人"
        age = 14
        "Fish"
    }
    println("withRet:$withRet")
}

//run 使用
fun testRun1(studentInfo: StudentInfo) {
    studentInfo?.isBoy = false
    studentInfo?.name = "小鱼人"
    studentInfo?.age = 14
}

fun testRun2(studentInfo: StudentInfo) {
    var withRet = studentInfo?.run {
        isBoy = false
        name = "小鱼人"
        age = 14
        "Fish"
    }
    println("withRet:$withRet")
}

//apply 使用
fun testApply1() {
    var studentInfo = StudentInfo()
    studentInfo.isBoy = false
    studentInfo.name = "小鱼人"
    studentInfo.age = 14
}

fun testApply2() {
    var applyRet = StudentInfo().apply {
        isBoy = false
        name = "小鱼人"
        age = 14
        "Fish"
    }
    println("withRet:${applyRet.name}")
}

//repeat 使用
fun testRepeat1() {
    var list = mutableListOf<StudentInfo>()
    for (index in 1..100) {
        list.add(StudentInfo())
    }
}

fun testRepeat2() {
    var list = mutableListOf<StudentInfo>()
    repeat(10) {
        //重复这个动作10次
        list.add(StudentInfo())
        println("第 $it 个")
    }
}

fun main(args: Array<String>) {
    testAlso2(StudentInfo())
    testWith2(StudentInfo())
}
