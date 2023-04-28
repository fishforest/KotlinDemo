package com.fish.kotlindemo.flowoperand

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

import kotlin.system.measureTimeMillis

class FlowOperandAll {
    //构建操作符
    fun test1() {
        runBlocking {
            flow {
                emit("hello fish")
            }.collect {
                println("collect:$it")
            }
        }
    }

    fun test2() {
        runBlocking {
            flowOf(1, 2, 3).collect {
                println("collect:$it")
            }
        }
    }

    fun test3() {
        runBlocking {
            (1..3).asFlow().collect {
                println("collect:$it")
            }
        }
    }

    fun test4() {
        runBlocking {
            channelFlow {
                trySend("hello fish")
            }.collect {
                println("collect:$it")
            }
        }
    }

    fun test5() {
        runBlocking {
            callbackFlow {
                trySend("hello fish")
                close()
                awaitClose {

                }
            }.collect {
                println("collect:$it")
            }
        }
    }

    //末端操作符
    fun test6() {
        runBlocking {
            flow {
                emit("hello fish")
                emit("hello fish2")
            }.collectIndexed { index, value ->
                println("collect $index-->$value")
            }
        }
    }

    fun test7() {
        runBlocking {
            val list = mutableListOf<String>()
            flow {
                emit("hello fish")
                emit("hello fish2")
            }.toCollection(list)
            list.forEach {
                println(it)
            }
        }
    }

    fun test8() {
        runBlocking {
            val list = mutableListOf<String>()
            flow {
                emit("hello fish")
                emit("hello fish2")
            }.toList(list)
            list.forEach {
                println(it)
            }
        }
    }

    fun test9() {
        runBlocking {
            val set = mutableSetOf<String>()
            flow {
                emit("hello fish")
                emit("hello fish2")
                emit("hello fish")
            }.toSet(set)
            set.forEach {
                println(it)
            }
        }
    }

    fun test10() {
        runBlocking {
            val first = flowOf(1, 2, 3).first()
            println("collect:$first")

            val last = flowOf(1, 2, 3).last()
            println("collect:$last")

            val firstNull = flowOf(null, 1, 2, 3).firstOrNull()
            println("collect:$firstNull")

            val lastNull = flowOf(1, 2, 3).lastOrNull()
            println("collect:$lastNull")

            val firstPrediction = flowOf(1, 2, 3, 4).first {
                it % 2 == 0
            }
            println("collect:$firstPrediction")
        }
    }

    fun test11() {
        runBlocking {
            val single = flowOf(2).single()
            println("collect:$single")

            val singleNull = flowOf(2, null, null).singleOrNull()
            println("collect:$singleNull")
        }
    }

    fun test12() {
        runBlocking {
            val count = flowOf(1, 2, 3, 4).count()
            println("collect:$count")
        }
    }

    fun test13() {
        runBlocking {
            val reduce = flowOf(1, 2, 3).reduce { accumulator, value ->
                accumulator + value
            }
            println("collect:$reduce")

            val fold = flowOf(1, 2, 3).fold("hello") { accumulator, value ->
                accumulator + value
            }
            println("collect:$reduce $fold")
        }
    }

    fun test14() {
        runBlocking {
            flow {
                println("emit hello fish")
                emit("hello fish")
            }.onEach {
                println("each $it")
            }.onStart {
                println("onStart")
            }.onCompletion {
                println("onCompletion")
            }.filter {
                true
            }.collect {
                println("collect:$it")
            }
        }
    }

    fun test15() {
        runBlocking {
            flow {
                println("emit hello fish")
                emit("hello fish")
            }.filter {
                true
            }.filterNot {
                false
            }.filterNotNull().filterIsInstance<Int>()
                .collect {
                    println("collect:$it")
                }
        }
    }

    fun test16() {
        runBlocking {
            flow {
                println("emit hello fish")
                emit("hello fish")
                emit("hello fish2")
            }.transform {
                emit("$it 2223")
            }.transformWhile {
                emit("$it 4444")
                true
            }.collect {
                println("collect:$it")
            }
        }
    }

    fun test17() {
        runBlocking {
            flow {
                println("emit hello fish")
                emit("hello fish")
                emit("hello fish2")
            }.map {
                "fish map"
            }.mapNotNull {
                "null"
            }.collect {
                println("collect:$it")
            }
        }
    }

    fun test18() {
        GlobalScope.launch {
            flow {
                println("emit ${Thread.currentThread()}")
                emit("hello fish")
                emit("hello fish2")
            }.transformLatest {
                println("transform latest ${Thread.currentThread()}")
                delay(2000)
                emit(it)
                emit("go on")
            }.mapLatest {
                delay(1000)
                "$it map"
            }.collect {
                println("collect ${Thread.currentThread()}")
                println("collect:$it")
            }
        }
    }

    fun test19() {
        runBlocking {
            flow {
                println("emit ${Thread.currentThread()}")
                emit("hello fish")
                emit("hello fish2")
                delay(3000)
                emit("hello fish3")
            }.drop(0)
                .take(1)
                .collect {
                    println("collect ${Thread.currentThread()}")
                    println("collect:$it")
                }
        }
    }

    fun test20() {
        runBlocking {
            flow {
                emit("hello fish")
                emit("hello fish2")
                delay(3000)
                emit("hello fish3")
            }.debounce(1000)
                .collect {
                    println("collect:$it")
                }
        }
    }

    fun test21() {
        runBlocking {
            flow {
                emit("hello fish")
                delay(2000)
                emit("hello fish2")
                delay(2000)
                emit("hello fish3")
                delay(2000)
                emit("hello fish4")
            }.sample(1000)
                .collect {
                    println("collect:$it")
                }
        }
    }

    fun test22() {
        runBlocking {
            class Stu(
                val name: String? = null,
                val age: Int? = null,
            )

            flow {
                emit(Stu("fish", 12))
                emit(Stu("fish2", 13))
                emit(Stu("fish3", 14))
                emit(Stu("fish4", 14))
            }.distinctUntilChangedBy {
                it.age
            }.collect {
                println("collect:${it.name}")
            }

            flow {
                emit(1)
                emit(1)
                emit(2)
                emit(3)
            }.distinctUntilChanged()
                .collect {
                println("collect:${it}")
            }
        }
    }

    fun test23() {
        runBlocking {
            val time = measureTimeMillis {
                flow {
                    emit("hello fish")
                    delay(200)
                    emit("hello fish2")
                    delay(200)
                    emit("hello fish3")
                    delay(200)
                    emit("hello fish4")
                }.flowOn(Dispatchers.IO)
                    .buffer()
                    .collect {
                        delay(200)
                        println("collect:$it")
                    }
            }
            println("use time:$time")
        }
    }

    fun test24() {
        runBlocking {
            val time = measureTimeMillis {
                flow {
                    emit("hello fish")
                    emit("hello fish2")
                    emit("hello fish3")
                    emit("hello fish4")
                }.conflate()
                    .catch {

                    }
                    .collect {
                        delay(200)
                        println("collect:$it")
                    }
            }
            println("use time:$time")
        }
    }

    fun test25() {
        runBlocking {
            val time = measureTimeMillis {
                flow {
                    emit("hello fish")
                    1/0
                }.retry(2) {
                    false
                }
                    .collect {
                        println("collect:$it")
                    }
            }
            println("use time:$time")
        }
    }

    fun test26() {
        runBlocking {
            val flow1 = flowOf(1,2,3,4)
            val flow2 = flowOf("a", "b", "c")
            flow1.zip(flow2) {
                v1,v2->v1.toString() + v2
            }.collect {
                println("collect:$it")
            }
        }
    }

    fun test27() {
        runBlocking {
            val flow1 = flowOf(1,2,3,4)
            val flow2 = flowOf("a", "b", "c")
            flow1.combine(flow2) {
                    v1,v2->v1.toString() + v2
            }.collect {
                println("collect:$it")
            }
        }
    }

    fun test28() {
        runBlocking {
            flow {
                emit(1)
                emit(2)
                emit(3)
                emit(4)
                println("${Thread.currentThread()}")
            }
                .collectLatest {
                    println("${Thread.currentThread()}")
                    delay(200)
                println("collect:$it")
            }
        }
    }

    fun test29() {
        runBlocking {
            flow {
                emit(1)
                emit(2)
                emit(3)
                emit(4)
                println("${Thread.currentThread()}")
            }.flatMapConcat{
                flow {
                    emit("$it fish0")
                }
            }
                .collect {
                    println("${Thread.currentThread()}")
                    delay(200)
                    println("collect:$it")
                }
        }
    }

    fun test30() {
        runBlocking {
            flow {
                emit(1)
                emit(2)
                emit(3)
                emit(4)
                println("${Thread.currentThread()}")
            }.flatMapMerge{
                flow {
                    emit("$it fish0")
                }
            }
                .collect {
                    println("${Thread.currentThread()}")
                    delay(200)
                    println("collect:$it")
                }
        }
    }

    fun test31() {
        runBlocking {
            flow {
                emit(1)
                emit(2)
                emit(3)
                emit(4)
                println("${Thread.currentThread()}")
            }.flatMapLatest{
                flow {
                    delay(100)
                    emit("$it fish0")
                }
            }
                .collect {
                    println("${Thread.currentThread()}")
                    delay(200)
                    println("collect:$it")
                }
        }
    }


    fun test00() {
        runBlocking {
            val result = mutableListOf<String>()
            //构造flow
            val flow = flow {
                //上游
                emit("hello world ${Thread.currentThread()}")
            }
            //收集flow
            flow.collect {
                //下游
                println("collect:$it ${Thread.currentThread()}")
                result.add(it)
            }
        }
    }

    fun test01() {
        runBlocking {
            val result = mutableListOf<String>()
            flow {
                //上游
                emit("hello world ${Thread.currentThread()}")
            }.toList(result)
        }
    }

    fun test02() {
        runBlocking {
            flow {
                //上游
                emit("hello world ${Thread.currentThread()}")
            }.transform {
                emit("$it 1")
            }.transform {
                emit("$it 2")
            }.transform {
                emit("$it 3")
            }.collect {
                println("$it")
            }
        }
    }

    fun test03() {
        runBlocking {
            flow {
                //上游
                emit("hello world ${Thread.currentThread()}")
            }.map {
                "$it 1"
            }.collect {
                println("$it")
            }
        }
    }

    fun test04() {
        runBlocking {
            flow {
                //上游
                emit("hello world ${Thread.currentThread()}")
                emit("fish")
            }.filter {
                //包含hello字符串才继续往下发送
                it.contains("hello")
            }.collect {
                println("$it")
            }
        }
    }

    fun test05() {
        runBlocking {
            flow {
                //上游
                println("emit ${Thread.currentThread()}")
                emit("hello world")
            }.flowOn(Dispatchers.IO)//flowOn 之前的操作符在新协程里执行
                .collect {
                    println("$it")
                    println("collect ${Thread.currentThread()}")
                }
        }
    }

    fun test06() {
        runBlocking {
            val time = measureTimeMillis {
                flow {
                    //上游
                    println("emit ${Thread.currentThread()}")
                    emit("hello world")
                    delay(1000)
                    emit("hello world2")
                }.buffer().collect {
                        delay(2000)
                        println("$it")
                        println("collect ${Thread.currentThread()}")
                    }
            }
            println("use time:$time")
        }
    }

    fun test07() {
        runBlocking {
            flow {
                //上游
                repeat(5) {
                    emit("emit $it")
                    delay(100)
                }
            }.conflate().collect {
                delay(500)
                println("$it")
            }
        }
    }

    fun test08() {
        runBlocking {
            flow {
                //上游
                repeat(5) {
                    emit("emit $it")
                }
                println("emit ${Thread.currentThread()}")
            }.transformLatest {
                delay(200)
                emit("$it fish")
            }.collect {
                println("collect ${Thread.currentThread()}")
                println("$it")
            }
        }
    }

    fun test09() {
        runBlocking {
            flow {
                //上游
                repeat(5) {
                    emit("emit $it")
                }
                println("emit ${Thread.currentThread()}")
            }.mapLatest {
                delay(200)
                "$it fish"
            }.collect {
                println("collect ${Thread.currentThread()}")
                println("$it")
            }
        }
    }

    fun test010() {
        runBlocking {
            val flow1 = flow {
                emit("stuInfo")
            }
            flow1.flatMapConcat {
                //flow2
                flow {
                    emit("$it teachInfo")
                }
            }.collect {
                println("collect $it")
            }
        }
    }

    fun test011() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = flow {
                    println("emit ${Thread.currentThread()}")
                    emit("stuInfo 1")
                    emit("stuInfo 2")
                    emit("stuInfo 3")
                }
                flow1.flatMapConcat {
                    //flow2
                    flow {
                        println("flatMapConcat ${Thread.currentThread()}")
                        emit("$it teachInfo")
                        delay(1000)
                    }
                }.collect {
                    println("collect ${Thread.currentThread()}")
                    println("collect $it")
                }
            }
            println("use time:$time")
        }
    }

    fun test012() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = flow {
                    println("emit ${Thread.currentThread()}")
                    emit("stuInfo 1")
                    emit("stuInfo 2")
                    emit("stuInfo 3")
                }
                flow1.flatMapMerge(4) {
                    //flow2
                    flow {
                        println("flatMapMerge ${Thread.currentThread()}")
                        emit("$it teachInfo")
                        delay(1000)
                    }
                }.collect {
                    println("collect ${Thread.currentThread()}")
                    println("collect $it")
                }
            }
            println("use time:$time")
        }
    }

    fun test013() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = flow {
//                    println("emit ${Thread.currentThread()}")
                    emit("stuInfo 1")
                    emit("stuInfo 2")
                    emit("stuInfo 3")
                }
                flow1.flatMapLatest {
                    //flow2
                    flow {
//                        println("flatMapLatest ${Thread.currentThread()}")
                        delay(1000)
                        emit("$it teachInfo")
                    }
                }.collect {
//                    println("collect ${Thread.currentThread()}")
                    println("collect $it")
                }
            }
            println("use time:$time")
        }
    }


    fun test014() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = flow {
                    repeat(100) {
                        emit(it + 1)
                    }
                }
                flow1.collectLatest {
                    delay(20)
                    println("collect progress $it")
                }
            }
            println("use time:$time")
        }
    }

    fun test015() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = flow {
                    emit("stuSex 1")
                    emit("stuSex 2")
                    emit("stuSex 3")
                }
                val flow2 = flow {
                    emit("stuSubject")
                }
                flow1.combine(flow2) {
                    sex, subject->"$sex-->$subject"
                }.collect {
                    println(it)
                }
            }
            println("use time:$time")
        }
    }

    fun test016() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = flow {
                    emit("a")
                    emit("b")
                    emit("c")
                    emit("d")
                }
                val flow2 = flow {
                    emit("1")
                    emit("2")
                }
                flow1.combine(flow2) {
                        sex, subject->"$sex-->$subject"
                }.collect {
                    println(it)
                }
            }
            println("use time:$time")
        }
    }

    fun test017() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = flow {
                    emit("a")
                    emit("b")
                    emit("c")
                    emit("d")
                }
                val flow2 = flow {
                    emit("1")
                    emit("2")
                }
                flow1.zip(flow2) {
                        sex, subject->"$sex-->$subject"
                }.collect {
                    println(it)
                }
            }
            println("use time:$time")
        }
    }
}


fun main() = runBlocking<Unit> {
    FlowOperandAll().test017()
    Thread.sleep(200000)
}