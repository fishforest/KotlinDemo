package com.fish.kotlindemo.lifecycleAndCoroutine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fish.kotlindemo.databinding.ActivityThirdBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //测试页面关闭后的打印
        binding.btnStartLifecycle.setOnClickListener {
            thread {
                //模拟网络获取数据
                Thread.sleep(5000)
                runOnUiThread {
                    //线程持有Activity实例
                    Toast.makeText(this@ThirdActivity, "hello world", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //测试Activity stop状态下的打印
        binding.btnStartGetInfo.setOnClickListener {
            thread {
                //模拟获取数据
                var count = 0
                while (true) {
                    Thread.sleep(2000)
                    runOnUiThread {
                        binding.count.text = "计算值:${count++}"
                        println("${binding.count.text}")
                    }
                }
            }
        }

        //测试没有生命周期的协程
        val scope = CoroutineScope(Job())
        binding.btnStartUnlifecyleCoroutine.setOnClickListener {
            scope.launch {
                delay(5000)
                scope.launch(Dispatchers.Main) {
                    Toast.makeText(this@ThirdActivity, "协程还在运行中", Toast.LENGTH_SHORT).show()
                }
                println("协程还在运行中")
            }
        }

        //测试页面关闭后的协程
        binding.btnStartWithlifecyleCoroutine.setOnClickListener {
            lifecycleScope.launch {
                delay(5000)
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(this@ThirdActivity, "协程还在运行中", Toast.LENGTH_SHORT).show()
                }
                println("协程还在运行中")
            }
        }

        //测试Activity stop状态下的协程
        binding.btnStartPauseLifecyleCoroutine.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                delay(5000)
                lifecycleScope.launch(Dispatchers.Main) {
                    Toast.makeText(this@ThirdActivity, "协程还在运行中", Toast.LENGTH_SHORT).show()
                }
                println("协程还在运行中")
            }
        }

        //测试ViewModel使用协程
        binding.btnStartLifecycleVm.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                val viewModel = MyVM()
                viewModel.getInfo()
            }
        }

        //测试flow使用when
        binding.btnStartLifecycleFlowWhen.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                MyFlow().flow3.collect {
                    println("flow when x coming $it")
                }
            }
        }

        //测试flow使用repeat
        binding.btnStartLifecycleFlowRepeat.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    MyFlow().flow3.collect {
                        println("flow repeat x coming $it")
                    }
                }
            }
        }
    }
}