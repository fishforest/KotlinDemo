package com.fish.kotlindemo.datastore

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.core.*
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.fish.kotlindemo.test.LoginInfo
import com.fish.kotlindemo.varclass.myName
import kotlinx.coroutines.flow.*
import java.util.*

class MyDataStore(val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(MyDataStore.javaClass.name)

        val Context.dataProto: DataStore<LoginInfo> by dataStore(
            //文件名，存储在/data/data/包名/files/datastore下
            fileName = "login_info",
            //指定序列化器，负责将对象序列化/反序列化-到/从文件
            serializer = LoginInfoSerializer
        )
    }

    private var sp: SharedPreferences? = null

    init {
        sp = context.getSharedPreferences("mysp", Context.MODE_PRIVATE)
    }

//    val myNameKey = stringPreferencesKey("name")
//    val myAgeKey = stringPreferencesKey("age")

    suspend fun getData() {
        context.dataStore.data.collect {
            it.asMap().forEach {
                val vaule = it.value as String
            }
        }
    }

    suspend fun updateDataStore(userName: String, userId:Long) {
        context.dataProto.updateData { loginInfo ->
            //loginInfo为生成的类的对象
            loginInfo.toBuilder()
                    //给字段赋值
                .setUserName(userName)
                .setUserId(userId)
                    //返回LoginInfo
                .build()
        }
    }

    suspend fun observe() {
        context.dataProto.data.map {
            //it 指代LoginInfo对象
            "${it.userId}==${it.userName}"
        }.collect {
            println("data:$it")
        }
    }

    fun saveSP() {
        sp?.edit {
            putString("name", "fish${Random().nextInt(10000)}")
            putInt("age", 19)
        }
    }

    fun getSP() {
        val name = sp?.getString("name", "test")
        val age = sp?.getString("age", "test")
    }

    suspend fun putNameStringData(name: String, age:Int) {
        context.dataStore.edit {
            it[myNameKey] = name
            it[myAgeKey] = age
        }
    }

    val myNameKey = stringPreferencesKey("name")
    val myAgeKey = intPreferencesKey("age")
    suspend fun saveData() {
        context.dataStore.edit {
            //给不同的key赋值
            it[myNameKey] = "fish"
            it[myAgeKey] = 14
        }
    }

    val myScoreKey = floatPreferencesKey("score")
    suspend fun queryDataV2() {
        context.dataStore.data.map {
//            val dd = it[myScoreKey] as String
        }.collect {
            println("$it")
        }
    }

    suspend fun saveData2() {
        context.dataStore.edit {
            //只修改分数
            it[myNameKey] = "${Random().nextInt(10000)}"
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