package com.fish.kotlindemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fish.kotlindemo.databinding.ActivitySecondBinding
import com.fish.kotlindemo.lifecycleAndCoroutine.MyFlow
import com.fish.kotlindemo.vm.MyViewModel2
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.Exception

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartLifecycle.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    delay(5000)
                } catch (e : Exception) {
                    println(e.localizedMessage)
                }
                println("hello world")
            }

//            val vm = MyViewModel2()
//            vm.test()
//            vm.livedata3.observe(this) {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            }
//            vm.update()

        }

        lifecycleScope.launchWhenResumed {
            MyFlow().flow.collect {
                println("collect when $it")
            }


//            println("fuck...")
//            lifecycleScope.launch(Dispatchers.IO) {
//                var count = 0
//                while (true) {
//                    delay(1000)
//                    println("collect ${count++}")
//                }
//            }
//            println("fuck2...")


        }

//        val dialogFragment = DialogFragment()
//        dialogFragment.show(supportFragmentManager, "hello")

        println("111")
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                MyFlow().flow.collect {
                    println("collect repeat $it")
                }
                MyFlow().flow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            }
            println("222")

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

    object Fuck:JJ()
}

open class JJ {
    fun lishi(){}
}

sealed class MainState {

    object Idle : JJ()
    object Loading : MainState()
    data class Users(val user: List<String>) : MainState()
    data class Error(val error: String) : MainState()
}