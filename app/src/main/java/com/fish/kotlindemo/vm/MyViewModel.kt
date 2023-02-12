package com.fish.kotlindemo.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fish.kotlindemo.repository.MyRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel() : ViewModel() {

    private val _liveData = MutableLiveData<String>()

    val livedata:LiveData<String>
    get() = _liveData

    val repo = MyRepo()
    fun getInfo(block:(String)->Unit) {
        viewModelScope.launch {
            val result = repo.getInfo()
            withContext(Dispatchers.Main) {
                block(result)
            }

            _liveData.postValue("hello world")
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("hello")
    }
}