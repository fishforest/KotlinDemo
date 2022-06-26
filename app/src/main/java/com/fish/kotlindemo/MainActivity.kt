package com.fish.kotlindemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fish.kotlindemo.coroutinedispatch.CoroutineDispatch
import com.fish.kotlindemo.coroutineraw.startLaunch
import com.fish.kotlindemo.coroutinestory.JavaStudent
import com.fish.kotlindemo.coroutinestory.StudentCoroutine
import com.fish.kotlindemo.coroutinestory.StudentInfo
import com.fish.kotlindemo.coroutinestory.TeacherInfo
import com.fish.kotlindemo.coroutinesuspend.getStuInfo
import com.fish.kotlindemo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.btnJava.setOnClickListener {
            var javaStudent = JavaStudent()
//            var studentInfo = javaStudent.getWithoutThread(999)
//            Toast.makeText(this, "学生姓名：${studentInfo?.name ?:"空"}", Toast.LENGTH_LONG).show()
//            javaStudent.getStuInfoAsync(999) {
//                runOnUiThread {
//                    Toast.makeText(this, "学生姓名：${it?.name ?: "空"}", Toast.LENGTH_LONG).show()
//                }
//            }

            javaStudent.getTeachInfoAsync(999, object : JavaStudent.Callback {
                override fun onCallback(teacherInfo: TeacherInfo?) {
                    runOnUiThread {
                        Toast.makeText(binding.root.context,
                            "老师姓名：${teacherInfo?.name ?: "空"}",
                            Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCallback(studentInfo: StudentInfo?) {
                    TODO("Not yet implemented")
                }
            })
        }

        binding.btnKotlin.setOnClickListener {
            var student = StudentCoroutine()
            student.getTeachInfo(this@MainActivity, 999)
        }

        binding.btnRaw.setOnClickListener {
            startLaunch()
        }

        //点击UI
        binding.btnDelay.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                //在主线程执行协程
                Log.d("fish", "before suspend thread:${Thread.currentThread()}")
                //执行挂起函数
                getStuInfo()
            }
            binding.btnDelay.postDelayed({
                //延迟2s在主线程执行打印
                Log.d("fish", "post thread:${Thread.currentThread()}")
            }, 2000)
        }

        //分发
        binding.btnDispatch.setOnClickListener {
            var dispatch = CoroutineDispatch(binding.root.context)
//            dispatch.showStuInfo()
//            dispatch.showStuInfoV2()
            dispatch.launch1()
        }
    }
}