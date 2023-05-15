package com.fish.kotlindemo.lifecycleAndCoroutine

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.fish.kotlindemo.app.scope
import com.fish.kotlindemo.databinding.ActivityThirdBinding
import com.fish.kotlindemo.fragment.dialogFragment.FishFragmentActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlin.concurrent.thread

fun yellow() {

}
const val jj = 0
val fuck = 0
class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //监听数据变化
        val vm  by viewModels<MyVM>()
        vm.liveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        vm.getInfo()


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
                    Thread.sleep(5000)
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

            startActivity(Intent(this@ThirdActivity, FishFragmentActivity::class.java))
        }

        //测试flow使用when
        binding.btnStartLifecycleFlowWhen.setOnClickListener {
            lifecycleScope.launchWhenResumed {
                MyFlow().flow.collect {
                    println("collect when $it")
                }
            }
        }

        //测试flow使用repeat
        binding.btnStartLifecycleFlowRepeat.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    MyFlow().flow.collect {
                        println("collect repeat $it")
                    }
                }
                println("repeatOnLifecycle over")

                MyFlow().flow.flowWithLifecycle(this@ThirdActivity.lifecycle, Lifecycle.State.RESUMED)
                    .collectLatest {
                        println("collect repeat $it")
                    }
            }
        }

        //全局协程的使用
        binding.btnStartLifecycleApp.setOnClickListener {
            application.scope.launch {
                delay(5000)
                println("协程在全局状态运行1")
            }

            GlobalScope.launch {
                delay(5000)
                println("协程在全局状态运行2")
            }

            ProcessLifecycleOwner.get().lifecycleScope.launch {
                delay(5000)
                println("协程在全局状态运行3")
            }
        }

        //liveData
        binding.btnStartLifecycleLivedata.setOnClickListener {
            vm.liveData.observe(this) {
                //接收数据
                println("hello world")
            }
            vm.getInfo()
        }

        var multiIoCount = 1
        binding.btnStartThreadMultiIo.setOnClickListener {
            repeat(10) {
                GlobalScope.launch(Dispatchers.IO) {
                    println("io multi...${multiIoCount++}  ${Thread.currentThread().name}")
                    Thread.sleep(10000)
                }
            }
        }

        var singleIoCount = 1
        binding.btnStartThreadSingleIo.setOnClickListener {
            repeat(1) {
                GlobalScope.launch(Dispatchers.IO) {
                    println("io single...${singleIoCount++}  ${Thread.currentThread().name}")
                    Thread.sleep(10000)
                }
            }
        }

        var multiCpuCount = 1
        binding.btnStartThreadMultiCpu.setOnClickListener {
            repeat(8) {
                GlobalScope.launch(Dispatchers.Default) {
                    println("cpu multi...${multiCpuCount++}  ${Thread.currentThread().name}")
                    Thread.sleep(36000000)
                }
            }
        }

        var singleCpuCount = 1
        binding.btnStartThreadSingleCpu.setOnClickListener {
            repeat(1) {
                GlobalScope.launch(Dispatchers.Default) {
                    println("cpu single...${singleCpuCount++}  ${Thread.currentThread().name}")
                    Thread.sleep(36000000)
                }
            }
        }
    }
}