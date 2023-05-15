package com.fish.kotlindemo.vm

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch

class MyViewModel2 : ViewModel() {

    val livedata1 = MediatorLiveData<String>()
    private val livedata2 = MutableLiveData<String>()

    val livedata3:LiveData<String> = livedata2.switchMap {
        val md = getFlow().asLiveData(viewModelScope.coroutineContext)
        md
    }.map {
        "helloxxx $it"
    }

    fun test() {
        viewModelScope.launch {
            flow {
                emit("hello")
            }.transform {
                emit("jj")
            }.mapLatest {

            }
        }

        livedata1.addSource(livedata2) {
            println("livedata $it")
            livedata1.value = it
        }
    }

    fun getFlow():Flow<String> {
        return flow {
            emit("I'm flow")
        }
    }

    fun update() {
        livedata2.value = "fuck"
    }

    fun testLife() {
        viewModelScope.launch {
            println("fragment continue1")
            delay(5000)
            println("fragment continue2")
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}