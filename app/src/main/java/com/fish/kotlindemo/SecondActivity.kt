package com.fish.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.fish.kotlindemo.databinding.ActivityMainBinding
import com.fish.kotlindemo.repository.MyRepo
import com.fish.kotlindemo.service.MyService
import com.fish.kotlindemo.vm.MyViewModel
import com.fish.kotlindemo.vm.MyViewModel2
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val data = "fish"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = ViewModelProvider(this).get(MyViewModel::class.java)

        vm.getInfo{
            Toast.makeText(this@SecondActivity, it, Toast.LENGTH_SHORT).show()
        }

        vm.livedata.observe(this) {value->
            Toast.makeText(this@SecondActivity, value, Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launchWhenResumed {
            delay(5000)
            println("1====$data")
        }

        MainScope().launch {
            delay(5000)
            println("2====$data")

        }

        val scope = CoroutineScope(SupervisorJob() + CoroutineName("shit"))
        scope.launch {
            println("shit")
        }

        val vm2 = ViewModelProvider(this)[MyViewModel2::class.java]
        vm2.test()

        startService(Intent(this@SecondActivity, MyService::class.java))

        lifecycleScope.launchWhenResumed {
            println("hello world")
            MyRepo().getFlow().collectLatest {
                println("$it")
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}