package com.fish.kotlindemo.coroutineraw

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun startLaunch() {
    GlobalScope.launch(Dispatchers.Main) {
        println("launching...")
    }
}