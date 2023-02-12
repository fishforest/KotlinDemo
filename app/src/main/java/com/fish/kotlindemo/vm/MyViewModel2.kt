package com.fish.kotlindemo.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch

class MyViewModel2 : ViewModel() {
    fun test() {
        viewModelScope.launch {
            flow {
                emit("hello")
            }.transform {
                emit("jj")
            }.mapLatest {

            }
        }
    }
}