package com.fish.kotlindemo.repository

import android.app.Application
import com.fish.kotlindemo.app.MyApp
import com.fish.kotlindemo.app.scope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class MyRepo {
    companion object {
        var scope:CoroutineScope? = null
        var app:Application ?= null
    }
    suspend fun getInfo():String {
        delay(2000)
        getId()
//        app?.scope?.launch {
//            delay(6000)
//            println("shit")
//        }?.join()
//        app?.scope?.coroutineContext?.let {
//            withContext(it) {
//                delay(6000)
//                println("shit")
//            }
//            println("fuck world")
//        }
        println("fuck world2")
        return "hello"
    }

    fun bind(app1:Application) {
        app = app1
    }

    suspend fun getId() {
        app?.scope?.launch {
            delay(6000)
            println("shit")
        }?.join()
        println("fuck world")
    }

    fun getFlow(): Flow<String> {
        return flow {
            delay(2000)
            emit("hello world")
        }.map {
            "$it 0000"
        }
    }
}