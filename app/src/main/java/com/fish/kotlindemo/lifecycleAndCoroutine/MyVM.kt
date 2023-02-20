package com.fish.kotlindemo.lifecycleAndCoroutine

import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread


class MyVM: ViewModel() {
    fun getInfo() {
        viewModelScope.launch {
            //模拟网络请求
            delay(4000)
            liveData.postValue("hello world")
            println("hello world")
        }
    }

    val liveData = MutableLiveData<String>()
    fun getStuInfo() {
        thread {
            //模拟网络请求
            Thread.sleep(2000)
            liveData.postValue("hello world")
        }
    }

    val scope = CoroutineScope(Job())
    fun getStuInfoV2() {
        scope.launch {
            //模拟网络请求
            delay(4000)
            liveData.postValue("hello world")
            println("hello world")
        }
    }
}