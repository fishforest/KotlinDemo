package com.fish.kotlindemo.lifecycleAndCoroutine

import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MyVM: ViewModel() {
    fun getInfo() {
        viewModelScope.launch {
            delay(5000)
            println("viewModel协程还在运行中")
        }
    }
}