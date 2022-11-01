package com.fish.kotlindemo.sequence

import kotlin.system.measureTimeMillis

class SequenceDemo {
    fun testCollection() {
        var time = measureTimeMillis {
            var list = (0..10000000).filter {
                it % 2 == 0
            }.filter {
                it > 1000
            }
            println("kotlin collection list size:${list.size}")
        }
        println("kotlin collection use time:$time")
    }

    fun testSequence() {
        var time =  measureTimeMillis {
            val count =

                (0..10000000)
                .asSequence()//转换为sequence
                .filter {
                    it % 2 == 0//过滤偶数
                }.filter {
                    it > 1000//过滤>1000
                }
                .count() //统计个数


            println("kotlin sequence list size:${count}")
        }
        println("kotlin sequence use time:$time")
    }

    fun testSequence1() {
        var time =  measureTimeMillis {
            val count = (0..10000000)
                .asSequence()//转换为sequence
                .filter {
                    it % 2 == 0//过滤偶数
                }.take(10).count()
            println("kotlin sequence1 list size:${count}")
        }
        println("kotlin sequence1 use time:$time")
    }

//
//    fun simple(): Sequence<Int> = sequence { // sequence builder
//        for (i in 1..3) {
//            Thread.sleep(100) // pretend we are computing it
//            yield(i) // yield next value
//        }
//    }
//
//    fun main2() {
//        simple().forEach { value -> println(value) }
//    }
}

fun main(args: Array<String>) {
    var demo = SequenceDemo()
    demo.testCollection()
    demo.testSequence()
    Thread.sleep(1000000)

}