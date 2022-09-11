package com.fish.kotlindemo.select

import android.graphics.Bitmap
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.selects.selectUnbiased

class SelectDemo {

    //从zxing 获取二维码信息
    suspend fun getQrcodeInfoFromZxing(bitmap: Bitmap?): String {
        //模拟耗时
        delay(2000)
        return "I'm fish"
    }

    //从zbar 获取二维码信息
    suspend fun getQrcodeInfoFromZbar(bitmap: Bitmap?): String {
        delay(1000)
        return "I'm fish"
    }

    fun testSelect() {
        runBlocking {
            var bitmap = null
            var starTime = System.currentTimeMillis()
            var qrcoe1 = getQrcodeInfoFromZxing(bitmap)
            var qrcode2 = getQrcodeInfoFromZbar(bitmap)
            println("qrcode1=$qrcoe1 qrcode2=$qrcode2 useTime:${System.currentTimeMillis() - starTime} ms")
        }
    }

    fun testSelect1() {
        var bitmap = null;
        var starTime = System.currentTimeMillis()
        var deferredZxing = GlobalScope.async {
            getQrcodeInfoFromZxing(bitmap)
        }

        var deferredZbar = GlobalScope.async {
            getQrcodeInfoFromZbar(bitmap)
        }

        runBlocking {
            //挂起等待识别结果
            var qrcoe1 = deferredZxing.await()
            //挂起等待识别结果
            var qrcode2 = deferredZbar.await()
            println("qrcode1=$qrcoe1 qrcode2=$qrcode2 useTime:${System.currentTimeMillis() - starTime} ms")
        }
    }

    fun testSelect2() {
        var bitmap = null;
        var starTime = System.currentTimeMillis()
        var deferredZxing = GlobalScope.async {
            getQrcodeInfoFromZxing(bitmap)
        }

        var deferredZbar = GlobalScope.async {
            getQrcodeInfoFromZbar(bitmap)
        }

        var isEnd = false
        var result: String? = null
        GlobalScope.launch {
            if (!isEnd) {
                //没有结束，则继续识别
                var resultTmp = deferredZxing.await()
                if (!isEnd) {
                    //识别没有结束，说明自己是第一个返回结果的
                    result = resultTmp
                    println("zxing recognize ok useTime:${System.currentTimeMillis() - starTime} ms")
                    isEnd = true
                }
            }
        }

        GlobalScope.launch {
            if (!isEnd) {
                var resultTmp = deferredZbar.await()
                if (!isEnd) {
                    //识别没有结束，说明自己是第一个返回结果的
                    result = resultTmp
                    println("zbar recognize ok useTime:${System.currentTimeMillis() - starTime} ms")
                    isEnd = true
                }
            }
        }

        //检测是否有结果返回
        runBlocking {
            while (!isEnd) {
                delay(1)
            }
            println("recognize result:$result")
        }
    }

    fun testSelect3() {
        var bitmap = null;
        var starTime = System.currentTimeMillis()
        var deferredZxing = GlobalScope.async {
            getQrcodeInfoFromZxing(bitmap)
        }
        var deferredZbar = GlobalScope.async {
            getQrcodeInfoFromZbar(bitmap)
        }
        runBlocking {
            //通过select 监听zxing、zbar 结果返回
            var result = select<String> {
                //监听zxing
                deferredZxing.onAwait { value ->
                    //value 为deferredZxing 识别的结果
                    "zxing result $value"
                }

                //监听zbar
                deferredZbar.onAwait { value ->
                    "zbar result $value"
                }
            }

            //运行到此，说明已经有结果返回
            println("result from $result useTime:${System.currentTimeMillis() - starTime}")
        }
    }

    fun testSelect4() {
        runBlocking {
            var bitmap = null;
            var starTime = System.currentTimeMillis()
            var receiveChannelZxing = produce {
                //生产数据
                var result = getQrcodeInfoFromZxing(bitmap)
                //发送数据
                send(result)
            }

            var receiveChannelZbar = produce {
                var result = getQrcodeInfoFromZbar(bitmap)
                send(result)
            }

            var result = select<String> {
                //监听是否有数据发送过来
                receiveChannelZxing.onReceive {

                        value ->
                    "zxing result $value"

                }

                receiveChannelZbar.onReceive {

                        value ->
                    "zbar result $value"

                }
            }

            println("result from $result useTime:${System.currentTimeMillis() - starTime}")
        }
    }

    operator fun invoke(block: (Int) -> String): String {
        return block(3)
    }

    fun testSelect5() {
        runBlocking {
            var starTime = System.currentTimeMillis()
            var receiveChannelZxing = produce {
                //发送数据
                send("I'm fish")
            }

            //确保channel 数据已经send
            delay(1000)
            var result = select<String> {
                //监听是否有数据发送过来
                receiveChannelZxing.onReceive { value ->
                    "zxing result $value"
                }
            }
            println("result from $result useTime:${System.currentTimeMillis() - starTime}")
        }
    }

    fun testSelect6() {
        runBlocking {
            var starTime = System.currentTimeMillis()
            var receiveChannelZxing = produce {
                //发送数据
                send("I'm fish")
            }

            //确保channel 数据已经send
            delay(1000)
            var result = selectUnbiased<String> {
                //监听是否有数据发送过来
                receiveChannelZxing.onReceive { value ->
                    "zxing result $value"
                }
            }
            println("result from $result useTime:${System.currentTimeMillis() - starTime}")
        }
    }
}

fun main(args: Array<String>) {
    var selectDemo = SelectDemo()
    selectDemo.testSelect4()
//    var result = selectDemo { age ->
//        when (age) {
//            3 -> "I'm fish3"
//            4 -> "I'm fish4"
//            else -> "error"
//        }
//    }
//    println("result：$result")

    Thread.sleep(100000)
}