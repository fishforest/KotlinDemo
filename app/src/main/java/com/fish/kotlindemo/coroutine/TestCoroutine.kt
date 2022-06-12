package com.fish.kotlindemo.coroutine

import android.os.Looper
import com.fish.kotlindemo.funclass.testUpFun1
import com.fish.kotlindemo.varclass.name
import com.fish.kotlindemo.varclass.test
import kotlinx.coroutines.*
import kotlinx.coroutines.intrinsics.startCoroutineCancellable
import kotlin.concurrent.thread
import kotlin.coroutines.*

class TestCoroutine {
    fun test() {
        println("start block")
        runBlocking {
//            delay(10000)
            println("blocking")
//            test1()
            var defer = async {
                println("jj")
            }
//┳┏②③④⑶⒔⑴⑴⑴⑴22②②⑵⑵⑵🀄︎
            defer.await()
            defer.cancel()
            println("jj me")
            println("after")
        }
        println("end bock")
        thread {
            Thread.sleep(1000000)
        }
    }

    fun test1() {
        println("launch start:${Thread.currentThread()}")
        var job = GlobalScope.launch(Dispatchers.IO) {
            println("launching before :${Thread.currentThread()}")
            withContext(Dispatchers.Default) {
                println("with context: : ${Thread.currentThread()}")
                withContext(Dispatchers.Default) {
                    println("with context2: : ${Thread.currentThread()}")
                    withContext(Dispatchers.Default) {
                        println("with context3: : ${Thread.currentThread()}")
                        withContext(Dispatchers.Default) {
                            println("with context4: : ${Thread.currentThread()}")
                            withContext(Dispatchers.Default) {
                                println("with context5: : ${Thread.currentThread()}")
                                withContext(Dispatchers.Default) {
                                    println("with context6: : ${Thread.currentThread()}")
                                    withContext(Dispatchers.Default) {
                                        println("with context7: : ${Thread.currentThread()}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            println("launching after:${Thread.currentThread()}")
        }
        println("launch end ${Thread.currentThread()}")
        thread {
            Thread.sleep(1000000)
        }
    }
}

fun test(block: (Int) -> String) {
    block.invoke(3)
}

fun test2() {
    GlobalScope.launch() {
        fuck()
        println("${Thread.currentThread()}")
    }

    Thread.sleep(100000)
}

fun fuck() {
    println("fuck begin")
    Thread.sleep(1000)
    println("fuck end")
}

fun <T> launchMe(block: suspend () -> T) {
    var coroutine = block.createCoroutine(object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("result:$result")
        }
    })
    coroutine.resume(Unit)
}

suspend fun loadDataFuck():String {
    var tt = suspendCoroutine<String> { continuation ->
//        thread {
//            println("start thread:${Thread.currentThread()}")
//            Thread.sleep(2000)
//            println("after thread")
//            continuation.resume("ok3333")
//        }
        continuation.resume("ok3333")
        println("after thread2:${Thread.currentThread()}")
    }

    return tt
}

suspend inline fun loadData2() {
    thread {

    }
    name
}

fun test3() {
    GlobalScope.launch(Dispatchers.Default) {
        println("delay before ${Thread.currentThread()}")
        delay(2000)
        println("delay after ${Thread.currentThread()}")
    }
//
    Thread.sleep(300)
    GlobalScope.launch(Dispatchers.Default) {
        println("delay before2 ${Thread.currentThread()}")
        while (true) {

        }
    }

    GlobalScope.launch(Dispatchers.Default) {
        println("delay before3 ${Thread.currentThread()}")
        while (true) {

        }
    }

//    GlobalScope.launch(Dispatchers.IO) {
//        println("delay before1 ${Thread.currentThread()}")
//        delay(300000)
//        println("delay after1 ${Thread.currentThread()}")
//    }
//
//    GlobalScope.launch(Dispatchers.Default) {
//        println("delay before2 ${Thread.currentThread()}")
//        delay(3000000)
//        println("delay after2 ${Thread.currentThread()}")
//    }
}

fun test4() {
    GlobalScope.launch {
        var jj = getData("flush")
        println("jj $jj")
    }
}

suspend fun getData(url: String): String = withContext(Dispatchers.IO) {
    println("url:$url")
    delay(1000)
    "jjj"
}


fun main(args: Array<String>) {
    var testCoroutine = TestCoroutine()
//    testCoroutine.test()
//    testCoroutine.test1()

////    test()
//    test4()
//    test2()
//    test3()

    launchMe {
        println("before suspend")
        var tt = loadDataFuck()
        println("after suspend tt:$tt")
    }
//
//    var coroutineJJ = CoroutineJJ()
//    coroutineJJ.test2()

    thread {
        Thread.sleep(1000000)
    }

}


