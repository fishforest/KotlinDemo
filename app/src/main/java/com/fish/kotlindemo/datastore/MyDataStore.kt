package com.fish.kotlindemo.datastore

import android.content.Context
import androidx.datastore.core.*
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*

class MyDataStore(val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(MyDataStore.javaClass.name)
    }

//    val myNameKey = stringPreferencesKey("name")
//    val myAgeKey = stringPreferencesKey("age")

    suspend fun getNameStringData(callback:(String, String)->Unit) {
        context.dataStore.data.collect {
            println("single ${it.toMutablePreferences()[myNameKey]}")
            println("single2 ${it[myAgeKey]}")

            it.asMap().forEach {
                println("${it.key.name}, ${it.value}")
//                callback.invoke(it.key.name, it.value as String)
            }
        }
    }

    suspend fun putNameStringData(value : String) {
        context.dataStore.edit {
            it[myNameKey] = value
        }
    }

    val myNameKey = stringPreferencesKey("name")
    val myAgeKey = intPreferencesKey("age")
    suspend fun saveData() {
        context.dataStore.edit {
            //给不同的key赋值
            it[myNameKey] = "fish"
            it[myAgeKey] = 13
        }
    }

    val myScoreKey = floatPreferencesKey("score")
    suspend fun queryDataV2() {
        context.dataStore.data.map {
            //只关心分数的变化
            it[myScoreKey]
        }.collect {
            println("$it")
        }
    }
    suspend fun saveData2() {
        context.dataStore.edit {
            //只修改分数
            it[myNameKey] = "fish is perfect3"
        }
    }

    suspend fun queryData() {
        context.dataStore.data.collect {
            it.asMap().forEach {
                println("${it.key.name}, ${it.value}")
            }
        }

        println("dataStore flow end")
    }

    suspend fun queryData2() {
        val flow = flow {
            emit("hello")
        }

        flow.collect {
            println(it)
        }

        println("normal flow end")
    }
}