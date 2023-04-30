package com.fish.kotlindemo.flowoperand

import androidx.lifecycle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LiveDataDemo {
    val _liveData1 = MutableLiveData(false)
    val livedata1 : LiveData<Boolean>
    get() = _liveData1

    fun test0() {
        val mediaLiveData = MediatorLiveData<String>()

//        mediaLiveData.observeForever {
//            println("find something")
//        }

        mediaLiveData.addSource(_liveData1) {
            println("media add")
            mediaLiveData.value = "hello"
            _liveData1.value = true
        }

        val jj = _liveData1.switchMap {
            val ld = MutableLiveData<String>()
            ld.value = "你好"
            ld
        }

        _liveData1.map {
            "ddd"
        }

        jj.observeForever {
            println(it)
        }

        GlobalScope.launch {
            _liveData1.postValue(true)
        }
    }
}

fun main() = runBlocking<Unit> {
    LiveDataDemo().test0()
    Thread.sleep(200000)
}