package com.fish.kotlindemo.app

import android.app.Application
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.*
import com.fish.kotlindemo.repository.MyRepo
import kotlinx.coroutines.*


val Application.scope: CoroutineScope
get() {
    return CoroutineScope(SupervisorJob() + Dispatchers.IO)
}


class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        MyRepo().bind(this)

//        System.setProperty("kotlinx.coroutines.scheduler.core.pool.size", "20")
//
//        System.setProperty("kotlinx.coroutines.io.parallelism", "40")


        ProcessLifecycleOwner.get().lifecycleScope.launch {
            delay(1000)
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                println("foreground")
                checkPermission()
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
                println("background")
            }
        })
    }

    fun checkPermission() {
        val notify = NotificationManagerCompat.from(this).areNotificationsEnabled();
        println("notify:$notify")
    }
}


